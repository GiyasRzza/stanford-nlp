package com.careerhive.file_service.core;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
@Component
public class PdfParser {

    public byte[] processPdf(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            PDDocument document = Loader.loadPDF(file.getBytes());
            System.out.println("PDF file processed.");

            for (PDPage page : document.getPages()) {
                PDResources resources = page.getResources();
                byte[] imageBytes = extractImagesFromPage(resources);
                if (imageBytes != null) {
                    document.close();
                    return imageBytes;
                }
            }

            document.close();
            return null;
        }
    }
    private byte[] extractImagesFromPage(PDResources resources) throws IOException {

        for (COSName xObjectName : resources.getXObjectNames()) {
            PDXObject xObject = resources.getXObject(xObjectName);
            if (xObject instanceof PDImageXObject image) {
                BufferedImage bufferedImage = image.getImage();
                return convertBufferedImageToBytes(bufferedImage);
            }
        }
        return null;
    }
    private byte[] convertBufferedImageToBytes(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", baos);
        return baos.toByteArray();
    }

}

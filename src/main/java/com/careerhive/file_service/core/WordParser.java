package com.careerhive.file_service.core;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class WordParser {

    public List<byte[]> processWord(MultipartFile file) throws IOException {
        List<byte[]> images = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            XWPFDocument document = new XWPFDocument(inputStream);
            System.out.println("DOCX file processed.");

            for (XWPFPictureData pictureData : document.getAllPictures()) {
                byte[] imageBytes = pictureData.getData();
                images.add(imageBytes);
            }
        }

        return images;
    }
}


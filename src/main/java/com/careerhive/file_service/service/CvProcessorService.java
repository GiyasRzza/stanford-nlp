package com.careerhive.file_service.service;
import com.careerhive.file_service.exception.InvalidFileTypeException;
import com.careerhive.file_service.core.PdfParser;
import com.careerhive.file_service.core.WordParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@AllArgsConstructor
public class CvProcessorService {
    private final PdfParser pdfParser;
    private final WordParser wordParser;
    
    
    public byte[] fileControl(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        if (fileName == null || (!fileName.endsWith(".pdf") && !fileName.endsWith(".docx") && !fileName.endsWith(".doc"))) {
            throw new InvalidFileTypeException("Only PDF and Word files are allowed.");
        }

        String fileContent = file.getContentType();
        System.out.println("File content type: " + fileContent);

        if (fileName.endsWith(".pdf")) {
          return   pdfParser.processPdf(file);
        } else if (fileName.endsWith(".docx") || fileName.endsWith(".doc")) {
         return   wordParser.processWord(file).get(1);
        }
        return null;
    }




}

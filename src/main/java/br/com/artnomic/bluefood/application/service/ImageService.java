package br.com.artnomic.bluefood.application.service;

import br.com.artnomic.bluefood.application.service.exception.ApplicationServiceException;
import br.com.artnomic.bluefood.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class ImageService {

    @Value("${bluefood.files.logotype}")
    private String logotypeDir;

    @Value("${bluefood.files.category}")
    private String categoryDir;

    @Value("${bluefood.files.food}")
    private String foodDir;

    public void uploadLogotype(MultipartFile multipartFile, String fileName) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, logotypeDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }

    public byte[] getBytes(String type, String imgName) {

        try {
            String dir;

            if ("food".equals(type)) {
                dir = foodDir;
            } else if ("logotype".equals(type)) {
                dir = logotypeDir;
            } else if ("category".equals(type)) {
                dir = categoryDir;
            } else {
                throw new Exception(type + " não é um tipo de imagem válido");
            }

           return IOUtils.getBytes(Paths.get(dir, imgName));
        } catch (Exception e) {
            throw new ApplicationServiceException(e);
        }
    }
}

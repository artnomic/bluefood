package br.com.artnomic.bluefood.application.service;

import br.com.artnomic.bluefood.application.service.exception.ApplicationServiceException;
import br.com.artnomic.bluefood.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Value("${bluefood.files.logotype}")
    private String loggotypeDir;

    public void uploadLogotype(MultipartFile multipartFile, String fileName) {
        try {
            IOUtils.copy(multipartFile.getInputStream(), fileName, loggotypeDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }
}

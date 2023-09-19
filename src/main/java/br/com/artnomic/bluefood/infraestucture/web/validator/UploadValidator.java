package br.com.artnomic.bluefood.infraestucture.web.validator;

import br.com.artnomic.bluefood.util.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class UploadValidator implements ConstraintValidator<UploadConstraint, MultipartFile> {

    private List<FileType> acceptedFileTypes;

    @Override
    public void initialize(UploadConstraint constraintAnnotation) {
        acceptedFileTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if(multipartFile == null) {
            return true;
        }

        for(FileType fileType : acceptedFileTypes) {
            if (fileType.sameOf(multipartFile.getContentType())) return true;
        }

        return false;
    }
}
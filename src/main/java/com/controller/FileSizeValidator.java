package com.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements Validator{

    private final int maxSizeFile = 4194304; //4M

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors e) {
        MultipartFile multipartFile = (MultipartFile) o;
        if(multipartFile.getSize()>maxSizeFile){
            e.rejectValue("attachment","size.uploaded.file");
        }
    }
}

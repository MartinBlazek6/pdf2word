package com.javacodepoint.fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ConverterService {

    void convertFileToPDF(MultipartFile file);

}

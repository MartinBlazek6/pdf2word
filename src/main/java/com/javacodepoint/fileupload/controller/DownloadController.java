package com.javacodepoint.fileupload.controller;

import com.javacodepoint.fileupload.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DownloadController {
    private Logger logger = LoggerFactory.getLogger(DownloadController.class);

//    @Autowired
//    private DownloadService downloadService;
//
//
//    @GetMapping("/dd")
//    public void downloadZipFile(HttpServletResponse response) {
//        List<String> listOfFileNames = getListOfFileNames();
//        downloadService.downloadZipFile(response, listOfFileNames);
//    }
//
//    /**
//     * List of file names for testing
//     * @return
//     */
//    private List<String> getListOfFileNames() {
//        List<String> listOfFileNames = new ArrayList<>();
//        listOfFileNames.add("/Users/blazek/Documents/pdf2word/src/main/java/com/javacodepoint/fileupload/holmes.docx");
//        listOfFileNames.add("/Users/blazek/Documents/pdf2word/src/main/java/com/javacodepoint/fileupload/holmes.jpg");
//        return listOfFileNames;
//    }

}
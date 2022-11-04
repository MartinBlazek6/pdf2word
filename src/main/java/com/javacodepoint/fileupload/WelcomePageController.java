package com.javacodepoint.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.javacodepoint.fileupload.FileUploadRestController.uploadDirectory;

@Controller
public class WelcomePageController {

    @GetMapping("/")
    public String index() {
        return "filesupload";
    }
}

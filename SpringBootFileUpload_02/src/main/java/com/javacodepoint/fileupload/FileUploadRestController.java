package com.javacodepoint.fileupload;

import com.javacodepoint.fileupload.service.ConverterService;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

import static com.javacodepoint.fileupload.service.ConvertererviceImpl.uploadDirectory;

@RestController
@AllArgsConstructor
public class FileUploadRestController {
	@Autowired
	 ConverterService converterService;

	/**
     * Location to save uploaded files on server
     */
	//private static String UPLOAD_PATH = "/Users/blazek/Downloads/";

	public static String uploadDirectory = System.getProperty("user.home")+"/ConvertedFiles/";

	/*
	 * single file upload in a request
	 */
//	@PostMapping("/upload")
//	public String uploadFile(@RequestParam("files") MultipartFile[] files, RedirectAttributes attributes) throws IOException {
//
//		if (files[0].isEmpty()) {
//			attributes.addFlashAttribute("messageAlert", "Please select a file to upload.");
//			return "redirect:/";
//		}
//		Arrays.stream(files).forEach(converterService::convertFileToPDF);
//		attributes.addFlashAttribute("message", "You successfully converted all files");
//		Desktop.getDesktop().open(new File(uploadDirectory));
//		return "redirect:/";
//	}


	@GetMapping("/api/f")
	public String xx() throws IOException {
		Desktop.getDesktop().open(new File(uploadDirectory));
		return null;
	}



	@PostMapping("/api/fileupload")
	public void uploadFile(@RequestParam("multipartFile") MultipartFile uploadfile) {

		if (uploadfile.isEmpty()) {
			System.out.println("please select a file!");
		}

		try {
			Locale newLocale = Locale.ROOT;
			Locale.setDefault(newLocale);
			PdfDocument pw=new PdfDocument();
			pw.loadFromBytes(uploadfile.getBytes());
			pw.saveToFile(uploadDirectory + Objects.requireNonNull(uploadfile.getOriginalFilename()).replace("pdf","docx"), FileFormat.DOCX);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

//			Desktop.getDesktop().open(new File(uploadDirectory));

	}
}

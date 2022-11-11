package com.javacodepoint.fileupload.controller;

import com.javacodepoint.fileupload.service.ConverterService;
import com.javacodepoint.fileupload.service.DownloadService;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class FileUploadRestController {
	@Autowired
	 ConverterService converterService;

	/**
     * Location to save uploaded files on server
     */
	//private static String UPLOAD_PATH = "/Users/blazek/Downloads/";

	public static String uploadDirectory = System.getProperty("user.home")+"/ConvertedFiles/"+System.currentTimeMillis()+"/";

	public static String fileaa;
	public List<String> allFiles;
	public List<File> allFiless;
	public List<PdfDocument> allFilessConverted;

	@GetMapping("/api/f")
	public String xx() throws IOException {
		Desktop.getDesktop().open(new File(uploadDirectory));
		return null;
	}

	@PostMapping("/api/fileupload")
	public void uploadFile(@RequestParam("multipartFile") MultipartFile uploadfile) {
		//uploadDirectory = System.getProperty("user.home")+"/ConvertedFiles"+System.currentTimeMillis()+"/";
		String pathX = uploadDirectory + Objects.requireNonNull(uploadfile.getOriginalFilename()).replace("pdf","docx");

		if (uploadfile.isEmpty()) {
			System.out.println("please select a file!");
		}

		try {
			Locale newLocale = Locale.ROOT;
			Locale.setDefault(newLocale);
			PdfDocument pw=new PdfDocument();
			pw.loadFromBytes(uploadfile.getBytes());
			pw.saveToFile(pathX, FileFormat.DOCX);
			allFilessConverted.add(pw);

			fileaa = pathX;
			allFiles.add(pathX);
			allFiless.add(new File(pathX));
			pw.close();
			allFiles.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile() throws IOException
	{
		String filename = fileaa;
		File file = new File(filename);
		//file = allFiless.get(allFiles.size());
		InputStreamResource resource = new InputStreamResource(Files.newInputStream(file.toPath()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition",
				String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);

		return responseEntity;
	}





	@Autowired
	private DownloadService downloadService;


	@GetMapping("/dd")
	public void downloadZipFile(HttpServletResponse response) {
		List<String> listOfFileNames = getListOfFileNames();
		downloadService.downloadZipFile2(response, allFiless);
		allFiles.clear();
	}

	/**
	 * List of file names for testing
	 * @return
	 */
	private List<String> getListOfFileNames() {
		List<String> listOfFileNames = new ArrayList<>();
		listOfFileNames = allFiles;
//		listOfFileNames.add("/Users/blazek/Documents/pdf2word/src/main/java/com/javacodepoint/fileupload/holmes.docx");
//		listOfFileNames.add("/Users/blazek/Documents/pdf2word/src/main/java/com/javacodepoint/fileupload/holmes.jpg");
		return listOfFileNames;
	}
}

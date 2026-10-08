package org.doit.ik.mreview.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.doit.ik.mreview.dto.UploadResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@Log4j2
public class UploadController {
	
	// 서버에 실제 업로드 폴더
	// application.propreties: org.doit.upload.path=C:\\upload 
	@Value("${org.doit.upload.path}")
	private String uploadPath;
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("/", File.separator);

		File uploadPathFolder = new File(uploadPath, folderPath);

		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> uploadFile(
		@RequestParam("uploadFiles") MultipartFile [] uploadFiles) {
		
		// 1. 업로드된 확장자가 이미지만 .. 검사
		// 2. 동일한 파일 X : uuid 
		// 3. 업로드된 파일의 용량 체크
		
		List<UploadResultDTO> resultDTOList = new ArrayList<>();
		
		for (MultipartFile uploadFile  : uploadFiles) {
			// 1. 이미지파일만 업로드 체크
			if(!uploadFile.getContentType().startsWith("image")) {
			  log.warn("❓ this file is not image type.");
			  // 403 Forbidden .
			  return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			} // if
			
			String originalFilename =  uploadFile.getOriginalFilename();
			
			// IE / Edge 브라우저에서는  전체경로 
			// 🤩🤩🤩   ~~~\\스크린샷 2026-09-07 092252.png
			// System.out.println("🤩🤩🤩" + originalFilename);
			originalFilename = originalFilename.substring(originalFilename.lastIndexOf("\\")+1);			
			
			String folderPath = makeFolder(); // 년도/월/일
			
			// UUID
			String uuid = UUID.randomUUID().toString();
			// C:\\upload\\년\\월\\일\\uuid_originalFilename
			String saveFileName = String.format("%s%s%s%s%s_%s"
					, uploadPath, File.separator,folderPath, File.separator, uuid, originalFilename);
			Path savePath = Paths.get(saveFileName);
			
			try {
				// 1. 업로드된 원본파일을 저장
				uploadFile.transferTo(savePath);  // 업로드 파일 1개씩 저장
				
				// 2. 섬네일 생성 -> 저장
				String thumbnailSaveName = uploadPath+File.separator+folderPath+File.separator+"s_"+uuid+"_"+originalFilename;
				File thumbnailFile = new File(thumbnailSaveName);
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100,100);
				// 
				resultDTOList.add(new UploadResultDTO(originalFilename, uuid, folderPath));
			} catch (IllegalStateException | IOException e) { 
				e.printStackTrace();
			} 
			
		} // foreach
		
		
		return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
	}
	
	// 이미지 응답 
	// URL 인코딩의 파일 이름을 파라미터로 받아서 
	// 해당 파일을 byte[] 로 만들어서
	// 브라우저 전달.
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName) {
		ResponseEntity<byte[]> result = null;
		
		try {
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info("fileName: "+srcFileName);
			
			File file = new File(uploadPath + File.separator + srcFileName);
			log.info("file: "+file);

	         HttpHeaders headers = new HttpHeaders();

	         // MIME타입 처리: 파일의 확장자에 따라서 브라우저에 전송하는 MIME타입이 달라져야 하는 문제 해결:  Files.probeContentType()
	         headers.add("Content-Type", Files.probeContentType(file.toPath()));
	         // 파일 데이터 처리: 스프링이 제공하는 FileCopyUtils 클래스 이용.
	         result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			
		} catch (IOException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(
			@RequestParam("fileName") String fileName
			){
		String srcFileName = null;
		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			File file = new File(uploadPath + File.separator+srcFileName);
			boolean result = file.delete();  // 1. 파일 삭제
			
			File thumbnail = new File(file.getParent(), "s_"+file.getName());
			result = thumbnail.delete(); // 2. 섬네일 파일 삭제

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);	// 500오류
		}
	}

}


















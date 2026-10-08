package org.doit.ik.mreview.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO {
	
	// 업로드된 원래 파일이름
	private String fileName;
	
	// 파일의 UUID값
	private String uuid;
	
	// 업로드된 파일의 저장 경로
	private String folderPath;
	
	//
	public String getImageURL() {
		try {
			return  URLEncoder.encode(this.folderPath+"/"+uuid+"_"+this.fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return  URLEncoder.encode(this.folderPath+"/s_"+uuid+"_"+this.fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		return "";
	}

}

package org.doit.ik.mreview.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {
	private String fileName;
	
	//파일의 UUID
	private String uuid;
	
	//업로드 된 파일의 저장 경로
	private String folderPath;
	
	//
	public String getImageUrl() {
		
		try {
			return URLEncoder.encode(this.folderPath + "/" + uuid + "_" + this.fileName, "UTF-8") ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
				
	}	//
	
	public String getThumbnailURL() {
		
		try {
			return URLEncoder.encode(this.folderPath + "/s_" + uuid + "_" + this.fileName, "UTF-8") ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
				
	}
}

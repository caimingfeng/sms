package com.sms.util.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class ImageAPI {
	private static Logger logger = LoggerFactory.getLogger(ImageAPI.class);
	/**
	 * 上传图片
	 * @param imgio
	 * @param name 图片名字
	 * @return
	 */
	public  static String upload( InputStream imgio ,String name,String type){
		//HttpClient httpClient = new HttpClient();
		String imgRep = "";


			try {
				imgRep = copyFile(imgio, Common.imgUrl, name);

//				HttpParame[] params = new HttpParame[6];
//				HttpParame file = new HttpParame("files", name,
//						imgio,"image/"+type);
//				params[0] = file;
//
//				HttpParame entity_source = new HttpParame("entity_source", "prod_gallery");
//				params[1] = entity_source;
//
//				HttpParame user_id = new HttpParame("user_id", "-1");
//				params[2] = user_id;
//
//				HttpParame folder_id = new HttpParame("folder_id", "-1");
//				params[3] = folder_id;
//
//				HttpParame files = new HttpParame("file", type);
//				params[4] = files;
//
//				HttpParame file_name = new HttpParame("file_name", name);
//				params[5] = file_name;
//
//
//				HttpRequest httpRequest = new HttpRequest(Common.uploadUrl, params,
//						HttpRequest.MethodType.POST, null);
//				HttpResponse httpResponse = httpClient.post(Common.uploadUrl, params);
//
//				imgRep=httpResponse.getBody();
				logger.info("imgRep 图片上传返回结果======================================"+imgRep);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return imgRep;
	}
	public static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
/*	public static void main(String[] args) throws Exception {
		File pFile = new File("E://1.jpeg");
		System.out.println(pFile.getName());
		ImageAPI  a = new ImageAPI();
		a.upload("E://1.jpeg", "wwwwwwwwww");
		
	}*/

	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
}

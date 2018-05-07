package com.sms.util.upload;
/*
package com.admin.util.upload;




import com.admin.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


*/
/**
 * Created by LRJ on 19/7/16. 生成PDF 上传至服务器
 *//*

@Component
public class PdfAPI {
	private static Logger logger = LoggerFactory.getLogger(PdfAPI.class);
	public static   String uploadPdf(InputStream pdfio,String name) {

		String resp = "";


		try {
			HttpClient httpClient = new HttpClient();
			HttpParame[] params = new HttpParame[3];
			// 生成pdf结束
			HttpParame file = new HttpParame("file", name,
					pdfio,HttpParame.PDF);
			params[0] = file;
			HttpParame entity_source = new HttpParame("entity_source", "files_src");
			params[1] = entity_source;

			HttpParame file_name = new HttpParame("file_name", name);
			params[2] = file_name;

			HttpRequest httpRequest = new HttpRequest(Common.uploadUrl, params,
					HttpRequest.MethodType.POST, null);
			HttpResponse httpResponse = httpClient.post(Common.uploadUrl, params);

			resp = httpResponse.getBody();
			logger.info("resp pdf上传返回结果======================================"+resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(!Strings.isNullOrEmpty(resp)){
			JSONObject object = JSON.parseObject(resp);
			if(null !=object && object.getString("file_id")!=null &&  !StringUtils.isEmpty(object.getString("file_id"))){
				resp =object.getString("file_id");
				return Common.imgUrl+resp;
			}
		}

		return "";

	}


	public static   String createPdf(String content,String name ,String type) {

		String resp = null;

		URL u = null;
		HttpURLConnection con = null;
		try {
			HttpClient httpClient = new HttpClient();
			HttpParame[] params = new HttpParame[2];
			// 生成pdf开始
			u = new URL(Common.pdfUrl+type);
			con = (HttpURLConnection) u.openConnection();
			con.setConnectTimeout(5000);// 连接主机的超时时间（单位：毫秒）
			con.setReadTimeout(7000);// 从主机读取数据的超时时间（单位：毫秒）
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("contentType", "utf-8");
			// con.setRequestProperty("Content-TypeWifiConstants.SPILT_STRapplication/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			// 构建请求参数

			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "utf-8");
			String temp="";
//			if(type.equals("webBank")){
//				temp = "bankName=" + content;
//			}else{
				temp = "postData=" + content;
		//	}

			osw.write(temp);
			osw.flush();
			osw.close();

			// 生成pdf结束

			HttpParame file = new HttpParame("file", name+".pdf",
					con.getInputStream(),HttpParame.PDF);


			params[0] = file;

			HttpParame entity_source = new HttpParame("entity_source", "files_src");
			params[1] = entity_source;

			HttpRequest httpRequest = new HttpRequest(Common.uploadUrl, params,
					HttpRequest.MethodType.POST, null);
			HttpResponse httpResponse = httpClient.post(Common.uploadUrl, params);

			resp = httpResponse.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		if(!Strings.isNullOrEmpty(resp)){

			JSONObject object = JSON.parseObject(resp);
			if(null !=object && object.getString("file_id")!=null &&  !StringUtils.isEmpty(object.getString("file_id"))){
				resp =object.getString("file_id");
				resp=Common.imgUrl+resp;
			}
		}
		System.out.println("生成商家银行确认函============="+resp);
		return resp;
	}


	public static void main(String[] args) throws Exception {
		createPdf("234","32423","webAuthorized");
		//String url=PdfAPI.createPdf("SKG","webSKG");
		// JsonObject result = new JsonObject();
		// String body = "{postData:'cont'}";
		// result.addProperty("postData", "true");
		//// System.out.println(result.toString());
		// Gson gson = new Gson();
		// JsonObject result = new JsonObject();
		// pdfResponse pdf= new pdfResponse();
		// pdf.setDate("2016");
		// pdf.setOrder("123456");
		// pdf.setUserName("name");
		// String r= gson.toJson(pdf);
		// // JsonObject vv = new JsonParser().parse(r).getAsJsonObject();
		//
		//
		// result.addProperty("postData",r);
		// result.toString().replaceAll("\"", "");
		// System.out.println( result.toString().replace("\\", ""));
//		JsonObject result = new JsonObject();
//		result.addProperty("userName", "1234324");
//		String content = " postData= " + result.toString();
//		System.out.println(content);
		// createPdf();
	}
}
*/

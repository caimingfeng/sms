package com.sms.util.upload;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

/**
 * @Project: ykframework
 * @Title: F5HttpRequest.java
 * @Description:  用于HTTP请求，并获取返回结果，可以使用base64、oauth认证
 * @Company: 
 * @Author: 刘守建
 * @Date: Dec 6, 2011 4:27:33 PM
 * @version 1.0
 */
public class HttpClient {

	private Proxy proxy = null;
	private int connectionTimeout = 20000;
	private int readTimeout = 120000;
	private String cookies = null;
	
	public HttpClient (){}

	/**
	 * 构造方法
	 * @param proxy 代理服务
	 */
	public HttpClient (Proxy proxy){
		this.proxy = proxy;
	}
	
	/**
	 * 使用post方式提交
	 * @param url 提交的URL
	 * @param params  提交的参数
	 * @return
	 */
	public HttpResponse post(String url , HttpParame[] params){
		return this.reuqest(new HttpRequest(url,params,HttpRequest.MethodType.POST,null));
	}

	/**
	 * 使用get方式提交
	 * @param url 提交的URL
	 * @param params  提交的参数
	 * @return
	 */
	public HttpResponse get(String url , HttpParame[] params ){
		url += "?" + HttpParame.encodeParameters(params);
		url = url.endsWith("?")?url.substring(0,url.length()-1):url;
		return this.reuqest(new HttpRequest(url,null,HttpRequest.MethodType.GET,null));
	}
	
	/**
	 * 使用post方式提交
	 * @param url 提交的URL
	 * @param params 提交的参数
	 * @param headers http包头中的内容
	 * @return
	 */
	public HttpResponse post(String url , HttpParame[] params,Map<String, String> headers){
		return this.reuqest(new HttpRequest(url,params,HttpRequest.MethodType.POST,headers));
	}

	/**
	 * 使用get方式提交
	 * @param url 提交的URL
	 * @param params 提交的参数
	 * @param headers http包头中的内容
	 * @return
	 */
	public HttpResponse get(String url , HttpParame[] params,Map<String, String> headers ){
		url += "?" + HttpParame.encodeParameters(params);
		url = url.endsWith("?")?url.substring(0,url.length()-1):url;
		return this.reuqest(new HttpRequest(url,null,HttpRequest.MethodType.GET,headers));
	}
	
	/**
	 * http提交
	 * @param request F5HttpResponse
	 * @return
	 */
	public HttpResponse reuqest(HttpRequest request){
        HttpResponse res = null;
        HttpURLConnection con = null;
        OutputStream os = null;
        try {
            con = getConnection(request.getUrl());
            con.setDoInput(true);
            setHeaders(request, con);
            con.setRequestMethod(request.getMethod());
            this.setCookies(con);
            if (request.getMethod().equals(HttpRequest.MethodType.POST)) {
                if (HttpParame.containsFile(request.getHttpParams())) {
                    String boundary = "----Follow5-upload" + System.currentTimeMillis();
                    con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                    boundary = "--" + boundary;
                    con.setDoOutput(true);
                    os = con.getOutputStream();
                    DataOutputStream out = new DataOutputStream(os);
                    for (HttpParame param : request.getHttpParams()) {
                        if (param.isFile()) {
                            write(out, boundary + "\r\n");
                            write(out, "Content-Disposition: form-data; name=\"" + param.getName() + "\"; filename=\"" + param.getFile().getName() + "\"\r\n");
                            write(out, "Content-Type: " + param.getContentType() + "\r\n\r\n");
                            BufferedInputStream in = new BufferedInputStream(
                            		param.hasFileBody() ? param.getFileBody() :new FileInputStream(param.getFile())
                            		);
                            int buff = 0;
                            while ((buff = in.read()) != -1) {
                                out.write(buff);
                            }
                            write(out, "\r\n");
                            in.close();
                        } else {
                            write(out, boundary + "\r\n");
                            write(out, "Content-Disposition: form-data; name=\"" + param.getName() + "\"\r\n");
                            write(out, "Content-Type: text/plain; charset=UTF-8\r\n\r\n");
                            out.write(param.getValue().getBytes("UTF-8"));
                            write(out, "\r\n");
                        }
                    }
                    write(out, boundary + "--\r\n");
                    write(out, "\r\n");

                    out.flush();     
        		    out.close();     
                } else {
                    con.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    String postParam = HttpParame.encodeParameters(request.getHttpParams());
                    byte[] bytes = postParam.getBytes("UTF-8");
                    con.setRequestProperty("Content-Length",
                            Integer.toString(bytes.length));
                    con.setDoOutput(true);
                    os = con.getOutputStream();
                    os.write(bytes);
                }
                os.flush();
                os.close();

                con.connect();
            }
            res = new HttpResponse(con);
            this.cookies = res.getCookie();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception ignore) {
            }
            try {
            	if(con != null)
            		con.disconnect();
            } catch (Exception ignore) {
            }
        }
        
        return res;
	}

	private void setCookies(HttpURLConnection con ){
		if(this.cookies != null)
			con.setRequestProperty("Cookie", getCookies());
	}
	
	private void setHeaders(HttpRequest request,
			HttpURLConnection connection) {
		//String authorizationHeader;
        Map<String, String> headers = request.getHeaders();
		if (null != headers) {
			for (String key : headers.keySet()) {
				connection.addRequestProperty(key, headers.get(key));
			}
		}
	}

	private void write(DataOutputStream out, String outStr) throws IOException {
		out.writeBytes(outStr);
	}
	 
	private HttpURLConnection getConnection(String url) throws IOException {
		HttpURLConnection con = null;
		if (isProxyConfigured()) {
			con = (HttpURLConnection) new URL(url).openConnection(proxy);
		} else {
			con = (HttpURLConnection) new URL(url).openConnection();
		}
		if (connectionTimeout > 0) {
			con.setConnectTimeout(connectionTimeout);
		}
		if (readTimeout > 0) {
			con.setReadTimeout(readTimeout);
		}
		con.setInstanceFollowRedirects(false);
		return con;
	}
	
	private boolean isProxyConfigured() {
		return proxy != null ;
	}
	
	/**
	 * 获取连接超时时间
	 * @return
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * 设置连接超时时间
	 * @param connectionTimeout
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * 获取读取内容的超时时间
	 * @return
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * 设置读取内容的超时时间
	 * @param readTimeout
	 */
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * 获取cookies
	 * @return
	 */
	public String getCookies() {
		return cookies;
	}

	/**
	 * 设置cookies
	 * @param cookies
	 */
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	/**
	 * 获取代理服务
	 * @return
	 */
	public Proxy getProxy() {
		return proxy;
	}

	/**
	 * 设置代理服务
	 * @return
	 */
	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
}

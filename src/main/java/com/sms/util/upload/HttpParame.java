package com.sms.util.upload;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class HttpParame implements Comparable<Object>,java.io.Serializable {

	private static final long serialVersionUID = 8037091340590107997L;
	private String name = null;
	private String value = null;
	private File file = null;
	private String contentType = null;
	private InputStream fileBody = null;

	public HttpParame(String name, String value) {
		this.name = name;
		this.value = value;
	}

//	public HttpParame(String name, File file) {
//		this.name = name;
//		this.file = file;
//	}

	public HttpParame(String name, String fileName, InputStream fileBody,String contentType) {
		this.name = name;
		this.file = new File(fileName);
		this.fileBody = fileBody;
		this.contentType = contentType;
	}

	public HttpParame(String name, int value) {
		this.name = name;
		this.value = String.valueOf(value);
	}

	public HttpParame(String name, long value) {
		this.name = name;
		this.value = String.valueOf(value);
	}

	public HttpParame(String name, double value) {
		this.name = name;
		this.value = String.valueOf(value);
	}

	public HttpParame(String name, boolean value) {
		this.name = name;
		this.value = String.valueOf(value);
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public File getFile() {
		return file;
	}

	public InputStream getFileBody() {
		return fileBody;
	}

	public boolean isFile() {
		return null != file;
	}

	public boolean hasFileBody() {
		return null != fileBody;
	}

	public static final String JPEG = "image/jpeg";
	public static final String JPG = "image/jpg";
	public static final String GIF = "image/gif";
	public static final String PNG = "image/png";
	public static final String OCTET = "application/octet-stream";
	public static final String EXL = "application/vnd.ms-excel";
	public static final String PDF = "application/pdf";
	/**
	 * 
	 * @return content-type
	 */
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof HttpParame))
			return false;

		HttpParame that = (HttpParame) o;

		if (file != null ? !file.equals(that.file) : that.file != null)
			return false;
		if (fileBody != null ? !fileBody.equals(that.fileBody)
				: that.fileBody != null)
			return false;
		if (!name.equals(that.name))
			return false;
		if (value != null ? !value.equals(that.value) : that.value != null)
			return false;

		return true;
	}

	public static boolean containsFile(HttpParame[] params) {
		boolean containsFile = false;
		if (null == params) {
			return false;
		}
		for (HttpParame param : params) {
			if (param.isFile()) {
				containsFile = true;
				break;
			}
		}
		return containsFile;
	}

	/*package*/static boolean containsFile(List<HttpParame> params) {
		boolean containsFile = false;
		for (HttpParame param : params) {
			if (param.isFile()) {
				containsFile = true;
				break;
			}
		}
		return containsFile;
	}

	public static HttpParame[] getParameterArray(String name, String value) {
		return new HttpParame[] { new HttpParame(name, value) };
	}

	public static HttpParame[] getParameterArray(String name, int value) {
		return getParameterArray(name, String.valueOf(value));
	}

	public static HttpParame[] getParameterArray(String name1,
			String value1, String name2, String value2) {
		return new HttpParame[] { new HttpParame(name1, value1),
				new HttpParame(name2, value2) };
	}

	public static HttpParame[] getParameterArray(String name1, int value1,
			String name2, int value2) {
		return getParameterArray(name1, String.valueOf(value1), name2, String
				.valueOf(value2));
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + (file != null ? file.hashCode() : 0);
		result = 31 * result + (fileBody != null ? fileBody.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PostParameter{" + "name='" + name + '\'' + ", value='" + value
				+ '\'' + ", file=" + file + ", fileBody=" + fileBody + '}';
	}

	public int compareTo(Object o) {
		int compared;
		HttpParame that = (HttpParame) o;
		compared = name.compareTo(that.name);
		if (0 == compared) {
			compared = value.compareTo(that.value);
		}
		return compared;
	}

	public static String encodeParameters(HttpParame[] httpParams) {
		if (null == httpParams) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < httpParams.length; j++) {
			if (httpParams[j].isFile()) {
				throw new IllegalArgumentException("parameter ["
						+ httpParams[j].name + "]should be text");
			}
			if (j != 0) {
				buf.append("&");
			}
			buf.append(encode(httpParams[j].name)).append("=").append(
					encode(httpParams[j].value));
		}
		return buf.toString();
	}

	/**
	 * @param value string to be encoded
	 * @return encoded string
	 * @see <a href="http://wiki.oauth.net/TestCases">OAuth / TestCases</a>
	 * @see <a href="http://groups.google.com/group/oauth/browse_thread/thread/a8398d0521f4ae3d/9d79b698ab217df2?hl=en&lnk=gst&q=space+encoding#9d79b698ab217df2">Space encoding - OAuth | Google Groups</a>
	 * @see <a href="http://tools.ietf.org/html/rfc3986#section-2.1">RFC 3986 - Uniform Resource Identifier (URI): Generic Syntax - 2.1. Percent-Encoding</a>
	 */
	public static String encode(String value) {
		String encoded = null;
		try {
			encoded = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException ignore) {
		}
		StringBuffer buf = new StringBuffer(encoded.length());
		char focus;
		for (int i = 0; i < encoded.length(); i++) {
			focus = encoded.charAt(i);
			if (focus == '*') {
				buf.append("%2A");
			} else if (focus == '+') {
				buf.append("%20");
			} else if (focus == '%' && (i + 1) < encoded.length()
					&& encoded.charAt(i + 1) == '7'
					&& encoded.charAt(i + 2) == 'E') {
				buf.append('~');
				i += 2;
			} else {
				buf.append(focus);
			}
		}
		return buf.toString();
	}

}

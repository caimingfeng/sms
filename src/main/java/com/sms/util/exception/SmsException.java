package com.sms.util.exception;
/**
* @author 蔡名锋:
* @version 创建时间：2018年4月15日 下午4:14:16
* <pre>
* 类说明:
* </pre>
*/
@SuppressWarnings("serial")
public class SmsException extends RuntimeException {

	public SmsException() {
		super();
	}
	
	public SmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SmsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmsException(String message) {
		super(message);
	}

	public SmsException(Throwable cause) {
		super(cause);
	}
}

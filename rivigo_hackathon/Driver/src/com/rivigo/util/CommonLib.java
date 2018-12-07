package com.rivigo.util;

import org.apache.tomcat.util.codec.binary.Base64;
import com.google.gson.Gson;

public class CommonLib {

	public static final boolean ZLOG = false;
	public static final boolean LIVE_BUILD = false;
	
	public static String VYOM_SUPPLY_SERVER =  "https://demand.vyom.com/api/";
	public static final String SEND_OTP_URL =  CommonLib.VYOM_SUPPLY_SERVER + "user/sendotp";
	public static final String CONFIRM_OTP_URL =  CommonLib.VYOM_SUPPLY_SERVER + "user/confirmotp";
	
	public static final int SEND_OTP =  101;
	public static final int CONFIRM_OTP =  102;
	
    public static final String HEADER_KEY_VERSION = "v";
    public static final String HEADER_KEY_ACCEPT_FORMAT = "Accept";
    public static final String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_KEY_SOURCE = "s";
    public static final String HEADER_KEY_LANGUAGE = "ln";
    
	public static final int RESPONSE_CODE_SUCCESS = 0;
	public static final int RESPONSE_CODE_USER_DOESN_NOT_EXIST = 1001;
	public static final int RESPONSE_CODE_INVALID_NUMBER = 1002;
	public static final int RESPONSE_CODE_INVALID_OTP = 1003;
	public static final int RESPONSE_CODE_USER_MATERIAL_NOT_COMPLETED = 1004;
	public static final int RESPONSE_CODE_USER_ALREADY_EXISTS = 1003;
	public static final int RESPONSE_CODE_UNAUTHORIZED = 401;

	public static final String ERROR_USER_MATERIAL_NOT_COMPLETED = "Complete all materials firdy";
	public static final String ERROR_USER_DOES_NOT_EXIST = "User does not exist";
	public static final String ERROR_USER_ALREADY_EXIST = "User already exists";
	public static final String ERROR_INVALID_NUMBER = "Invalid number";
	public static final String ERROR_INVALID_PASSWORD = "Password length must be greater than 3";
	public static final String ERROR_HACK_TRY = "I am sorry, but who's this?";
	
	public static boolean isValidNumber(String number) {
		if (number != null && number.length() == 10)
			return true;
		return false;
	} 
	
	public static String getJson(Object object) {
		return new Gson().toJson(object);
	}

	public static Object fromJson(String json, Class<?> T) {
		return new Gson().fromJson(json, T);
	}
	
	public static String getAccessToken(int supplierId) {
		String keySource = supplierId + "shiftnow" + String.valueOf(System.currentTimeMillis() * 1000)
				+ String.valueOf((int) (Math.random() * 1000 * 1000));
		byte[] tokenByte = Base64.encodeBase64(keySource.getBytes());
		return new String(tokenByte);
	}

}

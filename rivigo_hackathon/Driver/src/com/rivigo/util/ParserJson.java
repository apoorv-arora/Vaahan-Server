package com.rivigo.util;

import java.io.InputStream;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ParserJson {

	public static final Object parse(InputStream is, int type) {
		Object returnObject = null;

		if (is == null)
			return returnObject;

		JSONObject responseJson = null;
		try {
			responseJson = ParserJson.convertInputStreamToJSON(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (type == CommonLib.SEND_OTP)
				return responseJson;
			else if (type == CommonLib.CONFIRM_OTP) {
				return ParserJson.parse_Confirm_Otp_Response(responseJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObject;
	}

	public static final Object parse_Confirm_Otp_Response(JSONObject responseJson) throws JSONException {
		if (responseJson == null)
			return null;

		if (responseJson.has("code") && responseJson.get("code") instanceof Integer) {
			int code = responseJson.getInt("code");
			if (code == CommonLib.RESPONSE_CODE_SUCCESS) {
				if (responseJson.has("myHashMap") && responseJson.get("myHashMap") instanceof JSONObject) {
					JSONObject myHashMapJson = responseJson.getJSONObject("myHashMap");
					if (myHashMapJson.has("accessToken")) {
						return String.valueOf(myHashMapJson.get("accessToken"));
					}
				}
			}
		}
		return responseJson;
	}

	@SuppressWarnings("resource")
	public static JSONObject convertInputStreamToJSON(InputStream is) throws JSONException {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		String responseJSON = s.hasNext() ? s.next() : "";
		JSONObject map = new JSONObject(responseJSON);
		return map;
	}

	@SuppressWarnings("resource")
	public static String convertInputStream(InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		String responseJSON = s.hasNext() ? s.next() : "";
		return responseJSON;
	}
}

package com.rivigo.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.mail.Email;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.rivigo.controller.UserDetailDataAccess;
import com.rivigo.database.classes.User;
import com.rivigo.database.classes.UserSession;
import com.rivigo.responseDto.BaseResponse;
import com.rivigo.responseDto.LoginResponseDto;
import com.rivigo.util.CommonLib;
import com.rivigo.util.ConnectionRetryManager;
import com.rivigo.util.HttpManager;

@Path("/auth")
public class AuthApi extends BaseResource {

	public static final int LOGIN_TYPE_GOOGLE = 101;
	public static final int LOGIN_TYPE_FACEBOOK = 102;
	public static final int LOGIN_TYPE_SELF = 103;

	public AuthApi() {
		super(AuthApi.class.getName());
	}

	/**
	 * @author apoorvarora
	 */
	@Path("/sendOtp")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String loginAuthorization(@FormParam("userName") String userName, @FormParam("number") String number,
			@FormParam("token") String token) {
		LoginResponseDto responseDto = new LoginResponseDto();

		// check for keys
		if (!CommonLib.isValidNumber(number)) {
			responseDto.setCode(CommonLib.RESPONSE_CODE_INVALID_NUMBER);
			responseDto.setErrorMessage(CommonLib.ERROR_INVALID_NUMBER);
			return CommonLib.getJson(responseDto);
		}
		JSONObject response = null;
		// hit vyom service for send otp
		if (token == null || token.length() != 4) {
			// List<NameValuePair> params = new ArrayList<>();
			// params.add(new BasicNameValuePair("phoneNumber", number));

			JSONObject requestJson = new JSONObject();
			try {
				requestJson.put("phoneNumber", number);
			} catch (JSONException e) {
				e.printStackTrace();
				responseDto.setCode(CommonLib.RESPONSE_CODE_INVALID_NUMBER);
				responseDto.setErrorMessage(CommonLib.ERROR_INVALID_NUMBER);
				return CommonLib.getJson(responseDto);
			}

			response = (JSONObject) HttpManager.requestHttpsPost(CommonLib.SEND_OTP_URL, CommonLib.SEND_OTP,
					String.valueOf(requestJson), token, ConnectionRetryManager.DEFAULT_RETRIES);
		} else {
			// List<NameValuePair> params = new ArrayList<>();
			// params.add(new BasicNameValuePair("phoneNumber", number));
			// params.add(new BasicNameValuePair("otp", token));
			JSONObject requestJson = new JSONObject();
			try {
				requestJson.put("phoneNumber", number);
				requestJson.put("otp", token);
			} catch (JSONException e) {
				e.printStackTrace();
				responseDto.setCode(CommonLib.RESPONSE_CODE_INVALID_NUMBER);
				responseDto.setErrorMessage(CommonLib.ERROR_INVALID_NUMBER);
				return CommonLib.getJson(responseDto);
			}

			response = (JSONObject) HttpManager.requestHttpsPost(CommonLib.CONFIRM_OTP_URL, CommonLib.CONFIRM_OTP,
					String.valueOf(requestJson), token, ConnectionRetryManager.DEFAULT_RETRIES);
		}

		return CommonLib.getJson(response);
	}

	// UserDetailDataAccess uDetailDataAccess = new UserDetailDataAccess();
	// User user = uDetailDataAccess.getUserDetails(number);
	//
	// UserSession userSession =
	// uDetailDataAccess.setUserSessionDetails(user.getUserId());
	// if (userSession != null && userSession.getAccessToken() != null &&
	// userSession.getUid() == user.getUserId()) {
	// responseDto.setUserProfiles(uDetailDataAccess.getUserProfileDetailsListFromUserId(user.getUserId()));
	// responseDto.setAccessToken(userSession.getAccessToken());
	// responseDto.setUser(user);
	// }
	//

	/**
	 * Logout
	 * 
	 * @author apoorvarora
	 */
	@Path("/logout")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String userLogout(@FormParam("accessToken") String accessToken) {
		BaseResponse responseDto = new BaseResponse();

		UserDetailDataAccess uDetailAccess = new UserDetailDataAccess();
		User user = uDetailAccess.getUserDetails(accessToken);
		// check if supplier exists
		if (user != null && user.getUserId() > 0) {
			uDetailAccess.removeUserSession(accessToken);
			responseDto.setCode(CommonLib.RESPONSE_CODE_SUCCESS);
		} else {
			responseDto.setCode(CommonLib.RESPONSE_CODE_USER_DOESN_NOT_EXIST);
			responseDto.setErrorMessage(CommonLib.ERROR_USER_DOES_NOT_EXIST);
		}
		return CommonLib.getJson(responseDto);
	}

}
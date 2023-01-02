package com.infra.smsapp.util;

public class SMSUtil {

	public static boolean isNullOrEmpty(String str) {
		return !(str != null && !str.trim().isEmpty());
	}

}

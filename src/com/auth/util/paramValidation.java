package com.auth.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


public class paramValidation {

	public boolean isAaadharValid(String aadhaar) {
		Pattern p = Pattern.compile("^\\d{12}$");
		Matcher numberMatcher;
		boolean isAadhaar;
		numberMatcher = p.matcher(aadhaar);

		if (numberMatcher.matches()) {
			isAadhaar = true;
			return isAadhaar;
		} else {
			isAadhaar = false;
			return isAadhaar;
		}
	}

	public boolean isAuthmodeValid(String authmode) {
		if(StringUtils.isNotEmpty(authmode)||authmode.equalsIgnoreCase("OTP")||authmode.equalsIgnoreCase("BIO")){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	public boolean isFilepathValid(String filepath) {
		if(StringUtils.isNotEmpty(filepath)){
			
			return true;
		}else{
			
			return false;
		}
	}

	public boolean isResponseurlValid(String responseurl) {
		if(StringUtils.isNotEmpty(responseurl)){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	public boolean isNameValid(String name) {
		if(StringUtils.isNotEmpty(name)){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	public boolean isLocationValid(String location) {
		if(StringUtils.isNotEmpty(location)){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	public boolean isReasonValid(String reason) {
		if(StringUtils.isNotEmpty(reason)){
			
			return true;
		}else{
			
			return false;
		}
	}
	
	public boolean istxnValid(String txn) {
		if(StringUtils.isNotEmpty(txn)){
			return true;
		}else{
			return false;
		}
	}
	
}

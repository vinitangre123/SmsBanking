package com.infra.smsapp.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infra.smsapp.POJO.Input;
import com.infra.smsapp.constant.AppConstant;
import com.infra.smsapp.service.SMSService;
import com.infra.smsapp.util.SMSUtil;

@RestController
@RequestMapping("/sms/rest/")
public class SMSController {

	@Autowired
	SMSService service;
	
	 
	private static Logger logger = LogManager.getLogger(SMSController.class);

	boolean flag = false;

	@RequestMapping(value = "/processreq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
			  produces = {
					  MediaType.APPLICATION_FORM_URLENCODED_VALUE
					  })
	public boolean processRequest(@RequestBody Input input) {

		String response = "";
		logger.info("Request Parameters Received : " + "Mobile No : " + input.getMobileno() + "Card No : " + input.getCardNo() + "Function : "
				+ input.getFunction());

		boolean isValid = validateInput(input.getMobileno(), input.getCardNo(), input.getFunction());

		if (isValid) {

			List<String> list = service.Hotccard(input.getMobileno());
			if (list.size() > 0) {
				String Cardid = "";
				if (input.getFunction().equalsIgnoreCase(AppConstant.HOTC)) {
					flag = service.cardblock(list);

				} else if (input.getFunction().equalsIgnoreCase(AppConstant.UNHOTC)) {
					flag=service.cardUnblock(list);
					}

			} else {

			}

		}
		return flag;

	}

	private boolean validateInput(String mobileNo, String cardNo, String function) {

		if (SMSUtil.isNullOrEmpty(function)) {
			return false;
		} else if (SMSUtil.isNullOrEmpty(mobileNo)) {
			return false;
		} else if (SMSUtil.isNullOrEmpty(cardNo)) {
			return false;
		}
		return true;
	}

}

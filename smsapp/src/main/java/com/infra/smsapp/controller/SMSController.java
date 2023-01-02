package com.infra.smsapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infra.smsapp.constant.AppConstant;
import com.infra.smsapp.entity.MobileDetails;
import com.infra.smsapp.service.SMSService;
import com.infra.smsapp.util.SMSUtil;

@RestController
@RequestMapping("/sms/rest/")
public class SMSController {

	@Autowired
	SMSService service;
	
	
	private static Logger logger = LogManager.getLogger(SMSController.class);

	boolean flag = false;

	@RequestMapping(value = "/processrequnblock", method = RequestMethod.POST)
	public ResponseEntity<?> processRequest(@RequestParam String mobileNo, @RequestParam String cardNo,
			@RequestParam String function) {

		String response = "";
		HashMap<String,String>map=new HashMap<>();
		logger.info("Request Parameters Received : " + " Mobile No : " + mobileNo + " Card No : " + cardNo + " Function : " + function);

		MobileDetails mobileDetails = new MobileDetails();

		boolean isValid = validateInput(mobileNo, cardNo, function);

		if (isValid) {

			mobileDetails = service.Hotccard(mobileNo, cardNo);
			if (mobileDetails != null) 
			{
	
			  if (function.equalsIgnoreCase(AppConstant.UNHOTC)) {
					map = service.cardUnblock(mobileDetails.getCardNo(), mobileDetails.getMaskCard(),mobileDetails.getMobileNo());
				}

			} else {
				logger.error("Invalid Request Details :" + " Mobile No: " + mobileNo + " Card No : " + cardNo);

			}

		
		
		
		}
		return ResponseEntity.ok(map);

	}
//for cardblock
	@RequestMapping(value = "/processreqblock", method = RequestMethod.POST)
	public ResponseEntity<?> processRequest2(@RequestParam String mobileNo,
			@RequestParam String function) {

		String response = "";
		HashMap<String,String>map=new HashMap<>();
		logger.info("Request Parameters Received : " + " Mobile No : " + mobileNo +" Function : " + function);

		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		
		
		boolean isValid = validateInput2(mobileNo, function);

		if (isValid) {
            
			list = service.HotccardBlock(mobileNo);
			list2=service.MaskedCardlist(mobileNo);
			System.out.println("Final Cards :" + list2 );
			
			String mobileno=list.get(0);
			logger.info("mobileno"+mobileno);
			//String MaskCardno=list.get(1); 
			 String finalString=" ";
			 String temp=" ";
			 
			  finalString=list2.toString();
			
			
			
			if (list != null) {
				
				if (function.equalsIgnoreCase(AppConstant.HOTC)) {
				map = service.cardblock(list.get(0).toString(),finalString);

			} else {
				logger.error("Invalid Request Details :" + " Mobile No: " + mobileNo );

			}

		}
		}
		return ResponseEntity.ok(map);

	}
	@RequestMapping(value = "/processreqlimitupdate", method = RequestMethod.POST)
	public ResponseEntity<?> processRequest3(@RequestParam String Type,@RequestParam String cardNo,@RequestParam String CardLocalLimit,@RequestParam String mobileNo) {

		String response = "";
		HashMap<String,String>map=new HashMap<>();
		float cardlimit=Float.valueOf(CardLocalLimit);
		logger.info("Request Parameters Received : " + " Type : " + Type +" cardno : " + cardNo  +" cardlocallimit : " + CardLocalLimit  +" mobileno : " + mobileNo);

		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		
		
		boolean isValid = validateInput3(mobileNo, cardNo,Type,CardLocalLimit);

		if (isValid) {
            
			MobileDetails mobileDetails = new MobileDetails();
			mobileDetails = service.Hotccard(mobileNo, cardNo);
			System.out.println("Final records :" + mobileDetails );
			
			if (mobileDetails != null) {
				
				if (Type.equalsIgnoreCase(AppConstant.ATM)) {
				map = service.cardlimitAtmLocal(mobileDetails.getCardNo(),cardlimit,mobileDetails.getMaskCard(),mobileDetails.getMobileNo(),mobileDetails.getCardLimit());
				logger.info("Request Parameters Received : " + " Mobile No : " + mobileNo +" cardno : " + cardNo +" type : " + Type);

			} else if(Type.equalsIgnoreCase(AppConstant.ECOM))
			{
				map = service.cardlimitEcomLocal(mobileDetails.getCardNo(),cardlimit,mobileDetails.getMaskCard(),mobileDetails.getMobileNo(),mobileDetails.getCardPOSLocalLimit(),mobileDetails.getCardPOSLimit());
				logger.info("Request Parameters Received : " + " Mobile No : " + mobileNo +" cardno : " + cardNo +" type : " + Type);
			}
			else if(Type.equalsIgnoreCase(AppConstant.POS))
			{
				map = service.cardlimitPosLocal(mobileDetails.getCardNo(),cardlimit,mobileDetails.getMaskCard(),mobileDetails.getMobileNo(),mobileDetails.getCardECOMLocalLimit(),mobileDetails.getCardPOSLimit());
				logger.info("Request Parameters Received : " + " Mobile No : " + mobileNo +" cardno : " + cardNo +" type : " + Type);
			}
		}
		}
		return ResponseEntity.ok(map);

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
	private boolean validateInput2(String mobileNo, String function) {

		if (SMSUtil.isNullOrEmpty(function)) {
			return false;
		} else if (SMSUtil.isNullOrEmpty(mobileNo)) {
			return false;
		} 
		return true;
	}
	private boolean validateInput3(String mobileNo, String function,String type,String Cardlocallimit) {

		if (SMSUtil.isNullOrEmpty(function)) {
			return false;
		} else if (SMSUtil.isNullOrEmpty(mobileNo)) {
			return false;
		} 
	     else if (SMSUtil.isNullOrEmpty(type)){
		return false;
	   } 
	     else if (SMSUtil.isNullOrEmpty(Cardlocallimit)) {
	 		return false;
	 	   }
		return true;
	}
}

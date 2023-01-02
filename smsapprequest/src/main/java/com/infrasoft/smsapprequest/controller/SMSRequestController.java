package com.infrasoft.smsapprequest.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infrasoft.smsapprequest.Utility.SendSMS;

@RestController
@RequestMapping("/sms/rest/")
public class SMSRequestController {
	
	private static Logger logger = LogManager.getLogger(SMSRequestController.class);
	
	@Value("${RequestURL}")
	private String RequestURL;
	
	@Value("${RequestURL2}")
	private String RequestURL2;
	
	@Value("${RequestURL3}")
	private String RequestURL3;
	
	
	@Autowired
	SendSMS sms;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String processRequest()
	{
		return "Welocme vinit";
		
	}
	

	@RequestMapping(value = "/processunblockreq", method = RequestMethod.GET)
	public boolean processRequest(@RequestParam String mobileNo, @RequestParam String cardNo,@RequestParam String function) {

		boolean flag= false;
		String mobno= mobileNo.substring(2);
		logger.info("Request Parameters Received : " + " Mobile No : " + mobno + " Card No : " + cardNo + " Function : " + function);

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("mobileNo", mobno);
		map.add("cardNo", cardNo);
		map.add("function", function);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        
		ResponseEntity<Map> response = restTemplate.exchange(RequestURL, HttpMethod.POST, request, Map.class);
		
		String mobileno=(String) response.getBody().get("Mobileno");
		String CardNo=(String) response.getBody().get("cardNo");
		String MSG=(String) response.getBody().get("Msg");
		logger.info("Mobile no :" + mobileno);
		logger.info("CardNO :" + CardNo);
		logger.info("Msg :" + MSG);
		try {
			String resp=sms.sendSMS(mobileno.trim(), CardNo,MSG, function);
			logger.info("response :" + resp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Error : " + e.getMessage());
		}
		
		if (response.getStatusCode() == HttpStatus.OK) {
			logger.info("Response From SMS Service for succesful: " + response.getStatusCode());
			logger.info("Response From SMS Service for succesful: " + response.getBody().toString().trim());
			
			flag = true;
		} else {
			logger.info("Error Response From SMS Service for unsuccesful : " + response.getStatusCode());
			logger.info("Error Response From SMS Service for unsuccesful:" + response.getBody().toString().trim());
			flag = false;
		}

        

	
		return flag;

	}
	//for Block
	@RequestMapping(value ="/processblockreq", method = RequestMethod.GET)
	public boolean processRequest2(@RequestParam String mobileNo,@RequestParam String function) {
		
         
		boolean flag= false;
		//String response = "";
		String mobno= mobileNo.substring(2);
		logger.info("Request Parameters Received : " + " Mobile No : " + mobno +" Function : " + function);

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("mobileNo", mobno);
		//map.add("cardNo", cardNo);
		map.add("function", function);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        
		ResponseEntity<Map> response = restTemplate.exchange(RequestURL2, HttpMethod.POST, request, Map.class);
		
		String mobileno=(String) response.getBody().get("Mobileno");
		String CardNo=(String) response.getBody().get("Cards");
		
		String MSG=(String) response.getBody().get("Msg");
		logger.info("Mobile no :" + mobileno);
		logger.info("Cards :" + CardNo);
		logger.info("Msg :" + MSG);
		

		try {
			String resp=sms.sendSMS(mobileno.trim(), CardNo,MSG,function);
			logger.info("response :" + resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Error : " + e.getMessage());
		}
		
		if (response.getStatusCode() == HttpStatus.OK) {
			logger.info("Response From SMS Service for succesful: " + response.getStatusCode());
			logger.info("Response From SMS Service for succesful: " + response.getBody().toString().trim());
			
			flag = true;
		} else {
			logger.info("Error Response From SMS Service for unsuccesful : " + response.getStatusCode());
			logger.info("Response From SMS Service for unsuccesful: " + response.getBody().toString().trim());
			flag = false;
		}

        

	
		return flag;

	}
	
	@RequestMapping(value ="/processlimitupdate", method = RequestMethod.GET)
	public boolean processRequest3(@RequestParam String Type,@RequestParam String CardNo,@RequestParam String CardLocalLimit,@RequestParam String mobileNo) {
		
         
		boolean flag= false;
		//String response = "";
		String mobno= mobileNo.substring(2);
		logger.info("Request Parameters Received : " +" Type : " +Type +  " CardNo : " +CardNo +  " CardLocalLimit : " +CardLocalLimit  + " Mobile No : " + mobno );

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("Type", Type);
		map.add("cardNo", CardNo);
		map.add("CardLocalLimit", CardLocalLimit);
		map.add("mobileNo", mobno);
	
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        
		ResponseEntity<Map> response = restTemplate.exchange(RequestURL3, HttpMethod.POST, request, Map.class);
		
		String mobileno=(String) response.getBody().get("Mobileno");
		String Cardno=(String) response.getBody().get("cardNo");
		String MSG=(String) response.getBody().get("Msg");
		String cardlimit=(String) response.getBody().get("cardlimit");
		logger.info("Mobile no :" + mobileno);
		logger.info("Cardno :" + Cardno);
		logger.info("Msg :" + MSG);
		logger.info("cardlimit :" + cardlimit);
		

		try {
			String resp=sms.sendSMSforlimit(Cardno,MSG,cardlimit,mobileno.trim());
			logger.info("response :" + resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Error : " + e.getMessage());
		}
		
		if (response.getStatusCode() == HttpStatus.OK) {
			logger.info("Response From SMS Service for succesful: " + response.getStatusCode());
			logger.info("Response From SMS Service for succesful: " + response.getBody().toString().trim());
			
			flag = true;
		} else {
			logger.info("Error Response From SMS Service for unsuccesful : " + response.getStatusCode());
			logger.info("Response From SMS Service for unsuccesful: " + response.getBody().toString().trim());
			flag = false;
		}

        

	
		return flag;

	}
}

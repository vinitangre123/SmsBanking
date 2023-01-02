package com.infra.smsapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infra.smsapp.controller.SMSController;
import com.infra.smsapp.dao.SMSRepository;
import com.infra.smsapp.entity.MobileDetails;


@Service
public class SMSService {
	
	private static Logger logger = LogManager.getLogger(SMSService.class);
	
	@Value("${CardBlockMsg}")
	private String CardBlockMsg;
	
	
	@Value("${CardUnBlockMsg}")
	private String CardUnBlockMsg;
	
	@Value("${CardATMlocalLimit}")
	private String CardATMlocalLimit;
	
	@Value("${CardECOMlocalLimit}")
	private String CardECOMlocalLimit;
	
	@Value("${CardPOSlocalLimit}")
	private String CardPOSlocalLimit;
	
	@Value("${CardDefaultLimit}")
	private String CardDefaultLimit;
	

	
	@Autowired
	SMSRepository smsrepo;

	MobileDetails mobileDetails;
	
	
	HashMap<String,String>map=new HashMap<>();

	public MobileDetails Hotccard(String mobileNo, String maskCard) {
		mobileDetails = smsrepo.getdetalis(mobileNo, maskCard);
		return mobileDetails;
	}
	
	
	public List<String> HotccardBlock(String mobileNo) {
		List<String> list = smsrepo.getdetalisCardblock(mobileNo);
		return list;
	}
	
	
	public List<String> MaskedCardlist(String mobileNo) {
		List<String> list = smsrepo.getMaskedacrd(mobileNo);
		return list;
	}
	
	
	
	public HashMap<String, String> cardblock(String mobileno,String maskCards) {
		boolean flag = false;
		
		int a= smsrepo.hotcD390070(mobileno);
		int b = smsrepo.hotcD390060(mobileno);
	
	
		if (a == b) {
     
			//String MSG="Dear customer,your card is "+maskCard+" blocked KAIJSB PVT LTD.";
			logger.info("INSIDE METHOD");
			logger.info("maskCard"+maskCards.trim());
			map.put("Mobileno",mobileno);
             map.put("Cards",maskCards.trim());
             map.put("Msg",CardBlockMsg );
			//callsmsws.CallSMSWS(mobileno, MSG);
			flag = true;
		}

		return map;

	}

	public HashMap<String, String> cardUnblock(String cardNo, String maskCard,String mobileno) {
		boolean flag = false;

		int a = smsrepo.UnhotcD390070(cardNo);
		int b = smsrepo.UnhotcD390060(cardNo);
		if (a == b) {
			
			
			String MSG="Dear customer,your card is "+maskCard+" Unblocked KAIJSB PVT LTD.";
			logger.info("INSIDE METHOD");
			map.put("Mobileno",mobileno);
             map.put("cardNo",maskCard);
             map.put("Msg",CardUnBlockMsg);
			//callsmsws.CallSMSWS(mobileno, MSG);
			flag = true;
			
		}

		return map;

	}

	public HashMap<String, String> cardlimitAtmLocal(String cardNo, float cardlimit,String Maskcard,String mobileno,float atmlocal) {
		boolean flag = false;

		if(cardlimit > atmlocal)	
		{
			map.put("Mobileno",mobileno);
            map.put("cardNo",Maskcard);
            map.put("Msg",CardDefaultLimit);
            map.put("cardlimit",String.valueOf(atmlocal));
			//callsmsws.CallSMSWS(mobileno, MSG);
			flag = true;
		}
		else
		{
			int a = smsrepo.LimitupdateAtmLocalD390060(cardlimit,cardNo);
			if (a == 1) {
				
				
				//String MSG="Dear customer,your card is "+maskCard+" Unblocked KAIJSB PVT LTD.";
				logger.info("INSIDE METHOD");
				map.put("Mobileno",mobileno);
	             map.put("cardNo",Maskcard);
	             map.put("Msg",CardATMlocalLimit);
	             map.put("cardlimit",String.valueOf(cardlimit));
				//callsmsws.CallSMSWS(mobileno, MSG);
				flag = true;
				
		}
		
		}

		return map;

	}
	public HashMap<String, String> cardlimitEcomLocal(String cardNo, float cardlimit,String Maskcard,String mobileno,float cardposlocallimit,float cardposlimit) {
		boolean flag = false;

		if((cardlimit + cardposlocallimit) >cardposlimit)	
		{
			logger.info("INSIDE METHOD");
			map.put("Mobileno",mobileno);
             map.put("cardNo",Maskcard);
             map.put("Msg",CardDefaultLimit);
             map.put("cardlimit",String.valueOf(cardposlimit));
			//callsmsws.CallSMSWS(mobileno, MSG);
			flag = true;
			
		}
		else
		{
			int a = smsrepo.LimitupdateEcomLocalD390060(cardlimit,cardNo);
			if (a==1) {
				
				
				//String MSG="Dear customer,your card is "+maskCard+" Unblocked KAIJSB PVT LTD.";
				logger.info("INSIDE METHOD");
				map.put("Mobileno",mobileno);
	             map.put("cardNo",Maskcard);
	             map.put("Msg",CardECOMlocalLimit);
	             map.put("cardlimit",String.valueOf(cardlimit));
				//callsmsws.CallSMSWS(mobileno, MSG);
				flag = true;
				
			}
		}
		
	
	

		return map;

	}
	public HashMap<String, String> cardlimitPosLocal(String cardNo, float cardlimit,String Maskcard,String mobileno,float cardecomlocallimit,float cardposlimit) {
		boolean flag = false;

		if(cardlimit +cardecomlocallimit>cardposlimit)
		{
			logger.info("INSIDE METHOD");
			map.put("Mobileno",mobileno);
             map.put("cardNo",Maskcard);
             map.put("Msg",CardDefaultLimit);
             map.put("cardlimit",String.valueOf(cardposlimit));
			//callsmsws.CallSMSWS(mobileno, MSG);
			flag = true;
		}
		else
		{
			int a = smsrepo.LimitupdatePosLocalD390060(cardlimit,cardNo);
			if (a==1) {
				
				
				//String MSG="Dear customer,your card is "+maskCard+" Unblocked KAIJSB PVT LTD.";
				logger.info("INSIDE METHOD");
				map.put("Mobileno",mobileno);
	             map.put("cardNo",Maskcard);
	             map.put("Msg",CardPOSlocalLimit);
	             map.put("cardlimit",String.valueOf(cardlimit));
				//callsmsws.CallSMSWS(mobileno, MSG);
				flag = true;
				
			}

		}

		
		return map;

	}


}

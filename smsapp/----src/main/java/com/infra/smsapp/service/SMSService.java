package com.infra.smsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infra.smsapp.repository.SMSRepository;

@Service
public class SMSService {

	@Autowired
	SMSRepository smsrepo;

	int a = 0;

	public List<String> Hotccard(String mobile) {
		List<String> list = smsrepo.getdetalis(mobile);
		return list;
	}

	public boolean cardblock(List<String>list) {
		boolean flag = false;
		
		String MaskCardno=list.get(0);
		String Cardid=list.get(1);
		int a = smsrepo.hotcD390070(Cardid);
		int b = smsrepo.hotcD390060(Cardid);
		if (a == b) {
			
			
			flag = true;
		}

		return flag;

	}

	public boolean cardUnblock(List<String>list) {
		boolean flag = false;
		String MaskCardno=list.get(0);
		String Cardid=list.get(1);
		int a = smsrepo.UnhotcD390070(Cardid);
		int b = smsrepo.UnhotcD390060(Cardid);
		if (a == b) {
			flag = true;
		}
		
		return flag;

	}
}

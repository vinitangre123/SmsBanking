package com.infrasoft.smsapprequest.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendSMS {

	private static Logger logger = LogManager.getLogger(SendSMS.class);

	@Value("${ServiceURL}")
	private String serviceURL;

	@Value("${ServiceXML}")
	private String serviceXML;

	/*
	 * @Value("${msg}") private String msg;
	 */

	public String sendSMS(String mobileno, String Cardno,String msg,String Function) throws FileNotFoundException,Exception {

		logger.info("Send SMS Method Called");
		logger.info("Service URL : " + serviceURL);
		logger.info("Service XML : " + serviceXML);
		logger.info("Message : " + msg);
		
		String msgFinal=" ";
		if(Function.equalsIgnoreCase("UNHOTC"))
		{
			msgFinal=MessageFormat.format(msg,Cardno);
		}
		else if(Function.equalsIgnoreCase("HOTC"))
		{
			 msgFinal = msg;
		}
		
		String response="";
		logger.info("Final Message Created : " +msgFinal);
		if (!mobileno.startsWith("91")) {
			mobileno = "91" + mobileno;
		}
		try {
			HttpURLConnection conn = null;
			if (serviceURL == null || serviceXML.equals("")) {
				String sms = serviceURL.replaceFirst("textMSG", URLEncoder.encode(msgFinal, "UTF-8")).replaceFirst("mobnum", URLEncoder.encode(mobileno, "UTF-8"));
				URL url = new URL(sms);
				logger.info("Final serviceURL : " + url);
				//logger.info("Final SMSURL Called for Transaction Id : " + tranId + " & " + "Request Id : " + reqId);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.connect();
			} else {
				String data = serviceXML.replaceFirst("textMSG", URLEncoder.encode(msgFinal, "UTF-8")).replaceFirst("mobnum", URLEncoder.encode(mobileno, "UTF-8")).replace("+", "%20");
				URL url = new URL(serviceURL);
				logger.info("Final SMSURL-XML : " + url);
				logger.info("Final data : " + data);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.connect();
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data);
				wr.flush();
			
				//logger.info("Data Posted On Final SMSURL-XML for Transaction Id : " + tranId + " & " + " Request Id : " + reqId);
			}
					
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			//logger.info("Response From SMS Provider for  Transaction Id : " + tranId + " & " + " Request Id : " + reqId + " : " + buffer.toString());
			rd.close();
			conn.disconnect();
			response=buffer.toString();
		} catch (Exception ex) {
			//logger.info("Exception Occurred while sending SMS for Transaction Id : " + tranId + " & " + " Request Id : " + reqId + "- " + ex.getMessage());
		}
		return response;

	}

	public String sendSMSforlimit(String cardno, String msg,String amount,String mobileno) {
		// TODO Auto-generated method stub


		logger.info("Send SMS Method Called");
		logger.info("Service URL : " + serviceURL);
		logger.info("Service XML : " + serviceXML);
		logger.info("Message : " + msg);
		
		String msgFinal=MessageFormat.format(msg,cardno,amount);
	
		
		String response="";
		logger.info("Final Message Created : " +msgFinal);
		if (!mobileno.startsWith("91")) {
			mobileno = "91" + mobileno;
		}
		try {
			HttpURLConnection conn = null;
			if (serviceURL == null || serviceXML.equals("")) {
				String sms = serviceURL.replaceFirst("textMSG", URLEncoder.encode(msgFinal, "UTF-8")).replaceFirst("mobnum", URLEncoder.encode(mobileno, "UTF-8"));
				URL url = new URL(sms);
				logger.info("Final serviceURL : " + url);
				//logger.info("Final SMSURL Called for Transaction Id : " + tranId + " & " + "Request Id : " + reqId);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.connect();
			} else {
				String data = serviceXML.replaceFirst("textMSG", URLEncoder.encode(msgFinal, "UTF-8")).replaceFirst("mobnum", URLEncoder.encode(mobileno, "UTF-8")).replace("+", "%20");
				URL url = new URL(serviceURL);
				logger.info("Final SMSURL-XML : " + url);
				logger.info("Final data : " + data);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.connect();
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data);
				wr.flush();
			
				//logger.info("Data Posted On Final SMSURL-XML for Transaction Id : " + tranId + " & " + " Request Id : " + reqId);
			}
					
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			//logger.info("Response From SMS Provider for  Transaction Id : " + tranId + " & " + " Request Id : " + reqId + " : " + buffer.toString());
			rd.close();
			conn.disconnect();
			response=buffer.toString();
		} catch (Exception ex) {
			//logger.info("Exception Occurred while sending SMS for Transaction Id : " + tranId + " & " + " Request Id : " + reqId + "- " + ex.getMessage());
		}
		return response;

	
	}
}



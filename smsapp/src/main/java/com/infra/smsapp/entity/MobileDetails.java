package com.infra.smsapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "D390070")
public class MobileDetails {

	@Id
	@Column(name = "APPNO")
	private int appNo;

	@Column(name = "CARDNO")
	private String cardNo;

	@Column(name = "MOBILENO")
	private String mobileNo;

	@Column(name = "MASKCARDNO")
	private String maskCard;
	
	@Column(name = "CARDLIMIT")
	private float CardLimit;
	
	@Column(name = "CARDPOSLIMIT")
	private float CardPOSLimit;
	
	@Column(name = "CARDPOSLOCALLIMIT")
	private float CardPOSLocalLimit;
	
	@Column(name = "CARDATMLOCALLIMIT")
	private float CardATMLocalLimit;
	
	
	@Column(name = "CARDECOMLOCALLIMIT")
	private float CardECOMLocalLimit;


	public int getAppNo() {
		return appNo;
	}


	public void setAppNo(int appNo) {
		this.appNo = appNo;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getMaskCard() {
		return maskCard;
	}


	public void setMaskCard(String maskCard) {
		this.maskCard = maskCard;
	}


	public float getCardLimit() {
		return CardLimit;
	}


	public void setCardLimit(float cardLimit) {
		CardLimit = cardLimit;
	}


	public float getCardPOSLimit() {
		return CardPOSLimit;
	}


	public void setCardPOSLimit(float cardPOSLimit) {
		CardPOSLimit = cardPOSLimit;
	}


	public float getCardPOSLocalLimit() {
		return CardPOSLocalLimit;
	}


	public void setCardPOSLocalLimit(float cardPOSLocalLimit) {
		CardPOSLocalLimit = cardPOSLocalLimit;
	}


	public float getCardATMLocalLimit() {
		return CardATMLocalLimit;
	}


	public void setCardATMLocalLimit(float cardATMLocalLimit) {
		CardATMLocalLimit = cardATMLocalLimit;
	}


	public float getCardECOMLocalLimit() {
		return CardECOMLocalLimit;
	}


	public void setCardECOMLocalLimit(float cardECOMLocalLimit) {
		CardECOMLocalLimit = cardECOMLocalLimit;
	}


	@Override
	public String toString() {
		return "MobileDetails [appNo=" + appNo + ", cardNo=" + cardNo + ", mobileNo=" + mobileNo + ", maskCard="
				+ maskCard + ", CardLimit=" + CardLimit + ", CardPOSLimit=" + CardPOSLimit + ", CardPOSLocalLimit="
				+ CardPOSLocalLimit + ", CardATMLocalLimit=" + CardATMLocalLimit + ", CardECOMLocalLimit="
				+ CardECOMLocalLimit + "]";
	}
	

	
	

}

package com.infra.smsapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.infra.smsapp.entity.MobileDetails;

@Repository
@Transactional
public interface SMSRepository extends JpaRepository<MobileDetails, String> {
	
	@Query(value = "select * from D390070 a,D390060 b where a.CardNo=b.CardId and a.MobileNo=? and substring(a.MaskcardNo,13,4)=?", nativeQuery = true)
	MobileDetails getdetalis(String mobileNo,String maskCard );

	@Query(value = "SELECT a.MobileNo FROM D390070 a,D390060 b WHERE  a.CardNo=b.CardId   AND a.MobileNo =? AND b.Status=1", nativeQuery = true)
	List<String>  getdetalisCardblock(String mobileNo);

	@Query(value = "SELECT a.MaskcardNo FROM D390070 a,D390060 b WHERE  a.CardNo=b.CardId   AND a.MobileNo =? AND b.Status=1 ", nativeQuery = true)
	List<String>  getMaskedacrd(String mobileNo);

	
	@Modifying
	@Query(value = "update D390070  set Status=5 where CardNo in(SELECT a.CardNo FROM D390070 a,D390060 b WHERE  a.CardNo=b.CardId   AND a.MobileNo =? AND  b.Status=1) ", nativeQuery = true)
	public int hotcD390070(String mobileno);
	
	
	@Modifying
	@Query(value = "update D390060  set Status=5 where CardId in(SELECT a.CardNo FROM D390070 a,D390060 b WHERE  a.CardNo=b.CardId   AND a.MobileNo =? AND b.Status=1) ", nativeQuery = true)
	public int hotcD390060(String mobileno);
	
	
	
	/*
	 * @Modifying
	 * 
	 * @Query(value = "update D390070 set Status=2 where CardNo=? ", nativeQuery =
	 * true) public int hotcD390070(String cardNo);
	 */
	
	
	/*
	 * @Modifying
	 * 
	 * @Query(value = "update D390060 set Status=2 where CardId=? ", nativeQuery =
	 * true) public int hotcD390060(String cardNo);
	 */
	@Modifying
	@Query(value = "update D390070 set Status=1 where CardNo=? ", nativeQuery = true)
	public int UnhotcD390070(String cardNo);

	@Modifying
	@Query(value = "update D390060 set Status=1 where CardId=? ", nativeQuery = true)
	public int UnhotcD390060(String cardNo);
	
	@Modifying
	@Query(value = "update D390060 set IsATMLocal='Y',CardATMLocalLimit=? where CardId=? ", nativeQuery = true)
	public int LimitupdateAtmLocalD390060(float cardlimit,String cardNo);
	
	@Modifying
	@Query(value = "update D390060 set IsECOMLocal='Y',CardECOMLocalLimit=? where CardId=? ", nativeQuery = true)
	public int LimitupdateEcomLocalD390060(float cardlimit,String cardNo);
	
	@Modifying
	@Query(value = "update D390060 set IsPOSLocal='Y',CardPOSLocalLimit=? where CardId=? ", nativeQuery = true)
	public int LimitupdatePosLocalD390060(float cardlimit,String cardNo);




}

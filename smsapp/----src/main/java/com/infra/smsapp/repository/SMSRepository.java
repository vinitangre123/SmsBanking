package com.infra.smsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infra.smsapp.entity.MobileDetails;

@Repository
public interface SMSRepository extends JpaRepository<MobileDetails, String> {

	@Query(value = "select MaskcardNo,CardNo from D390070 where MobileNo=?", nativeQuery = true)
	public List<String> getdetalis(String MobileNo);

	@Modifying
	@Query(value = "update D390070 set Status=2 where CardNo=? ", nativeQuery = true)
	public int hotcD390070(String Cardid);

	@Modifying
	@Query(value = "update D390060 set Status=2 where CardId=? ", nativeQuery = true)
	public int hotcD390060(String Cardid);

	@Modifying
	@Query(value = "update D390070 set Status=1 where= CardNo? ", nativeQuery = true)
	public int UnhotcD390070(String Cardid);

	@Modifying
	@Query(value = "update D390060 set Status=1 where CardId=? ", nativeQuery = true)
	public int UnhotcD390060(String Cardid);
}

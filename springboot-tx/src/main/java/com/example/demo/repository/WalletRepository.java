package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

	@Query(value = "select balance from wallet where username =:username ", nativeQuery = true)
	Integer getWalletBalance(String username);
	
	// 更新餘額
		@Modifying
		@Transactional
		@Query(value = "update wallet set balance = balance - :bookPrice where username = :username", nativeQuery = true)
		void updateWalletBalance(Integer bookPrice, String username);
}

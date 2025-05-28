package com.example.demo.service;

import com.example.demo.exception.InsufficientAmountException;

public interface BookService {

	Integer getBookPrice(Integer bookId);
	
	Integer getBookAmount(Integer bookId);
	
	Integer getWalletBalance(String username);
	
	void reduceBookAmount(Integer bookId, Integer amountToReduce) throws InsufficientAmountException;
	
	void reduceWalletBalance(String username, Integer bookPrice) throws InsufficientAmountException;
}

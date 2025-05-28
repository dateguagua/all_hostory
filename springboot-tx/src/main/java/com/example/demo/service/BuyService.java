package com.example.demo.service;

import com.example.demo.exception.InsufficientAmountException;

public interface BuyService {

	void buyOneBook(String username, Integer bookId) throws InsufficientAmountException;
}

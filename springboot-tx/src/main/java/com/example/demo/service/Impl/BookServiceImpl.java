package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InsufficientAmountException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	public Integer getBookPrice(Integer bookId) {
		
		return bookRepository.getBookPrice(bookId);
	}

	@Override
	public Integer getBookAmount(Integer bookId) {
		// TODO Auto-generated method stub
		return stockRepository.getBookAmount(bookId);
	}

	@Override
	public Integer getWalletBalance(String username) {
		// TODO Auto-generated method stub
		return walletRepository.getWalletBalance(username);
	}

	@Override
	public void reduceBookAmount(Integer bookId, Integer amountToReduce) throws InsufficientAmountException {
		//檢查庫存
		Integer bookAmount = getBookAmount(bookId);
		if(bookAmount < amountToReduce) {
			throw new RuntimeException(String.format("bookId :%d 庫存不足 (%d < %d)%n", bookId, bookAmount, amountToReduce));
		}
		
		//更新庫存
		stockRepository.updateBookAmount(amountToReduce, bookId);
	}

	@Override
	public void reduceWalletBalance(String username, Integer bookPrice) throws InsufficientAmountException{
		// 1. 檢查餘額
				Integer walletBalance = getWalletBalance(username);
				if(walletBalance < bookPrice) {
					throw new RuntimeException(String.format("username: %s 餘額不足 (%d < %d)%n", username, walletBalance, bookPrice));
				}
				// 2. 更新餘額
				walletRepository.updateWalletBalance(bookPrice, username);
			}

}

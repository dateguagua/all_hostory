package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.InsufficientAmountException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.BuyService;



@Service //交易服務
public class BuyServiceImpl implements BuyService {
	
	@Autowired
	private BookService bookService;
	
	//RuntimeException 預設會回滾 可以透過dontRollbackOn改變
	//Exception 預設不會回滾 可以透過dontRollbackOn定義
	
	@Transactional(
			propagation = Propagation.REQUIRED, // 預設
			isolation = Isolation.DEFAULT, // 預設:使用資料庫預設
			rollbackFor = {InsufficientAmountException.class},
			noRollbackFor = {RuntimeException.class}
			)
	
	
	@Override
	public void buyOneBook(String username, Integer bookId) throws InsufficientAmountException{
		System.out.printf("%s 要買書%n", username);
		//查詢書本價格
		Integer bookPrice = bookService.getBookPrice(bookId);
		System.out.printf("bookId; %d, bookPrice: %d%n", bookId, bookPrice);
		//減庫存一本
		bookService.reduceBookAmount(bookId, 1); // 固定減去 1 本
		// 3. 修改餘額
		bookService.reduceWalletBalance(username, bookPrice);
		//結帳完成
		System.out.printf("%s 結帳完成%n", username);
		
	}

}

package com.teama.controller;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.teama.loan.service.LoanService;

@Component
public class MainScheduler {

	@Resource(name="loanService")
	private LoanService loanService;
	
	@Scheduled(cron="0 0/1 * * * *")
	public void autoLoan() {
		System.out.println("scheduled-autoLoan");
		
		loanService.autoReturn();
		loanService.autoLoan();
	}
}

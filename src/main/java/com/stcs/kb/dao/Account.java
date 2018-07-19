package com.stcs.kb.dao;

import lombok.Data;

public class Account {

	long accountId ;
	String id ;
	long tenantId;
	double nextInvoice;
	double balance;
	
	public Account(long accountId, String id) {
		super();
		this.accountId = accountId;
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTenantId() {
		return tenantId;
	}

	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	public double getNextInvoice() {
		return nextInvoice;
	}

	public void setNextInvoice(double nextInvoice) {
		this.nextInvoice = nextInvoice;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}

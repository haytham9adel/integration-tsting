package com.stcs.kb.service;

import java.util.List;
import java.util.stream.IntStream;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;
import org.skife.jdbi.v2.sqlobject.Transaction;

import com.stcs.kb.dao.Account;
import com.stcs.kb.dao.AccountDao;

public abstract class AccountService {
	
	@CreateSqlObject
	abstract AccountDao AccountDao();

	
	public List<Account> getBluevaultAccounts() {
		AccountDao accountDao = AccountDao();
		return accountDao.getBluevaultAcc();
	}
	
	@Transaction
	public void updateAccountDataToBluevaultTeanat(List<Account> blusvultAccounts ) {
		AccountDao accountDao = AccountDao();
		System.out.println( "no of updated Account: " + IntStream.of( accountDao.updateAccount(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated AccountHistory : " +IntStream.of( accountDao.updateAccountHistory(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated AuditLog:" + IntStream.of(accountDao.updateAuditLog(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated BlockingState:" + IntStream.of( accountDao.updateBlockingState(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated Bundles:" +IntStream.of( accountDao.updateBundles(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated CustomFields:" + IntStream.of( accountDao.updateCustomFields(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated CustomFieldsHistory:" +IntStream.of(  accountDao.updateCustomFieldsHistory(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated InvoiceItems:" + IntStream.of( accountDao.updateInvoiceItems(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated InvoicePayments:" + IntStream.of( accountDao.updateInvoicePayments(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated Invoices:" + IntStream.of( accountDao.updateInvoices(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated Payment:" + IntStream.of( accountDao.updatePayment(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentAttemptHistory:" + IntStream.of( accountDao.updatePaymentAttemptHistory(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentAttempts:" +IntStream.of(  accountDao.updatePaymentAttempts(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentHistory:" + IntStream.of( accountDao.updatePaymentHistory(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentMethods:" + IntStream.of( accountDao.updatePaymentMethods(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentMethodsHistory:" +IntStream.of(  accountDao.updatePaymentMethodsHistory(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentTransactionHistory:" +IntStream.of( accountDao.updatePaymentTransactionHistory(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated PaymentTransactions:" +IntStream.of( accountDao.updatePaymentTransactions(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated RolledUpUsage:" +IntStream.of( accountDao.updateRolledUpUsage(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated SubscriptionEvents:" +IntStream.of( accountDao.updateSubscriptionEvents(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated Subscriptions:" +IntStream.of( accountDao.updateSubscriptions(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated Tag:" +IntStream.of( accountDao.updateTag(blusvultAccounts.iterator()) ).sum() );
		System.out.println( "no of updated TagHistory:" +IntStream.of( accountDao.updateTagHistory(blusvultAccounts.iterator()) ).sum() );
	}
	
}

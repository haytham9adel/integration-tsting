package com.stcs.kb.dao;

import java.util.Iterator;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;



public abstract class AccountDao {

	@SqlQuery("select  id,  record_id  from accounts where payment_method_id is not null " )
	@Mapper(AccountMapper.class)
	public abstract List<Account> getBluevaultAcc();
	
	@SqlBatch("update accounts set tenant_record_id = :tenantId where record_id =:accountId  ")
	public abstract int[] updateAccount(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update account_history set tenant_record_id = :tenantId where target_record_id =:accountId  ")
	public abstract int[] updateAccountHistory(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update audit_log set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public abstract int[] updateAuditLog(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update blocking_states set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public abstract int[] updateBlockingState(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update subscriptions set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateSubscriptions(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update subscription_events set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateSubscriptionEvents(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update bundles set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateBundles(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update invoice_items set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateInvoiceItems(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update invoices set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateInvoices(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update invoice_payments set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateInvoicePayments(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update payment_attempts set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentAttempts(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update payment_attempt_history set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentAttemptHistory(@BindBean Iterator<Account> accounts   );

	@SqlBatch("update payment_methods set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentMethods(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update payment_method_history set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentMethodsHistory(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update payments set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePayment(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update payment_history set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentHistory(@BindBean Iterator<Account> accounts   );	
	
	@SqlBatch("update payment_transactions set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentTransactions(@BindBean Iterator<Account> accounts   );

	@SqlBatch("update payment_transaction_history set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updatePaymentTransactionHistory(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update rolled_up_usage set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateRolledUpUsage(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update custom_fields set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateCustomFields(@BindBean Iterator<Account> accounts   );
	
	@SqlBatch("update custom_field_history set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateCustomFieldsHistory(@BindBean Iterator<Account> accounts   );	
	
	@SqlBatch("update tags set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateTag(@BindBean Iterator<Account> accounts   );

	@SqlBatch("update tag_history set tenant_record_id = :tenantId where account_record_id =:accountId  ")
	public  abstract int[] updateTagHistory(@BindBean Iterator<Account> accounts   );
	
}

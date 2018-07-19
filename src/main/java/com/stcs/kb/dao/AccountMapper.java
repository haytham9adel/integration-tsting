package com.stcs.kb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;



public class AccountMapper implements ResultSetMapper<Account> {

	@Override
	public Account map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Account(r.getLong("record_id"), r.getString("id") );
    }
	
}

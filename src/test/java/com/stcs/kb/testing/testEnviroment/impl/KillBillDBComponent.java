package com.stcs.kb.testing.testEnviroment.impl;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.stcs.kb.testing.testEnviroment.AbstractDockerComponent;

public class KillBillDBComponent extends AbstractDockerComponent {

	private String machine = "localhost";
	// docker run -p 15672:15672 -p 5672:5672 rabbit-integration1
	private String dbNameKB = "killbill";
	private String usernameKB = "killbill";
	private String passwordKB = "killbill";

	private String dbNamePlugins = "killbill_plugins";
	private String usernamePlugins = "killbill";
	private String passwordPlugins = "killbill";

	private String dbNameBilling = "billing_service_db";
	private String usernameBilling = "billing_service";
	private String passwordBilling = "billing_service";

	public KillBillDBComponent(String netwrokName) {
		super(netwrokName);
		this.dockerImage = "bill_it_db";
		this.port = 5432;
		this.name = "dbnode ";

	}

	@Override
	public boolean isUpAndRunning() {
		try {
			DBI dbi = new DBI("jdbc:postgresql://localhost:" + port + "/" + dbNameKB, usernameKB, passwordKB);
			Handle handle = dbi.open();
			return handle != null;
		} catch (Exception e) {
			System.out.println(name + "  dont running yet : " + e.getMessage());
			return false;
		}
	}

}

package com.stcs.kb.testing.testEnviroment.impl;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.KBBillingImpl;
import com.stcs.kb.testing.testEnviroment.AbstractComponent;

public class TomcatNode extends AbstractComponent {

	private String name = "kbnode ";
	private String path = "/Users/haythamzamek/Documents/STC/billing/development/kb-18-tomcat/apache-tomcat-8.0.33/bin";
	private String startCommand = "/startup.sh";
	private String closeCommand = "/shutdown.sh";

	@Override
	public boolean start() {
		try {
			ProcessBuilder builder = new ProcessBuilder(path + startCommand);
			builder.redirectErrorStream(true);
			System.out.println("command > " + path + startCommand);
			builder.start();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		try {
			ProcessBuilder builder = new ProcessBuilder(path + closeCommand);
			builder.redirectErrorStream(true);
			System.out.println("command > " + path + closeCommand);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean isComponentUpAndRunning() {
		try {
			KBBillingImpl cartwheelKB = new KBBillingImpl(Constants.KB_CARTWHEEL_KEY,
					Constants.KB_CARTWHEEL_SECRET, 1000);
			cartwheelKB.cleanCache();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}

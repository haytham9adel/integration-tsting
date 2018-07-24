package com.stcs.kb.testing.testEnviroment.impl;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.KBBillingImpl;
import com.stcs.kb.testing.testEnviroment.AbstractDockerComponent;

public class KillBillComponent extends AbstractDockerComponent {

	public KillBillComponent(String netwrokName) {
		super(netwrokName);
		name = "kbnode ";
		port = 8080 ;
		dockerImage = "killbill" ;
	}

	@Override
	public boolean isUpAndRunning() {
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

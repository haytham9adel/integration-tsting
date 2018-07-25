package com.stcs.kb.testing.testEnviroment.impl;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.KBBillingImpl;
import com.stcs.kb.testing.testEnviroment.AbstractDockerComponent;

import lombok.extern.log4j.Log4j2;
@Log4j2
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
			log.info(name + " is not running yet : " + e.getMessage() );
			return false;
		}
		return true;
	}

}

package com.stcs.kb.util;

public class ConcuencyUtil {

//public static void pause(long seconds) {
//		final CountDownLatch done = new CountDownLatch(1);
//        try {
//			done.await(seconds, TimeUnit.SECONDS) ;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//}
	
	public static void pause(long seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}
}

package com.stcs.kb.testing.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class AbstractDockerComponent extends AbstractComponent {

	@Override
	public boolean start()  {
		try { 
		   Runtime rt = Runtime.getRuntime();
		   String command = "docker run "
		           +  ( (managmentPort==0)?"":" -p "+managmentPort +":"+managmentPort )
				   + (" -p "+port +":"+port  )
				   + " " + dockerImage  ;
		    System.out.println("command > " +command);
		    rt.exec( command );
		    
		}catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
		return true;
	}

	@Override
	public boolean stop()  {
		try { // | xargs -I {} docker kill {}
			Runtime rt = Runtime.getRuntime();
		    String[] cmd = { "/bin/sh", "-c", "docker ps -a | grep "+dockerImage+" | awk '{print $1 }' " };
		    System.out.println("command > " +cmd);
		    Process proc =  rt.exec(cmd );
		    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		    String s = null;
		    while ((s = stdInput.readLine()) != null) {
		    	 rt.exec("docker rm -f "+s);
			}
		    
		}catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
		return true;
	}


	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		return false;
	}

	

}

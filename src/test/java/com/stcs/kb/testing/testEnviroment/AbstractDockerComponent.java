package com.stcs.kb.testing.testEnviroment;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class AbstractDockerComponent extends AbstractComponent {

	protected String netwrokName  ;
	
	public AbstractDockerComponent(String netwrokName) {
		this.netwrokName = netwrokName ;
	}
	
	
	@Override
	public boolean start()  {
		try { 
		   Runtime rt = Runtime.getRuntime();
		   String command = "docker run "
		           +  ( (managmentPort==0)?"":" -p "+managmentPort +":"+managmentPort )
				   + (" -p "+port +":"+port  )
				   + " --name "+name
				   + " -d --network "+netwrokName
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


	

	

}

package com.stcs.kb.testing.testEnviroment;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class AbstractDockerComponent extends AbstractComponent {

	protected String networkName;

	public AbstractDockerComponent(String networkName) {
		this.networkName = networkName;
	}

	@Override
	public boolean start() {
		try {
			new DockerImageBuilder().port(port).managmentPort(managmentPort).name(name).networkName(networkName)
					.dockerImage(networkName).run();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
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

		} catch (Exception e) {
			return false;
		}
		return true;
	}

}

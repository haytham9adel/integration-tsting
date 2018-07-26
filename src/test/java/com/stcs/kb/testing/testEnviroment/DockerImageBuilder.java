package com.stcs.kb.testing.testEnviroment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DockerImageBuilder {

	private String name;
	private int port;
	private int managmentPort;
	private String dockerImage;
	private String networkName;
	private StringBuilder runCommandBuilder = new StringBuilder("docker run ");

	public DockerImageBuilder name(final String name) {
		this.name = name;
		return this;
	}

	public DockerImageBuilder port(final int port) {
		this.port = port;
		return this;
	}

	public DockerImageBuilder managmentPort(final int managmentPort) {
		this.managmentPort = managmentPort;
		return this;
	}

	public DockerImageBuilder dockerImage(final String dockerImage) {
		this.dockerImage = dockerImage;
		return this;
	}

	public DockerImageBuilder networkName(final String networkName) {
		this.networkName = networkName;
		return this;
	}

	public void start() throws IOException {
		runCommandBuilder.append(" --name ").append(name).append(" -p ").append(port).append(":").append(port);
		if (managmentPort != 0) {
			runCommandBuilder.append(" -p ").append(managmentPort).append(":").append(managmentPort);
		}
		runCommandBuilder.append(" -d --network ").append(networkName).append(" ").append(dockerImage);

		log.info("Starting docker image : {} with command {}", dockerImage, runCommandBuilder.toString());
		Runtime.getRuntime().exec(runCommandBuilder.toString());

	}

	public boolean stop()  {
		try { 
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

package com.stcs.kb.testing.testEnviroment;

import java.io.IOException;

public class DockerImageBuilder {

	private String name;
	private int port;
	private int managmentPort;
	private String dockerImage;
	private String networkName;
	private Runtime runtime = Runtime.getRuntime();
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

	public void run() throws IOException {
		runCommandBuilder.append(" --name ").append(name).append(" -p ").append(port).append(":").append(port);
		if (managmentPort != 0) {
			runCommandBuilder.append(" -p ").append(managmentPort).append(":").append(managmentPort);
		}
		runCommandBuilder.append(" -d --network ").append(networkName).append(" ").append(dockerImage);
		runtime.exec(runCommandBuilder.toString());

	}

}

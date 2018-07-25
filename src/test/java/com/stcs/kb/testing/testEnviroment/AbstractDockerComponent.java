package com.stcs.kb.testing.testEnviroment;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractDockerComponent extends AbstractComponent {

	protected String networkName;
	private DockerImageBuilder dockerImageBuilder;

	public AbstractDockerComponent(String networkName) {
		this.networkName = networkName;
		dockerImageBuilder = new DockerImageBuilder();
	}

	@Override
	public boolean start() {
		try {
			dockerImageBuilder.port(port).managmentPort(managmentPort).name(name).networkName(networkName)
					.dockerImage(dockerImage).start();
		} catch (Exception e) {
			log.error("Couldn't start docker image : {} due to exception {}", dockerImage , e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean stop() {
		try { 
			dockerImageBuilder.stop();
		} catch (Exception e) {
			log.error("Couldn't stop docker image : {} due to exception {}", dockerImage , e.getMessage());
			return false;
		}
		return true;
	}

}

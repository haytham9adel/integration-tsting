package com.stcs.kb.testing.testEnviroment;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * An abstract class implements {@link com.stcs.kb.testing.testEnviroment.Component}, it is the base class
 * of any component that will be used in integration testing
 * 
 * 
 * @author Haytham.Adel
 */

public abstract class AbstractComponent implements Component {
     
	protected int    port ;
	protected int    managmentPort ;
	protected String dockerImage ;
	protected String name ;
	protected List<Component> dependantComponent = new ArrayList<>();
	
	@Override
	public List<Component> getDepndantComponents() {
		return dependantComponent;
	}
	
	@Override
	public Component addDepndantComponent(Component depndant) {
		dependantComponent.add(depndant);
		return this;
	}

	@Override
	public abstract boolean start()    ;

	@Override
	public abstract boolean stop()   ;

	@Override
	public abstract boolean isUpAndRunning( )   ;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getManagmentPort() {
		return managmentPort;
	}
	public void setManagmentPort(int managmentPort) {
		this.managmentPort = managmentPort;
	}
	public String getDockerImage() {
		return dockerImage;
	}
	public void setDockerImage(String dockerImage) {
		this.dockerImage = dockerImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String hostname) {
		this.name = hostname;
	}	
	
	
}

package com.stcs.kb.testing.component;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent implements Component {
     
	protected int    port ;
	protected int    managmentPort ;
	protected String dockerImage ;
	protected String name ;
	protected List<Component> dependantComponent = new ArrayList<>();
	
	@Override
	public List<Component> getDepndantComponent() {
		// TODO Auto-generated method stub
		return dependantComponent;
	}
	/* (non-Javadoc)
	 * @see com.stcs.kb.testing.component.Component#start()
	 */
	@Override
	public abstract boolean start()    ;
	/* (non-Javadoc)
	 * @see com.stcs.kb.testing.component.Component#stop()
	 */
	@Override
	public abstract boolean stop()   ;
	public abstract boolean init()   ;	
	/* (non-Javadoc)
	 * @see com.stcs.kb.testing.component.Component#isComponentUpAndRunning()
	 */
	@Override
	public abstract boolean isComponentUpAndRunning( )   ;
	
	
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

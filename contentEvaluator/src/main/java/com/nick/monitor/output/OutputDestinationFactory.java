package com.nick.monitor.output;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OutputDestinationFactory {

	public static enum OutputType {
		// POC examples of extension 
		EMAIL, FTP, JMS, FILE, WEB_SOCKET
	}
	
	@Autowired
	@Qualifier("emailOutput")
	private OutputDestination<String> emailOutput;
	
	@Autowired
	@Qualifier("ftpOutput")
	private OutputDestination<String> ftpOutput;
	
	private Map<OutputType, OutputDestination<? extends Serializable>> map = new HashMap<>();
	
	protected void init() {
		map.put(OutputType.EMAIL, emailOutput);
		map.put(OutputType.FTP, ftpOutput);
	}
	public <T extends Serializable> OutputDestination<T> getOutputDestination(OutputType outputType) {
			return (OutputDestination<T>) map.get(outputType);
	}
}

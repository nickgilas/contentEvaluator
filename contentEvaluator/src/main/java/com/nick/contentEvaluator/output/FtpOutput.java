package com.nick.contentEvaluator.output;

import org.springframework.stereotype.Component;

@Component
public class FtpOutput extends BaseOutputDestination<String> {

	@Override
	protected void send(String contents) {
		logger.info("Pretenting to send ftp upload for contents: " + contents);
		// No-Op since this is a POC the integration isn't required. It illustrates how to extend the framework by adding a new classes
	}

}

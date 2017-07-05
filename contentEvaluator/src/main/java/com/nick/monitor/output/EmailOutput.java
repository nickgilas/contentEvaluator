package com.nick.monitor.output;

import org.springframework.stereotype.Component;

/**
 *  Used to send emails
 *  
 * @author Nick
 *
 */
@Component
public class EmailOutput extends BaseOutputDestination<EmailParams> {

	@Override
	public void send(EmailParams contents) {
		logger.info("Pretenting to send email with contents: " + contents);
		// No-Op since this is a POC the integration isn't required. It illustrates how to extend the framework by adding a new classes
	}
	
}

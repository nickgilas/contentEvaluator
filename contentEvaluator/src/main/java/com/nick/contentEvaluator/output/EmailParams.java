package com.nick.contentEvaluator.output;

import java.io.Serializable;

/**
 * Domain object used to hold all parms needed 
 * to send an email
 * 
 * @author Nick Gilas
 *
 */
public class EmailParams implements Serializable {
	
	private static final long serialVersionUID = -8724220213465499260L;
	
	private String to;
	private String from;
	private String body;
	private String subject;
	
	
	public EmailParams(String to, String from, String body, String subject) {
		super();
		this.to = to;
		this.from = from;
		this.body = body;
		this.subject = subject;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}	
}

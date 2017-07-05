package com.nick.monitor.contentEvaluators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentEvaluatorFactory {

	@Autowired
	private ContentEvaluator<String> stringEvaluator;
	
	public ContentEvaluator<String> getStringContentEvaluator() {
		return stringEvaluator;
	}
}

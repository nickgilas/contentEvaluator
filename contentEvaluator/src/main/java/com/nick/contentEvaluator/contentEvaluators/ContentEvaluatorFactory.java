package com.nick.contentEvaluator.contentEvaluators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ContentEvaluatorFactory {

	@Autowired
	@Qualifier("stringContentEvaluator")
	private ContentEvaluator<String> stringEvaluator;
	
	public ContentEvaluator<String> getStringContentEvaluator() {
		return stringEvaluator;
	}
}

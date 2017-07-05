package com.nick.monitor.contentEvaluators;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringContentEvaluator implements ContentEvaluator<String> {

	private static Logger logger = LoggerFactory.getLogger( StringContentEvaluator.class );
	
	public boolean isContentDifferent(String content1, String content2, Integer diffThresholdPercent) {
		
		Boolean validationResult = validateInput(content1, content2);
		if (validationResult != null) {
			logger.debug("Validation resulted were: " + validationResult);
			return validationResult;
		}
		
		float contentLength = (float) content1.length();
		
		String diffText= StringUtils.difference(content1, content2);
		
		if (StringUtils.isEmpty(diffText)) {
			// diff from the other direction to get both angles of data
			diffText = StringUtils.difference(content2, content1);			
		}
	
		float diffLength =  diffText.length() / contentLength;
		
		// convert to percent
		diffLength = diffLength * 100;
		
		logger.trace("Diff lenght: " + diffLength);

		boolean result = diffLength > diffThresholdPercent;
		logger.debug("Has content changed: " + result);
		
		return result;

	}

	private Boolean validateInput(String content1, String content2) {
		if (StringUtils.isBlank(content1) && StringUtils.isBlank(content2)) {
			return false;
		} else if (StringUtils.isBlank(content1) && !StringUtils.isBlank(content2)) { 
			return true;
		} else if (StringUtils.isBlank(content2) && !StringUtils.isBlank(content1)) {
			return true;
		}
		return null;
	}
}

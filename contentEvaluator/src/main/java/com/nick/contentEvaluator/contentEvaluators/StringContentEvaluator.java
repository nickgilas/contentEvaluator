package com.nick.contentEvaluator.contentEvaluators;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This evaluator implementation compares 2 strings and calculates the
 * differences. If the the difference surpasses a predetermined threshold then
 * it will return True.
 * 
 * @author Nick Gilas
 *
 */
@Component
public class StringContentEvaluator implements ContentEvaluator<String> {

	private static Logger logger = LoggerFactory.getLogger( StringContentEvaluator.class );
	
	public boolean isContentDifferent(String content1, String content2, Integer diffThresholdPercent) {
		
		Boolean validationResult = validateInput(content1, content2);
		if (validationResult != null) {
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
		
		logger.debug("Diff lenght: " + diffLength);

		boolean result = diffLength >= diffThresholdPercent;
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

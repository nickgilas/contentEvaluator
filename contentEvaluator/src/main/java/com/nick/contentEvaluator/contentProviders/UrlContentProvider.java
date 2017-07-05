package com.nick.contentEvaluator.contentProviders;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This implementation can read the contents of remote URI data and return a String version of the content
 * 
 * @author Nick Gilas
 *
 */
@Component
public class UrlContentProvider implements ContentProvider<URL> {

	private static Logger logger = LoggerFactory.getLogger( UrlContentProvider.class );
	
	private static final Charset CHAR_SET = Charset.forName("UTF-8");
	
	@Override
	public  String getContents(URL url) {

		if (url == null) {
			logger.warn("Input url is null");
			return null;
		}
		
		if (url.getFile().isEmpty()) {
			logger.warn("Input url has no content");
			return null;
		}
		
		// Load content in-memory (might need to output to another output resource if huge sites are to be loaded)
		StringBuilder content = new StringBuilder();
		try (BufferedInputStream input =  (BufferedInputStream) url.openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input, CHAR_SET))) {

			reader.lines().forEach(l -> content.append(l));
			return content.toString();
			
		} catch (Exception e) {
			logger.error("Unknown error occurred while trying to read the url contents: " + e.getMessage(), e);
			return null;
		}

	}
}

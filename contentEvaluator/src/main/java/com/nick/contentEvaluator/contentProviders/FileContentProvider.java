/**
 * 
 */
package com.nick.contentEvaluator.contentProviders;

import java.io.File;
import java.io.Serializable;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nick.contentEvaluator.contentProviders.ContentProviderFactory.ContentType;

/**
 * 
 * This implementation supports retrieving contents from a File. It  reuses 
 * an instance of {@link UrlContentProvider} to pull the contents since 
 * they are exactly the same logic. All this 
 * class does is convert the {@link File} object into a {@link URL}
 *
 * @author Nick Gilas
 *
 */
@Component
public class FileContentProvider implements ContentProvider<File> {
	private static Logger logger = LoggerFactory.getLogger( FileContentProvider.class );
	
	@Autowired
	private ContentProviderFactory contentProviderFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> T getContents(File file) {
		
		try {
			
			ContentProvider<URL> urlProvider = (ContentProvider<URL>) contentProviderFactory.getContentProvider(ContentType.URL);
			
			return urlProvider.getContents(file.toURI().toURL());
			
		} catch (Exception e) {
			logger.error("Unknown error occurred while trying to retrieve contens from file: " + file + " msg: " +  e.getMessage(), e);
			return null;
		}
	}
}

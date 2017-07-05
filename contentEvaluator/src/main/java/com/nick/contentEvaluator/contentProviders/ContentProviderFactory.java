package com.nick.contentEvaluator.contentProviders;

import org.springframework.stereotype.Component;

/**
 * This class utilizes both the singleton and factory patterns to create a single point 
 * of entry to retrieve concrete implementations of {@link ContentProvider}'s which
 * are related to a single {@link ContentType}. 
 * 
 * @author Nick Gilas
 *
 */
@Component
public class ContentProviderFactory {
	
	/*
	 * Holds the relationship between a ContentType 
	 * and it's implemented ContentProvider.
	 */
	public static enum ContentType {
		
		URL(new UrlContentProvider()), 
		FILE(new FileContentProvider());
		
		private ContentProvider provider;
		
		private ContentType(ContentProvider provider) {
			this.provider = provider;
		}
		
		protected ContentProvider getProvider() {
			return provider;
		}
	}
	
	public ContentProvider getContentProvider(ContentType type) {
		return type.getProvider();
	}
	
}

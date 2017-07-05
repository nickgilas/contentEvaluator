package com.nick.monitor;

import java.net.URL;

import org.junit.Test;

import com.nick.monitor.contentProviders.ContentProvider;
import com.nick.monitor.contentProviders.ContentProviderFactory;
import com.nick.monitor.contentProviders.ContentProviderFactory.ContentType;

/**
 * Purpose of this class is to test the core functionality 
 * of the individual content evaluators and the content providers.
 * Hence, Spring isn't utilized and the timer functionality isn't enabled.
 * 
 * @author Nick Gilas
 *
 */
public class IntegrationTest {

	@SuppressWarnings("unchecked")
	@Test
	public void test() throws Exception {
		
		ContentEvaluatorApplication app = new ContentEvaluatorApplication();
		
		app.start();
		
		ContentProvider<URL> provider = (ContentProvider<URL>) new ContentProviderFactory().getContentProvider(ContentType.URL);
		
		// Load test data
		URL url = ContentEvaluatorApplication.class.getClassLoader().getResource("site.html");
		
		String originalContent = provider.getContents(url);
		// wait for timer to execute and evalute the content again

	}

}

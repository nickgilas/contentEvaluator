package com.nick.monitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.nick.monitor.contentProviders.ContentProvider;
import com.nick.monitor.contentProviders.ContentProviderFactory;
import com.nick.monitor.contentProviders.ContentProviderFactory.ContentType;
import com.nick.monitor.contentProviders.UrlContentProvider;

public class UrlContentProviderTest {

	private ContentProvider<URL> provider;
	private static URL contentUrl;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		provider = (ContentProvider<URL>) new ContentProviderFactory().getContentProvider(ContentType.URL);		
		contentUrl = UrlContentProvider.class.getClassLoader().getResource("site.html");
	}

	@Test
	public void testGetContents_bad_url() throws Exception {
		
		assertNull(provider.getContents(new URL("http://somBadUrl!")));
	}
	
	@Test
	public void testGetContents_no_content() {
		
		assertNull(provider.getContents(null));
	}
	
	@Test
	public void testGetContents_no_diff() {
		
		// Sanity test
		String content1 = provider.getContents(contentUrl);
		String content2 = provider.getContents(contentUrl);
		
		assertEquals(content1, content2);
	
	}

}

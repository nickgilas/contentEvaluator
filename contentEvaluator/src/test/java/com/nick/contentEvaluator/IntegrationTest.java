package com.nick.contentEvaluator;

import java.io.File;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test that starts up Spring within a test contents and associated
 * test property data. It will do an end to end test of evaluating an html test
 * file and ensure everything is working as expected
 * 
 * @author Nick Gilas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class })
@TestPropertySource("classpath:test.properties")
public class IntegrationTest {

	private static Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

	@Autowired
	private TestAppConfig testAppConfig;

	@Autowired
	private ContentEvaluatorApplication app;

	private File site1File;
	private File site2File;

	@Test(timeout = 10000)
	public void test() throws Exception {
		
		try {

			URL site1Url = getUrlFromResource("site1.html");
			site1File = new File(site1Url.getFile());

			URL site2Url = getUrlFromResource("site2.html");
			site2File = new File(site2Url.getFile());

			app.start(site1Url);

			// wait for timer to execute the product and then change it's
			// underlying website data
			Thread.sleep(2500);

			switchFileData(site1Url, site2Url);

			logger.info("Renamed files");

			// wait for another execution (2 seconds) to complete and verify the
			// results
			Thread.sleep(3000);

			// assert that the email output was called because of the content
			// changed diff
			Mockito.verify(testAppConfig.emailOutput(), Mockito.times(1)).sendContents(Mockito.any());

			logger.info("Finished executing the integration test");
		} finally {
			// reset test data
			renameFiles();
		}
		
	}

	private void switchFileData(URL site1Url, URL site2Url) {
		File site1File = new File(site1Url.getFile());

		site1File.renameTo(new File(site1File.getParent(), "site1.html.tmp"));

		File site2File = new File(site2Url.getFile());

		site2File.renameTo(new File(site2File.getParent(), "site1.html"));
	}

	private void renameFiles() {
		new File(site2File.getParent(), "site1.html").renameTo(new File(site1File.getParent(), "site2.html"));
		new File(site1File.getParent(), "site1.html.tmp").renameTo(new File(site1File.getParent(), "site1.html"));
	}

	private URL getUrlFromResource(String name) {
		return ContentEvaluatorApplication.class.getClassLoader().getResource(name);
	}

}

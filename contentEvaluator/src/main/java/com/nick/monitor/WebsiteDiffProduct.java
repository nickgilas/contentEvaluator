package com.nick.monitor;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nick.monitor.contentEvaluators.ContentEvaluator;
import com.nick.monitor.contentEvaluators.ContentEvaluatorFactory;
import com.nick.monitor.contentProviders.ContentProvider;
import com.nick.monitor.contentProviders.ContentProviderFactory;
import com.nick.monitor.contentProviders.ContentProviderFactory.ContentType;
import com.nick.monitor.output.EmailParams;
import com.nick.monitor.output.OutputDestination;
import com.nick.monitor.output.OutputDestinationFactory;
import com.nick.monitor.output.OutputDestinationFactory.OutputType;

/**
 * This product is used to monitor content for any
 * {@link URL}. If a change is detected and the amount 
 * of content change is greater then and input percent
 * then an email will be sent to a user.
 * 
 * @author Nick
 *
 */
@Component
@Scope(scopeName = "prototype")
public class WebsiteDiffProduct implements Product {
	private static Logger logger = LoggerFactory.getLogger(WebsiteDiffProduct.class);

	private ContentProvider<URL> provider;
	private ContentEvaluator<String> evaluator;
	private OutputDestination<EmailParams> emailOutput;

	@Autowired
	private ContentProviderFactory providerFactory;

	@Autowired
	private ContentEvaluatorFactory evaluatorFactory;

	@Autowired
	private OutputDestinationFactory outputFactory;
	
	@Value("${diff.threashold.percent}")
	private Integer diffThresholdPercent;

	private URL url;
	private boolean isInit = false;
	private String cachedContent;
	

	@SuppressWarnings("unchecked")
	public void init(URL siteUrl) {

		this.url = siteUrl;
		provider = (ContentProvider<URL>) providerFactory.getContentProvider(ContentType.URL); 
		evaluator = evaluatorFactory.getStringContentEvaluator();
		emailOutput = outputFactory.getOutputDestination(OutputType.EMAIL);
		isInit = true;
	}
	
	@Override
	public void run() {
	
		if (!isInit) {
			logger.warn("Product is not initialized, call the init() method first");
			return;
		}

		String currentContent = provider.getContents(url);
		if (evaluator.isContentDifferent(cachedContent, currentContent, diffThresholdPercent)) {
			emailOutput.sendContents(buildEmailParams("Alert - Website has changed more then: %" + diffThresholdPercent));
		}
		
		// update cache
		cachedContent = currentContent;
	}
	
	private EmailParams buildEmailParams(String msg) {
		return new EmailParams("to@test.com", "from@test.com", 
				"The content for your interested site has been altered more then the threashold", 
				"Site Content Has Changed");
	}
}

package com.nick.contentEvaluator.products;

import java.io.Serializable;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nick.contentEvaluator.contentEvaluators.ContentEvaluator;
import com.nick.contentEvaluator.contentEvaluators.ContentEvaluatorFactory;
import com.nick.contentEvaluator.contentProviders.ContentProvider;
import com.nick.contentEvaluator.contentProviders.ContentProviderFactory;
import com.nick.contentEvaluator.contentProviders.ContentProviderFactory.ContentType;
import com.nick.contentEvaluator.output.EmailParams;
import com.nick.contentEvaluator.output.OutputDestination;
import com.nick.contentEvaluator.output.OutputDestinationFactory;
import com.nick.contentEvaluator.output.OutputDestinationFactory.OutputType;

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
	private static final Logger logger = LoggerFactory.getLogger(WebsiteDiffProduct.class);

	@Autowired
	private ContentProviderFactory providerFactory;

	@Autowired
	private ContentEvaluatorFactory evaluatorFactory;

	@Autowired
	private OutputDestinationFactory outputFactory;

	@Value("${diff.threashold.percent}")
	private Integer diffThresholdPercent;

	private ContentProvider<URL> provider;
	private ContentEvaluator<String> evaluator;
	private OutputDestination<EmailParams> emailOutput;

	private URL url;
	private boolean isInit = false;
	private String cachedContent;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> void init(T siteUrl) {

		this.url = (URL) siteUrl;
		provider = (ContentProvider<URL>) providerFactory.getContentProvider(ContentType.URL);
		evaluator = evaluatorFactory.getStringContentEvaluator();
		emailOutput = outputFactory.getOutputDestination(OutputType.EMAIL);

		// prime the cache
		cachedContent = provider.getContents(url);
		isInit = true;
	}
	

	@Override
	public void run() {
		logger.debug("Starting to execute product: " + getClass().getName());

		if (!isInit) {
			logger.warn("Product is not initialized, call the init() method first");
			return;
		}

		String currentContent = provider.getContents(url);
		if (evaluator.isContentDifferent(cachedContent, currentContent, diffThresholdPercent)) {
			logger.debug("Content has changed, sending output");
			emailOutput.sendContents(buildEmailParams("Alert - Website has changed more then: %" + diffThresholdPercent));
		} else {
			logger.debug("Content has not changed");
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

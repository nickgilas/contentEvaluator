package com.nick.monitor;

import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

//  https://github.com/nickgilas/contentEvaluator
/**
 * Write an application and applicable unit test that polls a URL at a certain 
 * interval and sends an email to someone if more than 10% of the content on 
 * the page has changed. The intent is that this application can be used in a 
 * wide variety of use cases: monitoring a changelog online, watching for when 
 * tickets an event become available etc. Please ask any questions you like 
 * and searching online is encouraged."
 *  
 * @author Nick Gilas
 * @since June 29th, 2017
 *
 */
@Component
public class ContentEvaluatorApplication {

	private static Logger logger = LoggerFactory.getLogger( ContentEvaluatorApplication.class );
	
	@Autowired
	private WebsiteDiffProduct product;

	@Value("${scheduled.worker.num.of.threads}")
	private Integer scheduledWorkerNumOfThreads;

	@Value("${interval.check.time.seconds}")
	private Integer intervalCheckTimeSeconds;
	
	public void start() {
	
		try {	
			logger.debug("Starting to run {}", ContentEvaluatorApplication.class.getName());
			
			// Note: in a final production version of the application this is
			// where logic should to load products with customer specific data
			// and schedule the product to execute

			ScheduledExecutorService executor = Executors.newScheduledThreadPool(scheduledWorkerNumOfThreads);
		
			// create all of the content evaluators and schedule their execution
			product.init(new URL("site1.html"));

			executor.scheduleWithFixedDelay(product, 1, intervalCheckTimeSeconds, TimeUnit.SECONDS);
			
		} catch (Throwable t) {
			logger.error("Error occurred while processing content: " + t.getMessage(), t);
			System.exit(-1);
		}
	}
	public static void main(String[] args) {
		
		System.out.println("Starting application");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ContentEvaluatorApplication app = (ContentEvaluatorApplication) context.getBean("contentEvaluatorApplication");
		app.start();
	}
}

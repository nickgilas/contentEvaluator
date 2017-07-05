package com.nick.contentEvaluator.products;

import java.io.File;
import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nick.contentEvaluator.contentEvaluators.ContentEvaluator;
import com.nick.contentEvaluator.contentProviders.ContentProvider;
import com.nick.contentEvaluator.output.EmailParams;
import com.nick.contentEvaluator.output.OutputDestination;

/**
 * POC example of extending the framework for new product that are composed of
 * the various components
 * 
 * @author Nick
 *
 */
@Component
@Scope(scopeName = "prototype")
public class FileDiffProduct implements Product {

	private ContentProvider<File> provider;
	private ContentEvaluator<String> evaluator;
	private OutputDestination<EmailParams> output;
	private File file;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Serializable> void init(T file) {

	}

	@Override
	public void run() {
		// Implement
	}
}

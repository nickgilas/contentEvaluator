package com.nick.contentEvaluator.products;

import java.io.Serializable;

import com.nick.contentEvaluator.contentEvaluators.ContentEvaluator;
import com.nick.contentEvaluator.contentProviders.ContentProvider;
import com.nick.contentEvaluator.output.OutputDestination;

/**
 * Products are a pre-configured combination of concrete
 * {@link ContentEvaluator}, {@link ContentProvider} and a
 * {@link OutputDestination} along with the code to execute all of the
 * components which will create a single cohesive feature that can be utilized
 * by and end customer. <br/>
 * It also is used to enforce all implementations to adhere to to the
 * {@link Runnable} interface to support execution within the framework.
 * 
 * @author Nick Gilas
 *
 */
public interface Product extends Runnable {

	public <T extends Serializable> void init(T inputParams);
}

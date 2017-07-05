package com.nick.contentEvaluator.contentProviders;

import java.io.Serializable;

/**
 * Used to define the responsibilities and actions that are needed by the
 * framework to dynamically create and retrieve any type of contents for a given
 * implementation.
 * 
 * All clients users of this interface should utilize the
 * {@link ContentProviderFactory} to retrieve the concrete implementation.
 * 
 * All implementations should note that the T generic type should translate to
 * the {@link ContentProvider#getContents(Serializable)} strongly typed input
 * parameter object
 * 
 * @author Nick Gilas
 */
public interface ContentProvider<T extends Serializable> {

	<T1 extends Serializable> T1 getContents(T providerParam);
	
}

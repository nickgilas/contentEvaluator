package com.nick.monitor.contentProviders;

import java.io.Serializable;

/**
 * Used to define the responsibilities and actions 
 * that are needed by the framework to dynamically create 
 * and retrieve the contents for a given implementation.
 * 
 * All clients users of this interface should utilize the {@link ContentProviderFactory}
 * to retrieve the concrete implementation.
 * 
 * All implementations should note that the T generic type
 * should translate to methods params type input object
 * 
 *  Also, not that no exceptions should be thrown and instead
 *  returning null is preferred
 *  
 * @author Nick Gilas
 */
public interface ContentProvider<T extends Serializable> {

	String getContents(T providerParam);
	
}

package com.nick.monitor.output;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles common tasks (i.e. validation, type checking, etc..) that are common to all concrete implementation.
 * 
 * It utilizes the Template pattern to ensure that all implementations follow the same business and 
 * validation rules and ensures consistent error handling 
 * @author Nick Gilas
 *
 */
public abstract class BaseOutputDestination<T> implements OutputDestination<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void sendContents(T contents) {

		try {
			if (contents == null || StringUtils.isBlank((String) contents)) {
				throw new IllegalArgumentException("Input contents are empty!");
			}
			
			// TODO - NG - see if this gets the generic input type this.clazz = (Class<>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			// Get the actual generic input type to ensure we are evaluating apples to apples
			Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			if (!clazz.isAssignableFrom(contents.getClass())) {
				throw new IllegalArgumentException(String.format("Incorrect input data type encountered expecte: %s got: %s ",clazz.getName(), contents.getClass().getName()));
			}
			send(contents);
		} catch (Exception e) {
			logger.error(String.format("Error occurred while trying to output to destination: %s, msg: %s ", getClass(), e.getMessage()), e);
			// log the error and move on
		}
	}
	
	protected abstract void send(T contents);
	
}

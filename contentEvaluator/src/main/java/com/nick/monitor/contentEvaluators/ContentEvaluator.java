package com.nick.monitor.contentEvaluators;

import java.io.Serializable;

/**
 * Used to define a generic content evaluator for use
 * within the larger framework. All implementations need to 
 * implement logic specific to their type evaluation.
 * 
 * @author Nick Gilas
 *
 */
public interface ContentEvaluator<T extends Serializable> {

	public boolean isContentDifferent(T content1, T content2, Integer diffThresholdPercent);
}

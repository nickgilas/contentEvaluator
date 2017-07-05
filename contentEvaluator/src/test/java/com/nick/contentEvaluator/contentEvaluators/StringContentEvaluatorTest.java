package com.nick.contentEvaluator.contentEvaluators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nick.contentEvaluator.contentEvaluators.StringContentEvaluator;

public class StringContentEvaluatorTest {

	private StringContentEvaluator evaluator;
	
	@Before
	public void setup() {
		evaluator = new StringContentEvaluator();
	}
	@Test
	public void testIsContentDifferent() {
		assertTrue(evaluator.isContentDifferent("111111111", "1111555555" , 10));
		assertFalse(evaluator.isContentDifferent("111", "111" , 10));
		assertTrue(evaluator.isContentDifferent("111", "1111" , 10));
		assertFalse(evaluator.isContentDifferent("111111111111", "11111111111", 10));
		assertFalse(evaluator.isContentDifferent(null, null, 10));
		assertTrue(evaluator.isContentDifferent(null, "not null", 10));
		assertTrue(evaluator.isContentDifferent("not null", null, 10));
	}

}

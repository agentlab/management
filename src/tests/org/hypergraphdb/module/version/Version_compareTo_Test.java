package org.hypergraphdb.module.version;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.Matcher;
import org.hypergraphdb.module.version.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * 
 * @author Dmitriy Shishkin
 */
@RunWith(Parameterized.class)
public class Version_compareTo_Test {

	private final static Matcher<Integer> EQ = equalTo(0);
	private final static Matcher<Integer> LT = lessThan(0);
	private final static Matcher<Integer> GT = greaterThan(0);

	@Parameters(name = "{index}: {0} compareTo {1} is {2}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Equal versions
            { new Version(0, 0, 0, null, null),         new Version(0, 0, 0, null, null),         EQ },
            { new Version(1, 0, 0, null, null),         new Version(1, 0, 0, null, null),         EQ },
            { new Version(1, 1, 0, null, null),         new Version(1, 1, 0, null, null),         EQ },
            { new Version(1, 1, 1, null, null),         new Version(1, 1, 1, null, null),         EQ },
            { new Version(1, 1, 1, "alpha", null),      new Version(1, 1, 1, "alpha", null),      EQ },
            { new Version(1, 1, 1, "alpha", "2"),       new Version(1, 1, 1, "alpha", "1"),       EQ },
			// Lower versions                                                                     
            { new Version(0, 0, 0, null, null),         new Version(1, 0, 0, null, null),         LT },
            { new Version(1, 0, 0, null, null),         new Version(2, 0, 0, null, null),         LT },
            { new Version(1, 1, 0, null, null),         new Version(1, 2, 0, null, null),         LT },
            { new Version(1, 1, 1, null, null),         new Version(1, 1, 2, null, null),         LT },
            { new Version(1, 1, 1, "alpha", null),      new Version(1, 1, 1, "beta", null),       LT },
            { new Version(1, 1, 1, "alpha", "betta"),   new Version(1, 1, 1, "beta", "alpha"),    LT },
            { new Version(1, 1, 1, "beta", null),       new Version(1, 1, 1, "rc", null),         LT },
            { new Version(1, 0, 0, "alpha.1", "2"),     new Version(1, 0, 0, "alpha.beta", "1"),  LT },
            // From semver.org
            { new Version(1, 0, 0, "alpha", null),      new Version(1, 0, 0, "alpha.1", null),    LT },
            { new Version(1, 0, 0, "alpha.1", null),    new Version(1, 0, 0, "alpha.beta", null), LT },
            { new Version(1, 0, 0, "alpha.beta", null), new Version(1, 0, 0, "beta", null),       LT },
            { new Version(1, 0, 0, "beta", null),       new Version(1, 0, 0, "beta.2", null),     LT },
            { new Version(1, 0, 0, "beta.2", null),     new Version(1, 0, 0, "beta.11", null),    LT },
            { new Version(1, 0, 0, "beta.11", null),    new Version(1, 0, 0, "rc.1", null),       LT },
            { new Version(1, 0, 0, "rc.1", null),       new Version(1, 0, 0, null, null),         LT },
			// Greater versions                                                                     
            { new Version(1, 0, 0, null, null),         new Version(0, 0, 0, null, null),         GT },
            { new Version(2, 0, 0, null, null),         new Version(1, 0, 0, null, null),         GT },
            { new Version(1, 2, 0, null, null),         new Version(1, 1, 0, null, null),         GT },
            { new Version(1, 1, 2, null, null),         new Version(1, 1, 1, null, null),         GT },
            { new Version(1, 1, 1, "beta", null),       new Version(1, 1, 1, "alpha", null),      GT },
            { new Version(1, 1, 1, "beta", "alpha"),    new Version(1, 1, 1, "alpha", "betta"),   GT },
            { new Version(1, 1, 1, "rc", null),         new Version(1, 1, 1, "beta", null),       GT },
            { new Version(1, 0, 0, "alpha.beta", "1"),  new Version(1, 0, 0, "alpha.1", "2"),     GT },
            // From semver.org
            { new Version(1, 0, 0, "alpha.1", null),    new Version(1, 0, 0, "alpha", null),      GT },
            { new Version(1, 0, 0, "alpha.beta", null), new Version(1, 0, 0, "alpha.1", null),    GT },
            { new Version(1, 0, 0, "beta", null),       new Version(1, 0, 0, "alpha.beta", null), GT },
            { new Version(1, 0, 0, "beta.2", null),     new Version(1, 0, 0, "beta", null),       GT },
            { new Version(1, 0, 0, "beta.11", null),    new Version(1, 0, 0, "beta.2", null),     GT },
            { new Version(1, 0, 0, "rc.1", null),       new Version(1, 0, 0, "beta.11", null),    GT },
            { new Version(1, 0, 0, null, null),         new Version(1, 0, 0, "rc.1", null),       GT },
        	// @formatter:on
		});
	}

	@Parameter(0)
	public Version first;

	@Parameter(1)
	public Version second;

	@Parameter(2)
	public Matcher<Integer> matcher;

	@Test
	public void evaluate() {
		// When
		int actual = first.compareTo(second);
		// Then
		assertThat(actual, matcher);
	}
}

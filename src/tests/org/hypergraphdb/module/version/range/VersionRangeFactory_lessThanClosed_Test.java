package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1_alpha;
import static org.hypergraphdb.module.version.range.Doubles.v1_1_0;
import static org.hypergraphdb.module.version.range.Doubles.v2_0_0;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.hypergraphdb.module.version.Version;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * 
 * @author Dmitriy Shishkin
 */
@RunWith(Parameterized.class)
public class VersionRangeFactory_lessThanClosed_Test {

	@Parameters(name = "{index}: lessThanClosed from {0} is {1}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Nulls
			{ null,          new NullPointerException("Bound is null, use explicit 'all' method") },
            //                                                                                      
            { v0_0_0,        new VersionRangeLessThanClosed(v0_0_0)                               },
            { v1_0_0,        new VersionRangeLessThanClosed(v1_0_0)                               },
            { v1_0_1,        new VersionRangeLessThanClosed(v1_0_1)                               },
            { v1_1_0,        new VersionRangeLessThanClosed(v1_1_0)                               },
            { v2_0_0,        new VersionRangeLessThanClosed(v2_0_0)                               },
            { v1_0_1_alpha,  new VersionRangeLessThanClosed(v1_0_1_alpha)                         },
        	// @formatter:on                                                                      
		});
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Parameter(0)
	public Version first;

	@Parameter(1)
	public Object result;

	@Test
	public void evaluate() {
		// Given
		if (result instanceof Throwable) {
			Throwable expected = (Throwable) result;
			exception.expect(expected.getClass());
			exception.expectMessage(expected.getMessage());
		}
		// When
		VersionRange actual = VersionRangeFactory.lessThanClosed(first);
		// Then
		if (result instanceof Class) {
			fail("Exception " + result + " expected");
		}
		assertThat(actual, equalTo(result));
	}
}

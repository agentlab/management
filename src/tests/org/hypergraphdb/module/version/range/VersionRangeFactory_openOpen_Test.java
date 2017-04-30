package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0$1;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0$2;
import static org.hypergraphdb.module.version.range.Doubles.v1000_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1_alpha;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1_alpha1;
import static org.hypergraphdb.module.version.range.Doubles.v1_1_0;
import static org.hypergraphdb.module.version.range.Doubles.v2_0_0;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.none;
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
public class VersionRangeFactory_openOpen_Test {

	@Parameters(name = "{index}: open-open from {0} and {1} is {2}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Nulls
			{ null,          v1_0_0,        new NullPointerException("Lower bound is null, use explicit 'lessThenXXX' method")                                      },
			{ v1_0_0,        null,          new NullPointerException("Upper bound is null, use explicit 'greaterThanXXX' method")                                   },
			{ null,          null,          new NullPointerException("Lower bound is null, use explicit 'lessThenXXX' method")                                      },
			// Wrong order                                                                                                                                          
			{ v1_0_1,        v1_0_0,        new IllegalArgumentException("Lower version should be less than upper version")                                         },
			{ v1_0_1_alpha1, v1_0_1_alpha,  new IllegalArgumentException("Lower version should be less than upper version")                                         },
            // Equal to NONE                                                                                                                                        
            { v0_0_0,        v0_0_0,        none()                                                                                                                  },
            { v0_0_0$1,      v0_0_0$2,      none()                                                                                                                  },
            { v1_0_0,        v1_0_0,        none()                                                                                                                  },
            { v1000_0_0,     v1000_0_0,     none()                                                                                                                  },
            //                                                                                                                                                      
            { v1_0_0,        v1_0_1,        new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0),new VersionRangeLessThanOpen(v1_0_1))              },
            { v1_0_0,        v1_1_0,        new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0),new VersionRangeLessThanOpen(v1_1_0))              },
            { v1_0_0,        v2_0_0,        new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0),new VersionRangeLessThanOpen(v2_0_0))              },
            { v1_0_1_alpha,  v1_0_1_alpha1, new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_1_alpha),new VersionRangeLessThanOpen(v1_0_1_alpha1)) },
        	// @formatter:on
		});
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Parameter(0)
	public Version first;

	@Parameter(1)
	public Version second;

	@Parameter(2)
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
		VersionRange actual = VersionRangeFactory.openOpen(first, second);
		// Then
		if (result instanceof Class) {
			fail("Exception " + result + " expected");
		}
		assertThat(actual, equalTo(result));
	}
}

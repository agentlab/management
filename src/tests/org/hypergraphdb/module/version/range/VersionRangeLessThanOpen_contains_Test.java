package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_1;
import static org.hypergraphdb.module.version.range.Doubles.v0_1_0;
import static org.hypergraphdb.module.version.range.Doubles.v0_1_1;
import static org.hypergraphdb.module.version.range.Doubles.v1000_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1;
import static org.hypergraphdb.module.version.range.Doubles.v1_1_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_1_1;
import static org.hypergraphdb.module.version.range.Doubles.v2_0_0;
import static org.junit.Assert.assertThat;

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
public class VersionRangeLessThanOpen_contains_Test {

	@Parameters(name = "{index}: Range {0} contains {1} is {2}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Less Than Open ( , 1.0.0)
            { new VersionRangeLessThanOpen(v1_0_0), v0_0_0,    true  },
            { new VersionRangeLessThanOpen(v1_0_0), v0_0_1,    true  },
            { new VersionRangeLessThanOpen(v1_0_0), v0_1_0,    true  },
            { new VersionRangeLessThanOpen(v1_0_0), v0_1_1,    true  },
            { new VersionRangeLessThanOpen(v1_0_0), v1_0_0,    false }, // bound
            { new VersionRangeLessThanOpen(v1_0_0), v1_0_1,    false },
            { new VersionRangeLessThanOpen(v1_0_0), v1_1_0,    false },
            { new VersionRangeLessThanOpen(v1_0_0), v1_1_1,    false },
            { new VersionRangeLessThanOpen(v1_0_0), v2_0_0,    false },
            { new VersionRangeLessThanOpen(v1_0_0), v1000_0_0, false },
        	// @formatter:on
		});
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Parameter(0)
	public VersionRange range;

	@Parameter(1)
	public Version version;

	@Parameter(2)
	public boolean result;

	@Test
	public void evaluate() {
		// When
		boolean actual = range.contains(version);
		// Then
		assertThat(actual, equalTo(result));
	}
}

package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_1;
import static org.hypergraphdb.module.version.range.Doubles.v0_1_0;
import static org.hypergraphdb.module.version.range.Doubles.v1000_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1_alpha;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_1_alpha1;
import static org.hypergraphdb.module.version.range.Doubles.v1_1_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_1_1;
import static org.hypergraphdb.module.version.range.Doubles.v1_2_3;
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
public class VersionRangeIntersection_contains_Test {

	@Parameters(name = "{index}: Range {0} contains {1} is {2}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			//===========================================================
			// Open-Open (1.0.0, 2.0.0)
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v0_0_0,        false }, // less than lower
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v1_0_0,        false }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v1_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v1_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v1_1_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v2_0_0,        false }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v1000_0_0,     false }, // greater than upper
            // Open-Open Zeros (0.0.0, 1.0.0)                                                                                                       
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),           v0_0_0,        false }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),           v0_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),           v0_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),           v1_0_0,        false }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),           v2_0_0,        false }, // greater than upper
            // Different sizes                                                                                                                      
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),           v1_2_3,        true  }, // major difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v1_1_0)),           v1_0_1,        true  }, // minor difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v1_0_1)),           v1_0_1_alpha,  true  }, // patch difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_1_alpha), new VersionRangeLessThanOpen(v1_0_1)),     v1_0_1_alpha1, true  }, // pre-release difference
			//===========================================================
			// Closed-Closed [1.0.0, 2.0.0]
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v0_0_0,        false }, // less than lower
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v1_0_0,        true  }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v1_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v1_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v1_1_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v2_0_0,        true  }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v1000_0_0,     false }, // greater than upper
            // Closed-Closed Zeros [0.0.0, 1.0.0]                                     
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),       v0_0_0,        true  }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),       v0_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),       v0_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),       v1_0_0,        true  }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),       v2_0_0,        false }, // greater than upper
            // Different sizes
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),       v1_2_3,        true  }, // major difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v1_1_0)),       v1_0_1,        true  }, // minor difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanClosed(v1_0_1)),       v1_0_1_alpha,  true  }, // patch difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_1_alpha), new VersionRangeLessThanClosed(v1_0_1)), v1_0_1_alpha1, true  }, // pre-release difference
			//===========================================================
			// Open-Closed (1.0.0, 2.0.0]
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v0_0_0,        false }, // less than lower
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v1_0_0,        false }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v1_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v1_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v1_1_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v2_0_0,        true  }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v1000_0_0,     false }, // greater than upper
            // Open-Closed Zeros (0.0.0, 1.0.0]                                                                                      
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),         v0_0_0,        false }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),         v0_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),         v0_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),         v1_0_0,        true  }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanClosed(v1_0_0)),         v2_0_0,        false }, // greater than upper
            // Different sizes                                                                                                         
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v2_0_0)),         v1_2_3,        true  }, // major difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v1_1_0)),         v1_0_1,        true  }, // minor difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanClosed(v1_0_1)),         v1_0_1_alpha,  true  }, // patch difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_1_alpha), new VersionRangeLessThanClosed(v1_0_1)),   v1_0_1_alpha1, true  }, // pre-release difference
			//===========================================================
			// Closed-Open [1.0.0, 2.0.0)
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v0_0_0,        false }, // less than lower
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v1_0_0,        true  }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v1_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v1_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v1_1_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v2_0_0,        false }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v1000_0_0,     false }, // greater than upper
            // Closed-Open Zeros [0.0.0, 1.0.0)                                                                                     
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),         v0_0_0,        true  }, // lower bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),         v0_0_1,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),         v0_1_0,        true  },
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),         v1_0_0,        false }, // upper bound
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v0_0_0), new VersionRangeLessThanOpen(v1_0_0)),         v2_0_0,        false }, // greater than upper
            // Different sizes                                                                                                         
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v2_0_0)),         v1_2_3,        true  }, // major difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v1_1_0)),         v1_0_1,        true  }, // minor difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_0), new VersionRangeLessThanOpen(v1_0_1)),         v1_0_1_alpha,  true  }, // patch difference
            { new VersionRangeIntersection(new VersionRangeGreaterThanClosed(v1_0_1_alpha), new VersionRangeLessThanOpen(v1_0_1)),   v1_0_1_alpha1, true  }, // pre-release difference
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

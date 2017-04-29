package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.all;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.closedClosed;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.closedOpen;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.none;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.greaterThanClosed;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.greaterThanOpen;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.lessThanClosed;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.lessThanOpen;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.openClosed;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.openOpen;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.single;
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
public class VersionRange_contains_Test {

	// @formatter:off
	private final static Version v0_0_0_alpha$1 = Version.builder().major(0).minor(0).patch(0).prerelease("alpha").metadata("1").build();
	private final static Version v0_0_0         = Version.builder().major(0).minor(0).patch(0).build();
	private final static Version v0_0_0$1       = Version.builder().major(0).minor(0).patch(0).metadata("1").build();
	private final static Version v0_0_1         = Version.builder().major(0).minor(0).patch(1).build();
	private final static Version v0_1_0         = Version.builder().major(0).minor(1).patch(0).build();
	private final static Version v0_1_1         = Version.builder().major(0).minor(1).patch(1).build();
	private final static Version v0_99_99_rc$1  = Version.builder().minor(99).patch(9).prerelease("rc").metadata("1").build();
	
	private final static Version v1_0_0_alpha$1 = Version.builder().major(1).minor(0).patch(0).prerelease("alpha").metadata("1").build();
	private final static Version v1_0_0         = Version.builder().major(1).minor(0).patch(0).build();
	private final static Version v1_0_0$1       = Version.builder().major(1).minor(0).patch(0).metadata("1").build();
	private final static Version v1_0_1_alpha   = Version.builder().major(1).minor(0).patch(1).prerelease("alpha").build();
	private final static Version v1_0_1_alpha1  = Version.builder().major(1).minor(0).patch(1).prerelease("alpha.1").build();
	private final static Version v1_0_1         = Version.builder().major(1).minor(0).patch(1).build();
	private final static Version v1_1_0         = Version.builder().major(1).minor(1).patch(0).build();
	private final static Version v1_1_1         = Version.builder().major(1).minor(1).patch(1).build();
	private final static Version v1_2_3         = Version.builder().major(1).minor(2).patch(3).build();
	
	private final static Version v2_0_0         = Version.builder().major(2).minor(0).patch(0).build();
	
	private final static Version v1000_0_0      = Version.builder().major(1000).minor(0).patch(0).build();
	// @formatter:on

	@Parameters(name = "{index}: Range {0} contains {1} is {2}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			//===========================================================
            // All [0.0.0,)
            { all(),                              v0_0_0,         true  },
            { all(),                              v1_0_0,         true  },
            { all(),                              v1000_0_0,      true  },
			//===========================================================
            // Empty ()                                             
            { none(),                             v0_0_0,         false },
            { none(),                             v1_0_0,         false },
            { none(),                             v1000_0_0,      false },
			//===========================================================
            // Single [0.0.0, 0.0.0]                                       
            { single(v0_0_0),                     v0_0_0,         true  },
            { single(v0_0_0),                     v0_0_0$1,       true  },
            { single(v0_0_0),                     v1_0_0,         false },
            { single(v0_0_0),                     v0_1_0,         false },
            { single(v0_0_0),                     v0_0_1,         false },
            { single(v0_0_0),                     v1000_0_0,      false },
            { single(v0_0_0),                     v0_0_0_alpha$1, false },
			//===========================================================
            // Single [1.0.0, 1.0.0]
            { single(v1_0_0),                     v1_0_0,         true  },
            { single(v1_0_0),                     v0_0_0,         false },
            { single(v1_0_0),                     v0_1_0,         false },
            { single(v1_0_0),                     v0_0_1,         false },
            { single(v1_0_0),                     v2_0_0,         false },
            { single(v1_0_0),                     v1_1_0,         false },
            { single(v1_0_0),                     v1_0_1,         false },
            { single(v1_0_0),                     v0_99_99_rc$1,  false },
            { single(v1_0_0),                     v1000_0_0,      false },
            { single(v1_0_0_alpha$1),             v1000_0_0,      false },
            { single(v1_0_0$1),                   v1_0_0,         true  },
			//===========================================================
			// Greater Than Closed [1.0.0, )
            { greaterThanClosed(v1_0_0),          v0_0_0,         false },
            { greaterThanClosed(v1_0_0),          v1_0_0,         true  }, // bound
            { greaterThanClosed(v1_0_0),          v1_0_1,         true  },
            { greaterThanClosed(v1_0_0),          v1_1_0,         true  },
            { greaterThanClosed(v1_0_0),          v1_1_1,         true  },
            { greaterThanClosed(v1_0_0),          v2_0_0,         true  },
            { greaterThanClosed(v1_0_0),          v1000_0_0,      true  },
            // Greater Than Closed Zeros [0.0.0,)   
            { greaterThanClosed(v0_0_0),          v0_0_0,         true  }, // bound
            { greaterThanClosed(v0_0_0),          v0_0_1,         true  },
            { greaterThanClosed(v0_0_0),          v0_1_0,         true  },
            { greaterThanClosed(v0_0_0),          v1_0_0,         true  },
			//===========================================================
			// Greater Than Open (1.0.0, )
            { greaterThanOpen(v1_0_0),            v0_0_0,         false },
            { greaterThanOpen(v1_0_0),            v1_0_0,         false }, // bound
            { greaterThanOpen(v1_0_0),            v1_0_1,         true  },
            { greaterThanOpen(v1_0_0),            v1_1_0,         true  },
            { greaterThanOpen(v1_0_0),            v1_1_1,         true  },
            { greaterThanOpen(v1_0_0),            v2_0_0,         true  },
            { greaterThanOpen(v1_0_0),            v1000_0_0,      true  },
            // Greater Than Open Zeros (0.0.0, )     
            { greaterThanOpen(v0_0_0),            v0_0_0,         false }, // bound
            { greaterThanOpen(v0_0_0),            v0_0_1,         true  },
            { greaterThanOpen(v0_0_0),            v0_1_0,         true  },
            { greaterThanOpen(v0_0_0),            v1_0_0,         true  },
			//===========================================================
			// Less Than Closed ( , 1.0.0]
            { lessThanClosed(v1_0_0),             v0_0_0,         true  },
            { lessThanClosed(v1_0_0),             v0_0_1,         true  },
            { lessThanClosed(v1_0_0),             v0_1_0,         true  },
            { lessThanClosed(v1_0_0),             v0_1_1,         true  },
            { lessThanClosed(v1_0_0),             v1_0_0,         true  }, // bound
            { lessThanClosed(v1_0_0),             v1_0_1,         false },
            { lessThanClosed(v1_0_0),             v1_1_0,         false },
            { lessThanClosed(v1_0_0),             v1_1_1,         false },
            { lessThanClosed(v1_0_0),             v2_0_0,         false },
            { lessThanClosed(v1_0_0),             v1000_0_0,      false },
			//===========================================================
			// Less Than Open ( , 1.0.0)
            { lessThanOpen(v1_0_0),               v0_0_0,         true  },
            { lessThanOpen(v1_0_0),               v0_0_1,         true  },
            { lessThanOpen(v1_0_0),               v0_1_0,         true  },
            { lessThanOpen(v1_0_0),               v0_1_1,         true  },
            { lessThanOpen(v1_0_0),               v1_0_0,         false }, // bound
            { lessThanOpen(v1_0_0),               v1_0_1,         false },
            { lessThanOpen(v1_0_0),               v1_1_0,         false },
            { lessThanOpen(v1_0_0),               v1_1_1,         false },
            { lessThanOpen(v1_0_0),               v2_0_0,         false },
            { lessThanOpen(v1_0_0),               v1000_0_0,      false },
			//===========================================================
			// Open-Open (1.0.0, 2.0.0)
            { openOpen(v1_0_0, v2_0_0),           v0_0_0,         false }, // less than lower
            { openOpen(v1_0_0, v2_0_0),           v1_0_0,         false }, // lower bound
            { openOpen(v1_0_0, v2_0_0),           v1_0_1,         true  },
            { openOpen(v1_0_0, v2_0_0),           v1_1_0,         true  },
            { openOpen(v1_0_0, v2_0_0),           v1_1_1,         true  },
            { openOpen(v1_0_0, v2_0_0),           v2_0_0,         false }, // upper bound
            { openOpen(v1_0_0, v2_0_0),           v1000_0_0,      false }, // greater than upper
            // Open-Open Zeros (0.0.0, 1.0.0)
            { openOpen(v0_0_0, v1_0_0),           v0_0_0,         false }, // lower bound
            { openOpen(v0_0_0, v1_0_0),           v0_0_1,         true  },
            { openOpen(v0_0_0, v1_0_0),           v0_1_0,         true  },
            { openOpen(v0_0_0, v1_0_0),           v1_0_0,         false }, // upper bound
            { openOpen(v0_0_0, v1_0_0),           v2_0_0,         false }, // greater than upper
            // Different sizes
            { openOpen(v1_0_0, v2_0_0),           v1_2_3,         true  }, // major difference
            { openOpen(v1_0_0, v1_1_0),           v1_0_1,         true  }, // minor difference
            { openOpen(v1_0_0, v1_0_1),           v1_0_1_alpha,   true  }, // patch difference
            { openOpen(v1_0_1_alpha, v1_0_1),     v1_0_1_alpha1,  true  }, // pre-release difference
			//===========================================================
			// Closed-Closed [1.0.0, 2.0.0]
            { closedClosed(v1_0_0, v2_0_0),       v0_0_0,         false }, // less than lower
            { closedClosed(v1_0_0, v2_0_0),       v1_0_0,         true  }, // lower bound
            { closedClosed(v1_0_0, v2_0_0),       v1_0_1,         true  },
            { closedClosed(v1_0_0, v2_0_0),       v1_1_0,         true  },
            { closedClosed(v1_0_0, v2_0_0),       v1_1_1,         true  },
            { closedClosed(v1_0_0, v2_0_0),       v2_0_0,         true  }, // upper bound
            { closedClosed(v1_0_0, v2_0_0),       v1000_0_0,      false }, // greater than upper
            // Closed-Closed Zeros [0.0.0, 1.0.0]
            { closedClosed(v0_0_0, v1_0_0),       v0_0_0,         true  }, // lower bound
            { closedClosed(v0_0_0, v1_0_0),       v0_0_1,         true  },
            { closedClosed(v0_0_0, v1_0_0),       v0_1_0,         true  },
            { closedClosed(v0_0_0, v1_0_0),       v1_0_0,         true  }, // upper bound
            { closedClosed(v0_0_0, v1_0_0),       v2_0_0,         false }, // greater than upper
            // Different sizes
            { closedClosed(v1_0_0, v2_0_0),       v1_2_3,         true  }, // major difference
            { closedClosed(v1_0_0, v1_1_0),       v1_0_1,         true  }, // minor difference
            { closedClosed(v1_0_0, v1_0_1),       v1_0_1_alpha,   true  }, // patch difference
            { closedClosed(v1_0_1_alpha, v1_0_1), v1_0_1_alpha1,  true  }, // pre-release difference
			//===========================================================
			// Open-Closed (1.0.0, 2.0.0]
            { openClosed(v1_0_0, v2_0_0),         v0_0_0,         false }, // less than lower
            { openClosed(v1_0_0, v2_0_0),         v1_0_0,         false }, // lower bound
            { openClosed(v1_0_0, v2_0_0),         v1_0_1,         true  },
            { openClosed(v1_0_0, v2_0_0),         v1_1_0,         true  },
            { openClosed(v1_0_0, v2_0_0),         v1_1_1,         true  },
            { openClosed(v1_0_0, v2_0_0),         v2_0_0,         true  }, // upper bound
            { openClosed(v1_0_0, v2_0_0),         v1000_0_0,      false }, // greater than upper
            // Open-Closed Zeros (0.0.0, 1.0.0]   
            { openClosed(v0_0_0, v1_0_0),         v0_0_0,         false }, // lower bound
            { openClosed(v0_0_0, v1_0_0),         v0_0_1,         true  },
            { openClosed(v0_0_0, v1_0_0),         v0_1_0,         true  },
            { openClosed(v0_0_0, v1_0_0),         v1_0_0,         true  }, // upper bound
            { openClosed(v0_0_0, v1_0_0),         v2_0_0,         false }, // greater than upper
            // Different sizes                    
            { openClosed(v1_0_0, v2_0_0),         v1_2_3,         true  }, // major difference
            { openClosed(v1_0_0, v1_1_0),         v1_0_1,         true  }, // minor difference
            { openClosed(v1_0_0, v1_0_1),         v1_0_1_alpha,   true  }, // patch difference
            { openClosed(v1_0_1_alpha, v1_0_1),   v1_0_1_alpha1,  true  }, // pre-release difference
			//===========================================================
			// Closed-Open [1.0.0, 2.0.0)
            { closedOpen(v1_0_0, v2_0_0),         v0_0_0,         false }, // less than lower
            { closedOpen(v1_0_0, v2_0_0),         v1_0_0,         true  }, // lower bound
            { closedOpen(v1_0_0, v2_0_0),         v1_0_1,         true  },
            { closedOpen(v1_0_0, v2_0_0),         v1_1_0,         true  },
            { closedOpen(v1_0_0, v2_0_0),         v1_1_1,         true  },
            { closedOpen(v1_0_0, v2_0_0),         v2_0_0,         false }, // upper bound
            { closedOpen(v1_0_0, v2_0_0),         v1000_0_0,      false }, // greater than upper
            // Closed-Open Zeros [0.0.0, 1.0.0)   
            { closedOpen(v0_0_0, v1_0_0),         v0_0_0,         true  }, // lower bound
            { closedOpen(v0_0_0, v1_0_0),         v0_0_1,         true  },
            { closedOpen(v0_0_0, v1_0_0),         v0_1_0,         true  },
            { closedOpen(v0_0_0, v1_0_0),         v1_0_0,         false }, // upper bound
            { closedOpen(v0_0_0, v1_0_0),         v2_0_0,         false }, // greater than upper
            // Different sizes                    
            { closedOpen(v1_0_0, v2_0_0),         v1_2_3,         true  }, // major difference
            { closedOpen(v1_0_0, v1_1_0),         v1_0_1,         true  }, // minor difference
            { closedOpen(v1_0_0, v1_0_1),         v1_0_1_alpha,   true  }, // patch difference
            { closedOpen(v1_0_1_alpha, v1_0_1),   v1_0_1_alpha1,  true  }, // pre-release difference
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

package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.Doubles.v0_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1000_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0_alpha$1;
import static org.hypergraphdb.module.version.range.Doubles.v1_0_0_beta$2;
import static org.hypergraphdb.module.version.range.Doubles.v1_2_0;
import static org.hypergraphdb.module.version.range.Doubles.v2_0_0;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.closedClosed;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
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
public class VersionRangeParserImpl_parse_Test {

	@Parameters(name = "{index}: Parse {0} is {1}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Invalid strings
            { null,                             NullPointerException.class                  },
            { "",                               VersionRangeParserException.class           },
            { "{",                              VersionRangeParserException.class           },
            { "}",                              VersionRangeParserException.class           },
            { "{0,1)",                          VersionRangeParserException.class           },
            { "[0,1}",                          VersionRangeParserException.class           },
            { "]0,1[",                          VersionRangeParserException.class           },
            { ")0,1(",                          VersionRangeParserException.class           },
            // Invalid bounds                                                               
            { "(0,0)",                          VersionRangeParserException.class           },
            { "(1,1)",                          VersionRangeParserException.class           },
            { "(1000,1000)",                    VersionRangeParserException.class           },
            // All                                                                            
            { "(,)",                            new VersionRangeAll()                       },
            { "(,_)",                           new VersionRangeAll()                       },
            { "(_,)",                           new VersionRangeAll()                       },
            { "(_,_)",                          new VersionRangeAll()                       },
            // Empty                                                                           
            { "()",                             new VersionRangeNone()                      },
            { "(0,0]",                          new VersionRangeNone()                      },
            { "[0,0)",                          new VersionRangeNone()                      },
            { "(1,1]",                          new VersionRangeNone()                      },
            { "[1,1)",                          new VersionRangeNone()                      },
            { "(1000,1000]",                    new VersionRangeNone()                      },
            { "[1000,1000)",                    new VersionRangeNone()                      },
            { "[1.0.0-alpha+1, 1.0.0-alpha+1)", new VersionRangeNone()                      },
            // Single                                                                          
            { "[0,0]",                          new VersionRangeSingle(v0_0_0)              },
            { "[1,1]",                          new VersionRangeSingle(v1_0_0)              },
            { "[1000,1000]",                    new VersionRangeSingle(v1000_0_0)           },
            { "[1.0.0-alpha+1, 1.0.0-alpha+1)", new VersionRangeSingle(v1_0_0_alpha$1)      },
			// Open                                                                          
            { "(0,1)",                          new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0))                },
            { "(1,2)",                          new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0))                },
            { "(1,1.2)",                        new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v1_2_0))                },
            { "(1.0,1.2)",                      new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v1_2_0))                },
            { "(1.0.0,1.2.0)",                  new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v1_2_0))                },
            { "(1.0.0-alpha+1, 1.0.0-beta+2)",  new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0_alpha$1), new VersionRangeLessThanOpen(v1_0_0_beta$2)) },
            { "( 0 , 1 )",                      new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v0_0_0), new VersionRangeLessThanOpen(v1_0_0))                },
            { "(  1  , 2  )",                   new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0))                },
            { "   (  1  , 2  )   ",             new VersionRangeIntersection(new VersionRangeGreaterThanOpen(v1_0_0), new VersionRangeLessThanOpen(v2_0_0))                },
			// Closed                                                                      
            { "[0,1]",                          closedClosed(v0_0_0, v1_0_0)                },
            { "[1,2]",                          closedClosed(v1_0_0, v2_0_0)                },
            { "[1,1.2]",                        closedClosed(v1_0_0, v1_2_0)                },
            { "[1.0,1.2]",                      closedClosed(v1_0_0, v1_2_0)                },
            { "[1.0.0,1.2.0]",                  closedClosed(v1_0_0, v1_2_0)                },
            { "[1.0.0-alpha+1, 1.0.0-beta+2]",  closedClosed(v1_0_0_alpha$1, v1_0_0_beta$2) },
            { "[ 0 , 1 ]",                      closedClosed(v0_0_0, v1_0_0)                },
            { "[  1  , 2  ]",                   closedClosed(v1_0_0, v2_0_0)                },
            { "   [  1  , 2  ]   ",             closedClosed(v1_0_0, v2_0_0)                },
            
        	// @formatter:on
		});
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Parameter(0)
	public String input;

	@Parameter(1)
	public Object result;

	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void evaluate() {
		// Given
		if (result instanceof Class) {
			exception.expect((Class<? extends Throwable>) result);
		}
		VersionRangeParser parser = new VersionRangeParserImpl();
		// When
		VersionRange version = parser.parse(input);
		// Then
		if (result instanceof Class) {
			fail("Exception " + result + " expected");
		}
		assertThat(version, equalTo(result));
	}
}

package org.hypergraphdb.module.version.range;

import static org.hamcrest.Matchers.equalTo;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.all;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.none;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.closedClosed;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.openOpen;
import static org.hypergraphdb.module.version.range.VersionRangeFactory.single;
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
public class VersionRangeParserImpl_parse_Test {

	private final static Version v0 = Version.builder().build();
	private final static Version v1 = Version.builder().major(1).build();
	private final static Version v1000 = Version.builder().major(1000).build();
	private final static Version v2 = Version.builder().major(2).build();
	private final static Version v1_2 = Version.builder().major(1).minor(2).build();
	private final static Version v1_alpha_1 = Version
			.builder()
			.major(1)
			.prerelease("alpha")
			.metadata("1")
			.build();
	private final static Version v1_beta_2 = Version
			.builder()
			.major(1)
			.prerelease("beta")
			.metadata("2")
			.build();

	@Parameters(name = "Parsing {0} is {1}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Invalid strings
            { null,                             NullPointerException.class                   },
            { "",                               VersionRangeParserException.class            },
            { "{",                              VersionRangeParserException.class            },
            { "}",                              VersionRangeParserException.class            },
            { "{0,1)",                          VersionRangeParserException.class            },
            { "[0,1}",                          VersionRangeParserException.class            },
            { "]0,1[",                          VersionRangeParserException.class            },
            { ")0,1(",                          VersionRangeParserException.class            },
            // Invalid bounds                                                                
            { "(0,0)",                          VersionRangeParserException.class            },
            { "(1,1)",                          VersionRangeParserException.class            },
            { "(1000,1000)",                    VersionRangeParserException.class            },
            // All                                                                             
            { "(,)",                            all()                                        },
            { "(,_)",                           all()                                        },
            { "(_,)",                           all()                                        },
            { "(_,_)",                          all()                                        },
            { "[0,_)",                          all()                                        },
            { "[0.0,_)",                        all()                                        },
            { "[0.0.0,_)",                      all()                                        },
            { "[0,)",                           all()                                        },
            { "[0.0,)",                         all()                                        },
            { "[0.0.0,)",                       all()                                        },
            // Empty                                                                           
            { "(0,0]",                          none()                                      },
            { "[0,0)",                          none()                                      },
            { "(1,1]",                          none()                                      },
            { "[1,1)",                          none()                                      },
            { "(1000,1000]",                    none()                                      },
            { "[1000,1000)",                    none()                                      },
            { "[1.0.0-alpha+1, 1.0.0-alpha+1)", none()                                      },
            // Single                                                                          
            { "[0,0]",                          single(v0)                                   },
            { "[1,1]",                          single(v1)                                   },
            { "[1000,1000]",                    single(v1000)                                },
            { "[1.0.0-alpha+1, 1.0.0-alpha+1)", single(v1_alpha_1)                           },
			// Open                                                                          
            { "(0,1)",                          openOpen(v0, v1)                    },
            { "(1,2)",                          openOpen(v1, v2)                    },
            { "(1,1.2)",                        openOpen(v1, v1_2)                  },
            { "(1.0,1.2)",                      openOpen(v1, v1_2)                  },
            { "(1.0.0,1.2.0)",                  openOpen(v1, v1_2)                  },
            { "(1.0.0-alpha+1, 1.0.0-beta+2)",  openOpen(v1_alpha_1, v1_beta_2)     },
            { "( 0 , 1 )",                      openOpen(v0, v1)                    },
            { "(  1  , 2  )",                   openOpen(v1, v2)                    },
            { "   (  1  , 2  )   ",             openOpen(v1, v2)                    },
			// Closed                                                                      
            { "[0,1]",                          closedClosed(v0, v1)                },
            { "[1,2]",                          closedClosed(v1, v2)                },
            { "[1,1.2]",                        closedClosed(v1, v1_2)              },
            { "[1.0,1.2]",                      closedClosed(v1, v1_2)              },
            { "[1.0.0,1.2.0]",                  closedClosed(v1, v1_2)              },
            { "[1.0.0-alpha+1, 1.0.0-beta+2]",  closedClosed(v1_alpha_1, v1_beta_2) },
            { "[ 0 , 1 ]",                      closedClosed(v0, v1)                },
            { "[  1  , 2  ]",                   closedClosed(v1, v2)                },
            { "   [  1  , 2  ]   ",             closedClosed(v1, v2)                },
            
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

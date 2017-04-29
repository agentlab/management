package org.hypergraphdb.module.version;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

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
public class VersionWriterImpl_write_Test {

	@Parameters(name = "{0} toString() is {1}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
			// Equal versions
            { new Version(0, 0, 0, null, null),         "0.0.0"              },
            { new Version(1, 0, 0, null, null),         "1.0.0"              },
            { new Version(1, 1, 0, null, null),         "1.1.0"              },
            { new Version(1, 1, 1, null, null),         "1.1.1"              },
            { new Version(1, 1, 1, "alpha", null),      "1.1.1-alpha"        },
            { new Version(1, 1, 4, "beta", null),       "1.1.4-beta"         },
            { new Version(1, 1, 1, "rc", null),         "1.1.1-rc"           },
            { new Version(1, 2, 3, null, "20172014"),   "1.2.3+20172014"     },
            { new Version(1, 2, 3, null, "alpha"),      "1.2.3+alpha"        },
            { new Version(1, 2, 3, null, "beta"),       "1.2.3+beta"         },
            { new Version(1, 1, 1, "alpha", "2"),       "1.1.1-alpha+2"      },
            { new Version(1, 2, 1, "alpha", "beta"),    "1.2.1-alpha+beta"   },
            { new Version(1, 0, 0, "alpha.1", "2"),     "1.0.0-alpha.1+2"    },
            { new Version(1, 1, 1, "beta", "alpha"),    "1.1.1-beta+alpha"   },
            { new Version(1, 0, 0, "alpha.beta", "1"),  "1.0.0-alpha.beta+1" },
            // From semver.org                          
            { new Version(1, 0, 0, "alpha", null),      "1.0.0-alpha"        },
            { new Version(1, 0, 0, "alpha.1", null),    "1.0.0-alpha.1"      },
            { new Version(1, 0, 0, "alpha.beta", null), "1.0.0-alpha.beta"   },
            { new Version(1, 0, 0, "beta", null),       "1.0.0-beta"         },
            { new Version(1, 0, 0, "beta.2", null),     "1.0.0-beta.2"       },
            { new Version(1, 0, 0, "beta.11", null),    "1.0.0-beta.11"      },
            { new Version(1, 0, 0, "rc.1", null),       "1.0.0-rc.1"         },
            { new Version(1, 0, 0, null, null),         "1.0.0"              },
        	// @formatter:on
		});
	}

	@Parameter(0)
	public Version input;

	@Parameter(1)
	public String result;

	@Test
	public void evaluate() {
		// Given
		VersionWriter writer = new VersionWriterImpl();
		// When
		String actual = writer.write(input);
		// Then
		assertThat(actual, equalTo(result));
	}
}

package org.hypergraphdb.module.version.range;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	// @formatter:off
	VersionRange_contains_Test.class,
	VersionRangeParserImpl_parse_Test.class,
	// @formatter:on
})
public class VersionRangeTestSuite {

}

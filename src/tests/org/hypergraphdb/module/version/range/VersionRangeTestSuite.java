package org.hypergraphdb.module.version.range;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	// @formatter:off
	VersionRangeAll_contains_Test.class,
	VersionRangeNone_contains_Test.class,
	VersionRangeSingle_contains_Test.class,
	VersionRangeGreaterThanClosed_contains_Test.class,
	VersionRangeGreaterThanOpen_contains_Test.class,
	VersionRangeLessThanClosed_contains_Test.class,
	VersionRangeLessThanOpen_contains_Test.class,
	VersionRangeIntersection_contains_Test.class,
	
	VersionRangeParserImpl_parse_Test.class,
	
	VersionRangeFactory_closedClosed_Test.class,
	VersionRangeFactory_closedOpen_Test.class,
	VersionRangeFactory_greaterThanClosed_Test.class,
	VersionRangeFactory_greaterThanOpen_Test.class,
	VersionRangeFactory_openClosed_Test.class,
	VersionRangeFactory_openOpen_Test.class,
	VersionRangeFactory_lessThanClosed_Test.class,
	VersionRangeFactory_lessThanOpen_Test.class,
	// @formatter:on
})
public class VersionRangeTestSuite {

}

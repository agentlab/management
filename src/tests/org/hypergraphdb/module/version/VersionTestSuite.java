package org.hypergraphdb.module.version;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	// @formatter:off
	Version_compareTo_Test.class,
	Version_toString_Test.class,
	VersionParserImpl_parse_Test.class,
	VersionWriterImpl_write_Test.class
	// @formatter:on
})
public class VersionTestSuite {

}

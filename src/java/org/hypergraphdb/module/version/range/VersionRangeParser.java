package org.hypergraphdb.module.version.range;

import org.hypergraphdb.app.management.Parser;

/**
 * 
 * @author Dmitriy Shishkin
 */
public interface VersionRangeParser extends Parser<VersionRange> {

	VersionRange parse(String input);

}

package org.hypergraphdb.module.version;

import org.hypergraphdb.app.management.Parser;

/**
 * 
 * @author Dmitriy Shishkin
 */
public interface VersionParser extends Parser<Version> {

	/**
	 * 
	 * @throws VersionParserException
	 *             if parse fails
	 */
	Version parse(String input);

}

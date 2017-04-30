package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

/**
 * 
 * @author Dmitriy Shishkin
 */
public interface VersionRange {

	public static final char LOWER_OPEN_BRACKET = '(';
	public static final char LOWER_CLOSED_BRACKET = '[';
	public static final char UPPER_OPEN_BRACKET = ')';
	public static final char UPPER_CLOSED_BRACKET = ']';
	public static final char SEPARATOR = ',';
	public static final String INFINITY = "";

	boolean contains(Version version);

}

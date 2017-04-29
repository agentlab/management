package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

/**
 * 
 * @author Dmitriy Shishkin
 */
class VersionRangeNone implements VersionRange {

	public boolean contains(Version version) {
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOWER_OPEN_BRACKET);
		builder.append(UPPER_OPEN_BRACKET);
		return builder.toString();
	}

}

package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

/**
 * 
 * @author Dmitriy Shishkin
 */
class VersionRangeAll implements VersionRange {

	public boolean contains(Version version) {
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOWER_CLOSED_BRACKET);
		builder.append(Version.ZERO.toString());
		builder.append(SEPARATOR);
		builder.append(INFINITY);
		builder.append(UPPER_OPEN_BRACKET);
		return builder.toString();
	}

}

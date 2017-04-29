package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

/**
 * 
 * @author Dmitriy Shishkin
 */
class VersionRangeGreaterThanOpen extends VersionRangeGreaterThan {

	public VersionRangeGreaterThanOpen(Version version) {
		super(version);
	}

	public boolean contains(Version version) {
		return this.version.compareTo(version) < 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getBracket());
		builder.append(version.toString());
		builder.append(SEPARATOR);
		builder.append(INFINITY);
		builder.append(UPPER_OPEN_BRACKET);
		return builder.toString();
	}

	@Override
	protected char getBracket() {
		return LOWER_OPEN_BRACKET;
	}

}

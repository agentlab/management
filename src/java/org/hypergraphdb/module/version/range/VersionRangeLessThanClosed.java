package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

/**
 * 
 * @author Dmitriy Shishkin
 */
class VersionRangeLessThanClosed extends VersionRangeLessThan {

	public VersionRangeLessThanClosed(Version version) {
		super(version);
	}

	public boolean contains(Version version) {
		return this.version.compareTo(version) >= 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOWER_OPEN_BRACKET);
		builder.append(INFINITY);
		builder.append(SEPARATOR);
		builder.append(version.toString());
		builder.append(getBracket());
		return builder.toString();
	}

	@Override
	protected char getBracket() {
		return UPPER_CLOSED_BRACKET;
	}

}

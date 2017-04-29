package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Getter
@EqualsAndHashCode
class VersionRangeIntersection implements VersionRange {

	private VersionRangeGreaterThan lower;
	private VersionRangeLessThan upper;

	public VersionRangeIntersection(VersionRangeGreaterThan lower, VersionRangeLessThan upper) {
		if (lower.version.compareTo(upper.version) > 0) {
			throw new IllegalArgumentException("Lower version should be less than upper version");
		}
		this.lower = lower;
		this.upper = upper;
	}

	public boolean contains(Version version) {
		if (version == null) {
			throw new NullPointerException();
		}
		return lower.contains(version) && upper.contains(version);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(lower.getBracket());
		builder.append(lower.version.toString());
		builder.append(SEPARATOR);
		builder.append(upper.version.toString());
		builder.append(upper.getBracket());
		return builder.toString();
	}

}

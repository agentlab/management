package org.hypergraphdb.app.management;

import lombok.Getter;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Getter
public class VersionRange {

	private BoundType lowerType;
	private Version lower;
	private BoundType upperType;
	private Version upper;

	public static enum BoundType {

		CLOSED,

		OPEN

	}

	public boolean contains(Version version) {
		if (version == null) {
			throw new NullPointerException();
		}
		if (lowerType == BoundType.CLOSED) {
			if (version.compareTo(lower) < 0) {
				return false;
			}
		} else {
			if (version.compareTo(lower) <= 0) {
				return false;
			}
		}
		if (upperType == BoundType.CLOSED) {
			if (version.compareTo(upper) > 0) {
				return false;
			}
		} else {
			if (version.compareTo(upper) >= 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (lowerType == BoundType.CLOSED) {
			builder.append("[");
		} else {
			builder.append("(");
		}
		builder.append(lower.toString());
		builder.append(", ");
		builder.append(upper.toString());
		if (upperType == BoundType.CLOSED) {
			builder.append("]");
		} else {
			builder.append(")");
		}
		return builder.toString();
	}

}

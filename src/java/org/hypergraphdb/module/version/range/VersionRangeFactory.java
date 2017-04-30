package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

import lombok.experimental.UtilityClass;

/**
 * 
 * @author Dmitriy Shishkin
 */
@UtilityClass
public final class VersionRangeFactory {

	public static VersionRange ALL = new VersionRangeAll();
	public static VersionRange NONE = new VersionRangeNone();

	public static VersionRange openOpen(Version lower, Version upper) {
		if (lower == null) {
			throw new NullPointerException(
					"Lower bound is null, use explicit 'lessThenXXX' method");
		}
		if (upper == null) {
			throw new NullPointerException(
					"Upper bound is null, use explicit 'greaterThanXXX' method");
		}
		int compare = lower.compareTo(upper);
		if (compare == 0) {
			return none();
		} else if (compare < 0) {
			return new VersionRangeIntersection(
					new VersionRangeGreaterThanOpen(lower),
					new VersionRangeLessThanOpen(upper));
		} else {
			throw new IllegalArgumentException("Lower version should be less than upper version");
		}
	}

	public static VersionRange closedClosed(Version lower, Version upper) {
		if (lower == null) {
			throw new NullPointerException(
					"Lower bound is null, use explicit 'lessThenXXX' method");
		}
		if (upper == null) {
			throw new NullPointerException(
					"Upper bound is null, use explicit 'greaterThanXXX' method");
		}
		int compare = lower.compareTo(upper);
		if (compare == 0) {
			return single(lower);
		} else if (compare < 0) {
			return new VersionRangeIntersection(
					new VersionRangeGreaterThanClosed(lower),
					new VersionRangeLessThanClosed(upper));
		} else {
			throw new IllegalArgumentException("Lower version should be less than upper version");
		}
	}

	public static VersionRange openClosed(Version lower, Version upper) {
		if (lower == null) {
			throw new NullPointerException(
					"Lower bound is null, use explicit 'lessThenXXX' method");
		}
		if (upper == null) {
			throw new NullPointerException(
					"Upper bound is null, use explicit 'greaterThanXXX' method");
		}
		int compare = lower.compareTo(upper);
		if (compare == 0) {
			return none();
		} else if (compare < 0) {
			return new VersionRangeIntersection(
					new VersionRangeGreaterThanOpen(lower),
					new VersionRangeLessThanClosed(upper));
		} else {
			throw new IllegalArgumentException("Lower version should be less than upper version");
		}
	}

	public static VersionRange closedOpen(Version lower, Version upper) {
		if (lower == null) {
			throw new NullPointerException(
					"Lower bound is null, use explicit 'lessThenXXX' method");
		}
		if (upper == null) {
			throw new NullPointerException(
					"Upper bound is null, use explicit 'greaterThanXXX' method");
		}
		int compare = lower.compareTo(upper);
		if (compare == 0) {
			return none();
		} else if (compare < 0) {
			return new VersionRangeIntersection(
					new VersionRangeGreaterThanClosed(lower),
					new VersionRangeLessThanOpen(upper));
		} else {
			throw new IllegalArgumentException("Lower version should be less than upper version");
		}
	}

	public static VersionRange greaterThanOpen(Version bound) {
		if (bound == null) {
			throw new NullPointerException("Bound is null, use explicit 'all' method");
		}
		return new VersionRangeGreaterThanOpen(bound);
	}

	public static VersionRange greaterThanClosed(Version bound) {
		if (bound == null) {
			throw new NullPointerException("Bound is null, use explicit 'all' method");
		}
		return new VersionRangeGreaterThanClosed(bound);
	}

	public static VersionRange lessThanOpen(Version bound) {
		if (bound == null) {
			throw new NullPointerException("Bound is null, use explicit 'all' method");
		}
		return new VersionRangeLessThanOpen(bound);
	}

	public static VersionRange lessThanClosed(Version bound) {
		if (bound == null) {
			throw new NullPointerException("Bound is null, use explicit 'all' method");
		}
		return new VersionRangeLessThanClosed(bound);
	}

	public static VersionRange single(Version bound) {
		if (bound == null) {
			throw new NullPointerException("Bound is null, use explicit 'none' method");
		}
		return new VersionRangeSingle(bound);
	}

	public static VersionRange all() {
		return ALL;
	}

	public static VersionRange none() {
		return NONE;
	}

}

package org.hypergraphdb.module.version;

import java.util.Comparator;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import net.greypanther.natsort.SimpleNaturalComparator;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Value
@Builder
@EqualsAndHashCode(exclude = "metadata")
public final class Version implements Comparable<Version> {

	public final static Version ZERO = new Version(0, 0, 0, null, null);
	public final static Version ONE  = new Version(1, 0, 0, null, null);

	private static final String MAIN_SEPARATOR = ".";
	private static final String PRERELEASE_SEPARATOR = "-";
	private static final String METADATA_SEPARATOR = "+";
	private static final Comparator<String> COMPARATOR = SimpleNaturalComparator.getInstance();

	private final int major;
	private final int minor;
	private final int patch;
	private final String prerelease;
	private final String metadata;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(major);
		builder.append(MAIN_SEPARATOR);
		builder.append(minor);
		builder.append(MAIN_SEPARATOR);
		builder.append(patch);
		if (prerelease != null) {
			builder.append(PRERELEASE_SEPARATOR);
			builder.append(prerelease);
		}
		if (metadata != null) {
			builder.append(METADATA_SEPARATOR);
			builder.append(metadata);
		}
		return builder.toString();
	}

	public int compareTo(Version other) {
		int majorDiff = Integer.compare(major, other.major);
		if (majorDiff != 0) {
			return majorDiff;
		}
		int minorDiff = Integer.compare(minor, other.minor);
		if (minorDiff != 0) {
			return minorDiff;
		}
		int patchDiff = Integer.compare(patch, other.patch);
		if (patchDiff != 0) {
			return patchDiff;
		}
		if (prerelease == null) {
			if (other.prerelease == null) {
				return 0;
			} else {
				return 1;
			}
		} else {
			if (other.prerelease == null) {
				return -1;
			} else {
				return COMPARATOR.compare(prerelease, other.prerelease);
			}
		}
	}

}

package org.hypergraphdb.module.version.range;

import java.util.Objects;

import org.hypergraphdb.module.version.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Getter
@AllArgsConstructor
class VersionRangeSingle implements VersionRange {

	protected Version version;

	public boolean contains(Version version) {
		return Objects.equals(this.version, version);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOWER_CLOSED_BRACKET);
		builder.append(version.toString());
		builder.append(UPPER_CLOSED_BRACKET);
		return builder.toString();
	}

}

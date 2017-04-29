package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
abstract class VersionRangeGreaterThan implements VersionRange {

	protected Version version;

	protected abstract char getBracket();

}

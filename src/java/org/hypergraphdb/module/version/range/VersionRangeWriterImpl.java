package org.hypergraphdb.module.version.range;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionRangeWriterImpl implements VersionRangeWriter {

	public String write(VersionRange input) {
		if (input == null) {
			throw new NullPointerException("Input version range can't be null");
		}
		return input.toString();
	}

}

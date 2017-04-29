package org.hypergraphdb.module.version;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionWriterImpl implements VersionWriter {

	public String write(Version input) {
		if (input == null) {
			throw new NullPointerException("Input version can't be null");
		}
		return input.toString();
	}

}

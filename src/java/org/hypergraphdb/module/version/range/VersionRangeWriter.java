package org.hypergraphdb.module.version.range;

import org.hypergraphdb.app.management.Writer;

/**
 * 
 * @author Dmitriy Shishkin
 */
public interface VersionRangeWriter extends Writer<VersionRange> {

	/**
	 * 
	 * @param input
	 * @return
	 * @throws VersionWriterException
	 *             if write fails
	 */
	String write(VersionRange input);

}

package org.hypergraphdb.module.version;

import org.hypergraphdb.app.management.Writer;

/**
 * 
 * @author Dmitriy Shishkin
 */
public interface VersionWriter extends Writer<Version> {

	/**
	 * 
	 * @param input
	 * @return
	 * @throws VersionWriterException
	 *             if write fails
	 */
	String write(Version input);

}

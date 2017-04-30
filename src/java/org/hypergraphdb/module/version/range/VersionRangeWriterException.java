package org.hypergraphdb.module.version.range;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionRangeWriterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VersionRangeWriterException() {
		super();
	}

	public VersionRangeWriterException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionRangeWriterException(String message) {
		super(message);
	}

	public VersionRangeWriterException(Throwable cause) {
		super(cause);
	}

}

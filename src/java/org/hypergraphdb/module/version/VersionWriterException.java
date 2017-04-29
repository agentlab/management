package org.hypergraphdb.module.version;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionWriterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VersionWriterException() {
		super();
	}

	public VersionWriterException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionWriterException(String message) {
		super(message);
	}

	public VersionWriterException(Throwable cause) {
		super(cause);
	}

}

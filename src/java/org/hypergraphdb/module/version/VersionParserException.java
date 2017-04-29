package org.hypergraphdb.module.version;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VersionParserException() {
		super();
	}

	public VersionParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionParserException(String message) {
		super(message);
	}

	public VersionParserException(Throwable cause) {
		super(cause);
	}

}

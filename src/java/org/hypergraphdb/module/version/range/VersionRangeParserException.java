package org.hypergraphdb.module.version.range;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionRangeParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VersionRangeParserException() {
		super();
	}

	public VersionRangeParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionRangeParserException(String message) {
		super(message);
	}

	public VersionRangeParserException(Throwable cause) {
		super(cause);
	}

}

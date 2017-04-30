package org.hypergraphdb.module.version.range;

import org.hypergraphdb.module.version.Version;
import org.hypergraphdb.module.version.VersionParser;
import org.hypergraphdb.module.version.VersionParserException;
import org.hypergraphdb.module.version.VersionParserImpl;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionRangeParserImpl implements VersionRangeParser {

	private static final String COMMA = ",";

	private VersionParser versionParser = new VersionParserImpl();

	public VersionRange parse(String input) {
		if (input == null) {
			throw new NullPointerException("Input string can't be null");
		}
		String[] parts = input.split(COMMA);
		if (parts.length != 2) {
			throw new VersionRangeParserException(
					"Version range should contain exactly 2 version separated by comma");
		}
		VersionRangeGreaterThan lower = parseLower(parts[0].trim());
		VersionRangeLessThan upper = parseUpper(parts[1].trim());
		return new VersionRangeIntersection(lower, upper);
	}

	private VersionRangeGreaterThan parseLower(String input) {
		String versionString = input.substring(1).trim();
		char first = input.charAt(0);
		if (first == '(') {
			return new VersionRangeGreaterThanOpen(parseVersion(versionString));
		} else if (first == '[') {
			return new VersionRangeGreaterThanClosed(parseVersion(versionString));
		} else {
			throw new VersionRangeParserException(
					"Lower range should start with '(' or '[', but was " + first);
		}
	}

	private VersionRangeLessThan parseUpper(String input) {
		int length = input.length();
		String versionString = input.substring(0, length - 1).trim();
		char last = input.charAt(length - 1);
		if (last == ')') {
			return new VersionRangeLessThanOpen(parseVersion(versionString));
		} else if (last == ']') {
			return new VersionRangeLessThanClosed(parseVersion(versionString));
		} else {
			throw new VersionRangeParserException(
					"Upper range should ends with ')' or ']', but was " + last);
		}
	}

	private Version parseVersion(String input) {
		try {
			return versionParser.parse(input);
		} catch (VersionParserException e) {
			throw new VersionRangeParserException(e);
		}
	}

}

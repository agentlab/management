package org.hypergraphdb.app.management;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class VersionParser {

	// @formatter:off
	private static final Pattern PATTERN = Pattern.compile(
			"(0|[1-9]+)" + 													// major
			"(?:\\.(0|[1-9]+))?" +											// minor
			"(?:\\.(0|[1-9]+))?" +											// patch
			"(?:\\-((?:0|[A-Za-z1-9-]+)(?:\\.(?:0|[A-Za-z1-9-]+))*))?" +	// pre-release
			"(?:\\+((?:[0-9A-Za-z-]+)(?:\\.(?:[0-9A-Za-z-]+))*))?"			// metadata
	);
	// @formatter:on

	public Version parse(String input) {
		if (input == null) {
			throw new NullPointerException("Input string can't be null");
		}
		Matcher matcher = PATTERN.matcher(input);
		if (matcher.matches()) {
			String major = matcher.group(1);
			String minor = matcher.group(2);
			String patch = matcher.group(3);
			String prerelease = matcher.group(4);
			String metadata = matcher.group(5);
			return Version
					.builder()
					.major(parseInteger(major))
					.minor(parseInteger(minor))
					.patch(parseInteger(patch))
					.prerelease(prerelease)
					.metadata(metadata)
					.build();
		}
		throw new VersionParserException(
				"Input string " + input
						+ " does not match SemVer pattern, see http://semver.org/spec/v2.0.0.html");
	}

	private int parseInteger(String group) {
		return group != null ? Integer.parseInt(group) : 0;
	}

}

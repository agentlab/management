package org.hypergraphdb.app.management;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * 
 * @author Dmitriy Shishkin
 */
@RunWith(Parameterized.class)
public class VersionParserTest {

	@Parameters(name = "{0}")
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			// @formatter:off
            { null,                          NullPointerException.class },
            { "",                            VersionParserException.class },
			// Zero version
            { "0",                           new Version(0, 0, 0, null, null) },
            { "0.0",                         new Version(0, 0, 0, null, null) },
            { "0.0.0",                       new Version(0, 0, 0, null, null) },
            { "0.0.0-0",                     new Version(0, 0, 0, "0", null) },
            { "0.0.0-0.0",                   new Version(0, 0, 0, "0.0", null) },
            // First version                                                   
            { "1",                           new Version(1, 0, 0, null, null) },
            { "1.1",                         new Version(1, 1, 0, null, null) },
            { "1.1.1",                       new Version(1, 1, 1, null, null) },
            { "1.1.1-1",                     new Version(1, 1, 1, "1", null) },
            { "1.1.1-1.1",                   new Version(1, 1, 1, "1.1", null) },
            // Trailing space
            { " ",                           VersionParserException.class },
            { "1 ",                          VersionParserException.class },
            { "1.2 ",                        VersionParserException.class },
            { "1.2.3 ",                      VersionParserException.class },
            { "1.2.3-4 ",                    VersionParserException.class },
            // Leading zero                                               
            { "01",                          VersionParserException.class },
            { "1.01",                        VersionParserException.class },
            { "1.1.01",                      VersionParserException.class },
            { "1.1.1-01",                    VersionParserException.class },
            // Trailing dot                                                
            { ".",                           VersionParserException.class },
            { "1.",                          VersionParserException.class },
            { "1.2.",                        VersionParserException.class },
            { "1.2.3.",                      VersionParserException.class },
            { "1.2.3-4.",                    VersionParserException.class },
            { "1.0.0-alpha.",                VersionParserException.class },
            // Letters                                                    
            { "a",                           VersionParserException.class },
            { "1.b",                         VersionParserException.class },
            { "1.2.c",                       VersionParserException.class },
            { "1.2.3-d",                     new Version(1, 2, 3, "d", null) },
            // Metadata
            { "1.0.0-alpha",                 new Version(1, 0, 0, "alpha", null) },
            { "1.0.0-alpha+001",             new Version(1, 0, 0, "alpha", "001") },
            { "1.0.0+20130313144700",        new Version(1, 0, 0, null, "20130313144700") },
            { "1.0.0-beta+exp.sha.5114f85",  new Version(1, 0, 0, "beta", "exp.sha.5114f85") },
            { "1.0.0-beta-1+exp-1",          new Version(1, 0, 0, "beta-1", "exp-1") },
        	// @formatter:on
		});
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Parameter(0)
	public String input;
	
	@Parameter(1)
	public Object result;

	@Test
	@SuppressWarnings("unchecked")
	public void evaluate() {
		// Given
		if (result instanceof Class) {
			exception.expect((Class<? extends Throwable>) result);
		}
		VersionParser pareser = new VersionParser();
		// When
		Version version = pareser.parse(input);
		// Then
		assertThat(version, equalTo(result));
	}
}

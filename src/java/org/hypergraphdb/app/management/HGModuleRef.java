package org.hypergraphdb.app.management;

import java.net.URI;

import lombok.Value;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Value
public class HGModuleRef {

	private URI name;
	private VersionRange version;

}

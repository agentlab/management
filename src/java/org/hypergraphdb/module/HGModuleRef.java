package org.hypergraphdb.module;

import java.net.URI;

import org.hypergraphdb.module.version.range.VersionRange;

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

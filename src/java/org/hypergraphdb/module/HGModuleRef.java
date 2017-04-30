package org.hypergraphdb.module;

import org.hypergraphdb.module.version.range.VersionRange;

import lombok.Value;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Value
public class HGModuleRef {

	private String name;
	private VersionRange version;

	@Override
	public String toString() {
		return name + ":" + version.toString();
	}

	public HGModuleRef(String name, VersionRange version) {
		this.name = name;
		this.version = version;
	}

	public HGModuleRef(Class<? extends HGModule> type, VersionRange version) {
		this.name = type.getName();
		this.version = version;
	}

}

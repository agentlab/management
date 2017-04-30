package org.hypergraphdb.module;

/**
 * 
 * @author Dmitriy Shishkin
 */
public enum HGModuleState {
	
	CREATED,

	INSTALLED,

	RESOLVED,

	ACTIVE,

	FAILURE;

	public boolean isResoved() {
		return (this == RESOLVED) || (this == ACTIVE) || (this == FAILURE);
	}

	public boolean isActive() {
		return this == ACTIVE;
	}

	public boolean isFailure() {
		return this == FAILURE;
	}

}

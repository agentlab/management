package org.hypergraphdb.module;

import java.util.List;

public class UnresolvedDependenciesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final List<HGModuleRef> unresolved;

	public UnresolvedDependenciesException(List<HGModuleRef> unresolved) {
		this.unresolved = unresolved;
	}

	@Override
	public String getMessage() {
		return "Unresolved dependencies: " + unresolved.toString();
	}

}

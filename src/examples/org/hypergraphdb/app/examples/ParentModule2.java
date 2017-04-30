package org.hypergraphdb.app.examples;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.module.HGModule;
import org.hypergraphdb.module.version.Version;

public class ParentModule2 extends HGModule {

	@Override
	public Version getVersion() {
		return Version.builder().major(1).build();
	}

	@Override
	public void activate(HyperGraph graph) {
		System.out.println("Activate module [" + this + "]...");
	}

	@Override
	public void modify(HyperGraph graph) {
		System.out.println("Modify module [" + this + "]...");
	}

	@Override
	public void deactivate(HyperGraph graph) {
		System.out.println("Deactivate module [" + this + "]...");
	}

}

package org.hypergraphdb.app.examples;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.module.HGModule;
import org.hypergraphdb.module.version.Version;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParentModule extends HGModule {

	@Override
	public Version getVersion() {
		return Version.builder().major(2).build();
	}

	@Override
	public void activate(HyperGraph graph) {
		log.info("Code for activate module [{}]...", this);
	}

	@Override
	public void modify(HyperGraph graph) {
		log.info("Code for modify module [{}]...", this);
	}

	@Override
	public void deactivate(HyperGraph graph) {
		log.info("Code for deactivate module [{}]...", this);
	}

}

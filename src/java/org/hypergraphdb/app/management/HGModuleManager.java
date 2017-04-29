package org.hypergraphdb.app.management;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;

/**
 * 
 * @author Dmitriy Shishkin
 */
public class HGModuleManager {

	public boolean isInstalled(HyperGraph graph, HGModuleRef ref) {
		return hg.findOne(
				graph,
				hg.and(hg.typePlus(HGModule.class), hg.eq("name", ref.getName()))) != null;
	}

	public void install(HyperGraph graph, HGModule module) {

	}

	public void resolve(HyperGraph graph, HGModule module) {

	}

}

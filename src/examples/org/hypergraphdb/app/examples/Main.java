package org.hypergraphdb.app.examples;

import org.hypergraphdb.HGEnvironment;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.module.HGModule;
import org.hypergraphdb.module.HGModuleManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	private static final String PATH = "test_hgdb/";

	public static void main(String[] args) {
		HyperGraph graph = null;
		try {
			log.info("DB exists: " + HGEnvironment.exists(PATH));
			log.info("DB open:   " + HGEnvironment.isOpen(PATH));
			graph = new HyperGraph(PATH);
			log.info("DB open:   " + HGEnvironment.isOpen(PATH));
			populate(graph);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (graph != null) {
				graph.close();
			}
		}
	}

	private static void populate(HyperGraph graph) {
		HGModuleManager manager = new HGModuleManager(graph);
		registerModule(graph, manager, new ChildModule());
		registerModule(graph, manager, new ParentModule());
	}

	private static void registerModule(HyperGraph graph, HGModuleManager manager, HGModule module) {
		try {
			log.info("=======================================");
			manager.register(module);
		} catch (Exception e) {
			log.warn("Failed to register module [{}]", module, e);
		}
		log.info("State: " + module.getState());
	}

}

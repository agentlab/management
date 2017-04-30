package org.hypergraphdb.module;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hypergraphdb.HGGraphHolder;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Slf4j
public class HGModuleManager implements HGGraphHolder {

	@Getter
	@Setter
	HyperGraph hyperGraph;

	private final Map<HGModule, List<HGModuleRef>> module2unresolved = new HashMap<>();
	private final Map<HGModuleRef, List<HGModule>> unresolved2module = new HashMap<>();

	public HGModuleManager(HyperGraph hyperGraph) {
		this.hyperGraph = hyperGraph;
	}

	private void putModule2unresolved(HGModule module, HGModuleRef refs) {
		List<HGModuleRef> list = module2unresolved.get(module);
		if (list == null) {
			list = new ArrayList<>();
			module2unresolved.put(module, list);
		}
		list.add(refs);
	}

	private void removeModule2unresolved(HGModule module, HGModuleRef ref) {
		List<HGModuleRef> list = module2unresolved.get(module);
		if (list == null) {
			return;
		}
		list.remove(ref);
	}

	private void putUnresolved2module(HGModuleRef ref, HGModule module) {
		List<HGModule> list = unresolved2module.get(ref);
		if (list == null) {
			list = new ArrayList<>();
			unresolved2module.put(ref, list);
		}
		list.add(module);
	}

	private void removeUnresolved2module(HGModuleRef ref, HGModule module) {
		List<HGModule> list = unresolved2module.get(ref);
		if (list == null) {
			return;
		}
		list.remove(module);
	}

	private void registerUnresolved(HGModule module, List<HGModuleRef> unresolved) {
		unresolved.forEach(ref -> {
			putModule2unresolved(module, ref);
			putUnresolved2module(ref, module);
		});
	}

	private void unregisterUnresolved(HGModuleRef ref) {
		List<HGModule> modules = unresolved2module.get(ref);
		if (modules != null) {
			modules.forEach(module -> {
				removeModule2unresolved(module, ref);
				removeUnresolved2module(ref, module);
			});
		}
	}

	private boolean isInstalled(String name) {
		return findModule(name) != null;
	}

	private HGHandle findModule(String name) {
		return hg.findOne(
				hyperGraph,
				hg.and(hg.typePlus(HGModule.class), hg.eq(HGModule.NAME, name)));
	}

	private HGModule getModule(String name) {
		HGHandle handle = findModule(name);
		if (handle != null) {
			return hyperGraph.get(handle);
		} else {
			return null;
		}
	}

	public void register(HGModule module) {
		install(module);
		resolve(module);
		activate(module);
	}

	private void install(HGModule module) {
		log.debug("Install module [{}]...", module);
		if (!isInstalled(module.getName())) {
			module.setState(HGModuleState.INSTALLED);
			hyperGraph.add(module);
			log.debug("Install module [{}] successfully", module);
		} else {
			log.debug("Install module [{}] successfully, already installed", module);
		}
	}

	private void resolve(String name) {
		HGModule module = getModule(name);
		if (module == null) {
			throw new IllegalStateException("Module " + name + " is not installed");
		}
		resolve(module);
	}

	private void resolve(HGModule module) {
		log.debug("Resolve module [{}]...", module);
		// Find unresolved dependencies
		List<HGModuleRef> unresolved = module.getDependencies().stream().filter(ref -> {
			HGModule mod = getModule(ref.getName());
			if (mod == null) {
				return true;
			}
			return !mod.getState().isResoved();
		}).collect(toList());
		// throw if there are some unresolved
		if (!unresolved.isEmpty()) {
			registerUnresolved(module, unresolved);
			module.setState(HGModuleState.FAILURE);
			hyperGraph.update(module);
			throw new UnresolvedDependenciesException(unresolved);
		}
		module.setState(HGModuleState.RESOLVED);
		hyperGraph.update(module);
		log.debug("Resolve module [{}] successfully", module);

		// Previously unresolved modules
		List<HGModule> possible = unresolved2module.get(module);
		if (possible != null) {
			log.debug("Previously unresolved modules: {}", possible);
		}
	}

	private void activate(HGModule module) {
		log.debug("Activate module [{}]...", module);
		module.activate(hyperGraph);
		module.setState(HGModuleState.ACTIVE);
		hyperGraph.update(module);
		log.debug("Activate module [{}] successfully", module);
	}

	@SuppressWarnings("unchecked")
	private HGModule getModuleInstance(HGModuleRef ref) {
		try {
			Class<? extends HGModule> type = (Class<? extends HGModule>) Class
					.forName(ref.getName());
			return type.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

}

package org.hypergraphdb.app.examples;

import java.util.ArrayList;
import java.util.List;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.module.HGModule;
import org.hypergraphdb.module.HGModuleRef;
import org.hypergraphdb.module.version.Version;
import org.hypergraphdb.module.version.range.VersionRangeFactory;

public class ChildModule extends HGModule {

	@Override
	public Version getVersion() {
		return Version.builder().major(2).build();
	}

	@Override
	public List<HGModuleRef> getDependencies() {
		List<HGModuleRef> result = new ArrayList<>();
		result.add(
				new HGModuleRef(
						ParentModule.class,
						VersionRangeFactory.single(Version.builder().major(2).build())));
		result.add(
				new HGModuleRef(
						ParentModule2.class,
						VersionRangeFactory.single(Version.builder().major(1).build())));
		return result;
	}

	@Override
	public void activate(HyperGraph graph) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(HyperGraph graph) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate(HyperGraph graph) {
		// TODO Auto-generated method stub

	}

}

package org.hypergraphdb.module;

import java.util.Collections;
import java.util.List;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.module.version.Version;
import org.hypergraphdb.module.version.range.VersionRangeFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Getter
@NoArgsConstructor
public abstract class HGModule {

	public static final String NAME = "name";

	private Version version;

	@Setter(value = AccessLevel.PACKAGE)
	private HGModuleState state = HGModuleState.CREATED;

	public abstract void activate(HyperGraph graph);

	public abstract void modify(HyperGraph graph);

	public abstract void deactivate(HyperGraph graph);

	public final String getName() {
		return this.getClass().getName();
	}

	public void setName(String name) {
	}

	public List<HGModuleRef> getDependencies() {
		return Collections.emptyList();
	}

	@Override
	public String toString() {
		return getName() + ":" + getVersion().toString();
	}

	public final HGModuleRef getRef() {
		return new HGModuleRef(getName(), VersionRangeFactory.single(version));
	}

}

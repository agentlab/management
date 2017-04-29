package org.hypergraphdb.app.management;

import java.net.URI;
import java.util.List;

import org.hypergraphdb.HyperGraph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Dmitriy Shishkin
 */
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public abstract class HGModule implements PresenceLifecycle {

	private URI uri;
	private Version version;
	private List<HGModuleRef> dependencies;
	private HGModuleState state;
	
	public void install(HyperGraph graph) {
		state = HGModuleState.INSTALLED;
	}
	
	public void resolve(HyperGraph graph){
		
	}

}

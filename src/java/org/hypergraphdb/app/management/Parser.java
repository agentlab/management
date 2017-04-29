package org.hypergraphdb.app.management;

public interface Parser<T> {

	T parse(String input);

}

package org.hypergraphdb.app.management;

public interface Writer<T> {

	String write(T input);

}

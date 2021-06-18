package com.test.task.registration.service;

public interface Enricher<T, P> {

	void enrich(final T forEnrich, final P source);

}

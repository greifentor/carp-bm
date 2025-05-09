package de.ollie.carp.bm.shell.command;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	private AtomicInteger value = new AtomicInteger();

	public int getValue() {
		return value.get();
	}

	public Counter inc() {
		value.incrementAndGet();
		return this;
	}
}

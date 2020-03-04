package com.github.dkurata38.monad_java.option;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class None<A> extends Option<A> {
	@Override
	Boolean isDefined() {
		return false;
	}

	@Override
	Boolean isEmpty() {
		return true;
	}

	@Override
	Boolean nonEmpty() {
		return false;
	}

	@Override
	Option<A> orElse(Supplier<Option<A>> alternative) {
		return alternative.get();
	}

	@Override
	A getOrElse(A defaultValue) {
		return defaultValue;
	}

	@Override
	A get() {
		throw new NoSuchElementException();
	}

	@Override
	<B> B fold(Supplier<A> ifEmpty, Function<A, B> f) {
		return f.apply(ifEmpty.get());
	}

	@Override
	<B> Option<B> map(Function<A, B> f) {
		return new None<>();
	}

	@Override
	<B> Option<B> flatMap(Function<A, Option<B>> f) {
		return new None<>();
	}

	@Override
	void foreach(Consumer<A> f) {

	}

	@Override
	<B> Option<B> collect(Function<A, B> f) {
		return new None<>();
	}

	@Override
	Option<A> filter(Predicate<A> p) {
		return new None<>();
	}

	@Override
	Option<A> filterNot(Predicate<A> p) {
		return new None<>();
	}

	@Override
	Boolean exists(Predicate<A> p) {
		return false;
	}

	@Override
	Boolean forall(Predicate<A> p) {
		return false;
	}

	@Override
	Boolean contains(A elem) {
		return false;
	}

	@Override
	List<A> toList() {
		return Collections.emptyList();
	}

	@Override
	public Iterator<A> iterator() {
		return toList().iterator();
	}
}

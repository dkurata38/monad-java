package com.github.dkurata38.monad_java.option;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Some<A> extends Option<A> {
	private final A value;

	private Some(A value) {
		this.value = value;
	}

	@Override
	Boolean isDefined() {
		return true;
	}

	@Override
	Boolean isEmpty() {
		return false;
	}

	@Override
	Boolean nonEmpty() {
		return true;
	}

	@Override
	Option<A> orElse(Supplier<Option<A>> alternative) {
		return this;
	}

	@Override
	A getOrElse(A defaultValue) {
		return value;
	}

	@Override
	A get() {
		return value;
	}

	@Override
	<B> B fold(Supplier<A> ifEmpty, Function<A, B> f) {
		return f.apply(value);
	}

	@Override
	<B> Option<B> map(Function<A, B> f) {
		return new Some<>(f.apply(value));
	}

	@Override
	<B> Option<B> flatMap(Function<A, Option<B>> f) {
		return f.apply(value);
	}

	@Override
	void foreach(Consumer<A> f) {
		f.accept(value);
	}

	@Override
	<B> Option<B> collect(Function<A, B> f) {
		return new Some<>(f.apply(value));
	}

	@Override
	Option<A> filter(Predicate<A> p) {
		return p.test(value) ? new Some<>(value) : None.of();
	}

	@Override
	Option<A> filterNot(Predicate<A> p) {
		return p.test(value) ? None.of() : new Some<>(value);
	}

	@Override
	Boolean exists(Predicate<A> p) {
		return p.test(value);
	}

	@Override
	Boolean forall(Predicate<A> p) {
		return p.test(value);
	}

	@Override
	Boolean contains(A elem) {
		return value.equals(elem);
	}

	@Override
	List<A> toList() {
		return Collections.singletonList(value);
	}

	@Override
	public Iterator<A> iterator() {
		return toList().iterator();
	}

	public static <A> Option<A> of(A value) {
		Objects.requireNonNull(value);
		return new Some<>(value);
	}
}

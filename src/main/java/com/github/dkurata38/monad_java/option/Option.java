package com.github.dkurata38.monad_java.option;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class Option<A> implements Iterable<A>, Serializable {

	/**
	 * True if not empty
	 */
	abstract Boolean isDefined();

	/**
	 * True if empty
	 */
	abstract Boolean isEmpty();

	/**
	 * True if not empty
	 */
	abstract Boolean nonEmpty();

	/**
	 * Evaluate and return alternate optional value if empty
	 */
	abstract Option<A> orElse(Supplier<Option<A>> alternative);

	/**
	 * Evaluate and return alternate value if empty
	 */
	abstract A getOrElse(A defaultValue);

	/**
	 * Return value, throw exception if empty
	 */
	abstract A get();

	/**
	 * Apply function on optional value, return default if empty
	 */
	abstract <B> B fold(Supplier<A> ifEmpty, Function<A, B> f);

	/**
	 * Apply a function on the optional value
	 */
	abstract <B> Option<B> map(Function<A, B> f);

	/**
	 * Same as map but function must return an optional value
	 */
	abstract <B> Option<B> flatMap(Function<A, Option<B>> f);

	/**
	 * Apply a procedure on option value
	 */
	abstract void foreach(Consumer<A> f);

	/**
	 * Apply partial pattern match on optional value
	 */
	abstract <B> Option<B> collect(Function<A, B> f);

	/**
	 * An optional value satisfies predicate
	 */
	abstract Option<A> filter(Predicate<A> p);

	/**
	 * An optional value doesn't satisfy predicate
	 */
	abstract Option<A> filterNot(Predicate<A> p);

	/**
	 * Apply predicate on optional value, or false if empty
	 */
	abstract Boolean exists(Predicate<A> p);

	/**
	 * Apply predicate on optional value, or true if empty
	 */
	abstract Boolean forall(Predicate<A> p);

	/**
	 * Checks if value equals optional value, or false if empty
	 */
	abstract Boolean contains(A elem);

	/*
	  Combine two optional values to make a paired optional value
	 */
//	abstract <B> void zip();

	/*
	  Split an optional pair to two optional values
	 */
//	abstract void unzip();

	/*
	  Split an optional triple to three optional values
	 */
//	abstract void unzip3();

	/**
	 * Unary list of optional value, otherwise the empty list
	 */
	abstract List<A> toList();

	public static <A> Option<A> wrap(A value) {
		if (Objects.nonNull(value)) {
			return Some.of(value);
		} else {
			return None.of();
		}
	}

	public static <A> Option<A> from(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<A> optional) {
		return optional
				.map(Some::of)
				.orElse(None.of());
	}
}

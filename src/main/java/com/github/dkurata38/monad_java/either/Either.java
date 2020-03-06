package com.github.dkurata38.monad_java.either;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import com.github.dkurata38.monad_java.option.Option;

public abstract class Either<L, R> {
	abstract boolean isLeft();
	abstract boolean isRight();

	/**
	 * Checks if value equals optional value, or false if empty
	 */
	abstract Boolean contains(R elem);
	/**
	 * Apply predicate on optional value, or false if empty
	 */
	abstract Boolean exists(Predicate<R> p);
	/**
	 * An optional value satisfies predicate
	 */
	abstract <L1> Either<L1, R> filterOrElse(Predicate<R> p, L1 zero);

	abstract <L1, R1> Either<L1, R1> flatMap(Function<R, Either<L1, R1>> f);

	abstract <T> T fold(Function<L, T> fl, Function<R, T> fr);

	abstract Boolean forall(Predicate<R> p);

	abstract void forEach(Consumer<R> c);
	abstract R getOrElse(R or);
	// joinRight/joinLeft

	abstract void left();

	abstract <R1> Either<L, R1> map(Function<R, R1> f);

	abstract R merge();

	abstract void right();

	abstract Either<R, L> swap();

	abstract Option<L> toOption();

	abstract List<L> toList();

	// abstract toTry();
}

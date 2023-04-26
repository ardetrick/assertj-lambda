package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import com.jnape.palatable.lambda.adt.Maybe;
import com.jnape.palatable.lambda.io.IO;

public interface Assertions extends InstanceOfAssertFactories {

    static <L, R> EitherAssert<L, R> assertThat(Either<L, R> actual) {
        return new EitherAssert<>(actual);
    }

    static <VALUE> IOAssert<VALUE> assertThat(IO<VALUE> actual) {
        return new IOAssert<>(actual);
    }

    static <VALUE> MaybeAssert<VALUE> assertThat(Maybe<VALUE> actual) {
        return new MaybeAssert<>(actual);
    }

}

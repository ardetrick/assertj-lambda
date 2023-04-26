package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;

import static com.jnape.palatable.lambda.io.IO.io;

public abstract class AbstractEitherAssert<
        SELF extends AbstractEitherAssert<SELF, LEFT, RIGHT>,
        LEFT,
        RIGHT
        > extends AbstractAssert<SELF, Either<LEFT, RIGHT>> {

    protected AbstractEitherAssert(Either<LEFT, RIGHT> leftrightEither, Class<?> selfType) {
        super(leftrightEither, selfType);
    }

    public SELF isRight() {
        actual.match(
                l -> io(() -> failWithMessage("Expected Right found Left", l)),
                r -> io(() -> {})
        ).unsafePerformIO();
        return myself;
    }

    public SELF isLeft() {
        actual.match(
                l -> io(() -> {}),
                r -> io(() -> failWithMessage("Expected Left found Right: <%s>", r))
        ).unsafePerformIO();
        return myself;
    }

    public RIGHT getRightOrFail() {
        return actual.biMap(
                        l -> isRight(),
                        r -> r)
                .projectB()
                .match($ -> null, $ -> $);
    }

    public LEFT getLeftOrFail() {
        return actual.biMap(
                        l -> l,
                        r -> isLeft())
                .projectA()
                .match($ -> null, $ -> $);
    }


}

package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactory;

import java.util.function.Consumer;

import static com.jnape.palatable.lambda.io.IO.io;

public class EitherAssert<L, R> extends AbstractAssert<EitherAssert<L, R>, Either<L, R>> {

    public EitherAssert(Either<L, R> either) {
        super(either, EitherAssert.class);
    }

    public EitherAssert<L, R> isRight() {
        actual.match(
                l -> io(() -> failWithMessage("Expected Right found Left", l)),
                r -> io(() -> {})
        ).unsafePerformIO();
        return this;
    }

    public EitherAssert<L, R> isLeft() {
        actual.match(
                l -> io(() -> {}),
                r -> io(() -> failWithMessage("Expected Left found Right: <%s>", r))
        ).unsafePerformIO();
        return this;
    }

    public L left() {
        return actual.match(
                l -> l,
                r -> null
        );
    }

    public R right() {
        return actual.match(
                r -> null,
                l -> l
        );
    }

    public EitherAssert<L, R> hasRightValueSatisfying(Consumer<R> additionalChecks) {
        isRight();
        actual.toMaybe().toOptional().ifPresent(additionalChecks);
        return this;
    }

    public AbstractObjectAssert<?, R> extractingRightAs() {
        return actual.match(
                this::throwExpectedRightFoundLeftFailure,
                Assertions::assertThat
        );
    }

    private AbstractObjectAssert<?, R> throwExpectedRightFoundLeftFailure(L left) {
        throw failure("Expected Right found Left", left);
    }

    public <ASSERT extends AbstractAssert<?, ?>> ASSERT extractingRightAs(InstanceOfAssertFactory<?, ASSERT> assertFactory) {
        return actual.biMap(
                        l -> isRight(),
                        r -> extracting($ -> r, assertFactory))
                .projectB()
                .match($ -> null, $ -> $);
    }

    public <ASSERT extends AbstractAssert<?, ?>> ASSERT extractingLeftAs(InstanceOfAssertFactory<?, ASSERT> assertFactory) {
        return actual.biMap(
                        l -> extracting($ -> l, assertFactory),
                        r -> isLeft())
                .projectA()
                .match($ -> null, $ -> $);
    }

}

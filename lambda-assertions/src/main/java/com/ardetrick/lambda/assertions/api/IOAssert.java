package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import com.jnape.palatable.lambda.adt.Maybe;
import com.jnape.palatable.lambda.io.IO;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.InstanceOfAssertFactory;

import static com.jnape.palatable.lambda.adt.Maybe.just;
import static com.jnape.palatable.lambda.adt.Maybe.nothing;

public class IOAssert<VALUE> extends AbstractAssert<IOAssert<VALUE>, IO<VALUE>> {

    Maybe<Either<Throwable, VALUE>> maybeCachedResult = nothing();

    protected IOAssert(IO<VALUE> io) {
        super(io, IOAssert.class);
    }

    public IOAssert<VALUE> completesNormally() {
        return idempotentPerformIO()
                .match(
                        throwable -> {
                            failWithMessage("IO did not complete normally. Threw exception <%s>", throwable);
                            return this;
                        },
                        value -> this
                );
    }

    public VALUE normalResult() {
        return idempotentPerformIO()
                .match(t -> null,
                        value -> value
                );
    }

    public IOAssert<VALUE> completesExceptionally() {
        return idempotentPerformIO()
                .match(
                        throwable -> this,
                        value -> {
                            failWithMessage("IO did not complete exceptionally. Result: <%s>", value);
                            return this;
                        }
                );
    }

    public <ASSERT extends AbstractAssert<ASSERT, ?>> ASSERT extractingResult(InstanceOfAssertFactory<?, ASSERT> assertFactory) {
        return idempotentPerformIO().match(
                left -> {
                    completesNormally();
                    return null;
                },
                right -> super.extracting($ -> right, assertFactory)
        );
    }

    private Either<Throwable, VALUE> idempotentPerformIO() {
        return maybeCachedResult.match(
                absent -> {
                    var result = actual.safe().unsafePerformIO();
                    this.maybeCachedResult = just(result);
                    return result;
                },
                $ -> $
        );
    }

}

package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.InstanceOfAssertFactories;

import java.util.function.Function;

public interface ExtractableLeftAsException extends ExtractableLeft<Exception, Exception, AbstractThrowableAssert<?, Exception>> {

    default AssertFactory<Exception, AbstractThrowableAssert<?, Exception>> leftAssertFactory() {
        return throwable -> InstanceOfAssertFactories.throwable(Exception.class).createAssert(throwable);
    }

    default Function<Either<Exception, ?>, Exception> leftExtractor(Exception l) {
        return $ -> l;
    }

}

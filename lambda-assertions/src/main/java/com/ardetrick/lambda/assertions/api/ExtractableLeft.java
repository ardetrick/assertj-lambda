package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AssertFactory;

import java.util.function.Function;

public interface ExtractableLeft<LEFT, LEFT_EXTRACTED, LEFT_EXTRACTED_ASSERT extends AbstractAssert<?, LEFT_EXTRACTED>> {

    Function<Either<LEFT, ?>, LEFT_EXTRACTED> leftExtractor(LEFT left);

    AssertFactory<LEFT_EXTRACTED, LEFT_EXTRACTED_ASSERT> leftAssertFactory();

}

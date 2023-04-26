package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;

import java.util.function.Function;

public interface ExtractableRight<RIGHT, RIGHT_EXTRACTED, RIGHT_EXTRACTED_ASSERT extends Assert<?, RIGHT_EXTRACTED>> {

    Function<Either<?, RIGHT>, RIGHT_EXTRACTED> rightExtractor(RIGHT right);

    AssertFactory<RIGHT_EXTRACTED, RIGHT_EXTRACTED_ASSERT> rightAssertFactory();

}

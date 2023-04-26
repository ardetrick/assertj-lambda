package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;

public abstract class AbstractExtractableEitherAssert<
        SELF extends AbstractExtractableEitherAssert<SELF, LEFT, RIGHT, LEFT_EXTRACTED, RIGHT_EXTRACTED, LEFT_EXTRACTED_ASSERT, RIGHT_EXTRACTED_ASSERT>,
        LEFT,
        RIGHT,
        LEFT_EXTRACTED,
        RIGHT_EXTRACTED,
        LEFT_EXTRACTED_ASSERT extends AbstractAssert<?, LEFT_EXTRACTED>,
        RIGHT_EXTRACTED_ASSERT extends AbstractAssert<?, RIGHT_EXTRACTED>
        > extends AbstractEitherAssert<SELF, LEFT, RIGHT>
        implements
        ExtractableLeft<LEFT, LEFT_EXTRACTED, LEFT_EXTRACTED_ASSERT>,
        ExtractableRight<RIGHT, RIGHT_EXTRACTED, RIGHT_EXTRACTED_ASSERT>
{

    protected AbstractExtractableEitherAssert(Either<LEFT, RIGHT> either, Class<?> selfType) {
        super(either, selfType);
    }

    public RIGHT_EXTRACTED_ASSERT extractingRight() {
        return actual.biMap(
                        l -> isRight(),
                        r -> extracting(rightExtractor(r), rightAssertFactory()))
                .projectB()
                .match($ -> null, $ -> $);
    }

    public LEFT_EXTRACTED_ASSERT extractingLeft() {
        return actual.biMap(
                        l -> extracting(leftExtractor(l), leftAssertFactory()),
                        r -> isLeft())
                .projectA()
                .match($ -> null, $ -> $);
    }

}

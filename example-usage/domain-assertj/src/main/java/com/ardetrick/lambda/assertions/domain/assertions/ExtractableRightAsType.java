package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.api.ExtractableRight;
import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.InstanceOfAssertFactory;

import java.util.function.Function;

public interface ExtractableRightAsType<RIGHT, RIGHT_ASSERT extends AbstractAssert<?, RIGHT>>
        extends ExtractableRight<RIGHT, RIGHT, RIGHT_ASSERT> {

    InstanceOfAssertFactory<RIGHT, RIGHT_ASSERT> getInstanceOfAssertFactory();

    default Function<Either<?, RIGHT>, RIGHT> rightExtractor(RIGHT right) {
        return $ -> right;
    }

    default AssertFactory<RIGHT, RIGHT_ASSERT> rightAssertFactory() {
        return (right) -> getInstanceOfAssertFactory().createAssert(right);
    }

}

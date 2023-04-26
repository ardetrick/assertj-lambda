package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AssertFactory;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public interface ExtractableRightAsIterable<RIGHT_ELEMENT, RIGHT_ELEMENT_ASSERT extends AbstractAssert<RIGHT_ELEMENT_ASSERT, RIGHT_ELEMENT>>
        extends ExtractableRight<Iterable<RIGHT_ELEMENT>, Iterable<RIGHT_ELEMENT>, AbstractIterableAssert<?, Iterable<RIGHT_ELEMENT>, RIGHT_ELEMENT, RIGHT_ELEMENT_ASSERT>> {

    Class<RIGHT_ELEMENT_ASSERT> rightAssertTypeClass();

    default Function<Either<?, Iterable<RIGHT_ELEMENT>>, Iterable<RIGHT_ELEMENT>> rightExtractor(Iterable<RIGHT_ELEMENT> right) {
        return $ -> right;
    }

    default AssertFactory<Iterable<RIGHT_ELEMENT>, AbstractIterableAssert<?, Iterable<RIGHT_ELEMENT>, RIGHT_ELEMENT, RIGHT_ELEMENT_ASSERT>> rightAssertFactory() {
        return (x) -> assertThat(x, rightAssertTypeClass());
    }

}

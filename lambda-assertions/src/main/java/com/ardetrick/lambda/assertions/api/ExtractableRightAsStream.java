package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public interface ExtractableRightAsStream<
        RIGHT_ELEMENT_TYPE,
        RIGHT_MAPPED_ASSERT_TYPE extends AbstractAssert<RIGHT_MAPPED_ASSERT_TYPE, RIGHT_ELEMENT_TYPE>
        >
        extends
        ExtractableRight<
                Stream<RIGHT_ELEMENT_TYPE>,
                List<RIGHT_ELEMENT_TYPE>,
                AbstractListAssert<?, List<RIGHT_ELEMENT_TYPE>, RIGHT_ELEMENT_TYPE, RIGHT_MAPPED_ASSERT_TYPE>
                > {

    Class<RIGHT_MAPPED_ASSERT_TYPE> rightMappedAssertTypeClass();

    default Function<Either<?, Stream<RIGHT_ELEMENT_TYPE>>, List<RIGHT_ELEMENT_TYPE>> rightExtractor(Stream<RIGHT_ELEMENT_TYPE> stream) {
        return $ -> stream.toList();
    }

    default AssertFactory<List<RIGHT_ELEMENT_TYPE>, AbstractListAssert<?, List<RIGHT_ELEMENT_TYPE>, RIGHT_ELEMENT_TYPE, RIGHT_MAPPED_ASSERT_TYPE>> rightAssertFactory() {
        return list -> Assertions.assertThat(list, rightMappedAssertTypeClass());
    }

}

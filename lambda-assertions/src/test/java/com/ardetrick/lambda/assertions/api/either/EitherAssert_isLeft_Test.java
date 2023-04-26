package com.ardetrick.lambda.assertions.api.either;

import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.adt.Either.left;
import static com.jnape.palatable.lambda.adt.Either.right;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EitherAssert_isLeft_Test {

    @Test
    public void should_pass_if_left() {
        assertThat(left("a"))
                .isLeft();
    }

    @Test
    public void should_fail_if_right() {
        assertThatThrownBy(
                () -> assertThat(right("a")).isLeft()
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage("Expected Left found Right: <a>");
    }

}

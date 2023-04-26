package com.ardetrick.lambda.assertions.api.either;

import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.adt.Either.left;
import static com.jnape.palatable.lambda.adt.Either.right;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EitherAssert_isRight_Test {

    @Test
    public void should_pass_if_right() {
        assertThat(right("a"))
                .isRight();
    }

    @Test
    public void should_fail_if_left() {
        assertThatThrownBy(
                () -> assertThat(left("a")).isRight()
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage("Expected Right found Left");
    }

}

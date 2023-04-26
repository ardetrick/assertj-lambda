package com.ardetrick.lambda.assertions.api.either;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.adt.Either.left;
import static com.jnape.palatable.lambda.adt.Either.right;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EitherAssert_hasRightValueSatisfying_Test {

    @Test
    public void should_pass_if_right_and_no_additional_assertions() {
        assertThat(right("a"))
                .hasRightValueSatisfying(($) -> {});
    }

    @Test
    public void should_pass_if_right_and_passing_additional_assertions() {
        assertThat(right("a"))
                .hasRightValueSatisfying((right) -> Assertions.assertThat(right).isEqualTo("a"));
    }

    @Test
    public void should_fail_if_right_and_failing_additional_assertions() {
        assertThatThrownBy(() -> assertThat(right("a"))
                .hasRightValueSatisfying((right) -> Assertions.assertThat(right).isEqualTo("b"))
        ).isInstanceOf(AssertionFailedError.class)
                .hasMessageContaining("expected: \"b\"");
    }

    @Test
    public void should_fail_if_right_and_other_exception_is_thrown() {
        assertThatThrownBy(() -> assertThat(right("a"))
                .hasRightValueSatisfying((right) -> {throw new RuntimeException("abc");})
        ).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("abc");
    }

    @Test
    public void should_fail_if_left() {
        assertThatThrownBy(() -> assertThat(left("a")).hasRightValueSatisfying($ -> {}))
                .isInstanceOf(AssertionError.class)
                .hasMessage("Expected Right found Left");
    }

}

package com.ardetrick.lambda.assertions.api.maybe;

import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.adt.Maybe.just;
import static com.jnape.palatable.lambda.adt.Maybe.nothing;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MaybeAssert_isNothing_Test {

    @Test
    public void should_pass_when_is_nothing() {
        assertThat(nothing()).isNothing();
    }

    @Test
    public void should_fail_when_is_just() {
        assertThatThrownBy(() -> assertThat(just("abc")).isNothing())
                .isInstanceOf(AssertionError.class)
                .hasMessage("Expected Nothing found Just <abc>");
    }

    @Test
    public void should_pass_when_invoked_multiple_times() {
        assertThat(nothing())
                .isNothing()
                .isNothing();
    }

}

//    @Test
//    public void givenJustOfIsOf() {
//        assertThat(just("abc")).containsValue("abc");
//    }
//
//    @Test
//    public void givenJustOfIsOfOther() {
//        assertThatThrownBy(() -> assertThat(just("abc")).containsValue("def"))
//                .hasMessage("does not equal");
//    }
package com.ardetrick.lambda.assertions.api.maybe;

import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.adt.Maybe.just;
import static com.jnape.palatable.lambda.adt.Maybe.nothing;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MaybeAssert_isJust_Test {

    @Test
    public void should_pass_when_is_just() {
        assertThat(just("just")).isJust();
    }

    @Test
    public void should_fail_when_is_nothing() {
        assertThatThrownBy(() -> assertThat(nothing()).isJust())
                .isInstanceOf(AssertionError.class)
                .hasMessage("Expected Just found Nothing");
    }

    @Test
    public void should_pass_when_invoked_multiple_times() {
        assertThat(just("just"))
                .isJust()
                .isJust();
    }

}

//     @Test
//    public void givenNothingIsNothing() {
//        assertThat(nothing()).isNothing();
//    }
//
//
//    @Test
//    public void givenJustIsNothingThrows() {
//        assertThatThrownBy(() -> assertThat(just("just")).isNothing())
//                .hasMessage("expected nothing");
//    }
//
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
package com.ardetrick.lambda.assertions;

import com.jnape.palatable.lambda.adt.Maybe;
import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;

public class Example03Maybe {

    @Test
    public void nothing() {
        assertThat(Maybe.nothing())
                .isNothing();
    }

    @Test
    public void just() {
        assertThat(Maybe.just("a"))
                .isJust()
                .hasValue("a");
    }

}

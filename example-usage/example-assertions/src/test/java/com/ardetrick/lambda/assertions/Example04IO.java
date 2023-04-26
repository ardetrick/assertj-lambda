package com.ardetrick.lambda.assertions;

import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.io.IO.io;

public class Example04IO {

    @Test
    public void completesNormally() {
        assertThat(io("a"))
                .completesNormally();
    }

    @Test
    public void exceptionally() {
        assertThat(io(() -> {throw new RuntimeException();}))
                .completesExceptionally();
    }

}

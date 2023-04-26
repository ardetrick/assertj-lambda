package com.ardetrick.lambda.assertions;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.ardetrick.lambda.assertions.domain.assertions.Assertions.assertThat;

public class Example00OutOfTheBoxAssertions {

    @Test
    public void strings() {
        assertThat("abc")
                .isNotBlank()
                .startsWith("a")
                .endsWith("c");
    }

    @Test
    public void doubles() {
        assertThat(1d)
                .isCloseTo(1.1d, Offset.offset(0.2d));
    }

    @Test
    public void collections() {
        assertThat(List.of("a", "b"))
                .hasSize(2)
                .containsExactlyInAnyOrder("a", "b");
    }

}

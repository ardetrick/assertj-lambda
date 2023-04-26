package com.ardetrick.lambda.assertions.api.io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.io.IO.io;
import static com.jnape.palatable.lambda.io.IO.throwing;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class IOAssert_completesExceptionally_Test {

    @Test
    public void should_pass_if_completes_exceptionally() {
        assertThat(throwing(new RuntimeException("uh oh")))
                .completesExceptionally();
    }

    @Test
    public void should_fail_if_completes_normally() {
        assertThatThrownBy(() ->
                assertThat(io("a"))
                        .completesExceptionally()
        ).isInstanceOf(AssertionError.class)
                .hasMessage("IO did not complete exceptionally. Result: <a>");
    }

    @Test
    public void should_only_perform_io_once_when_called_multiple_times() {
        var atomicInteger = new AtomicInteger();
        assertThat(io(() -> {
            atomicInteger.getAndIncrement();
            throw new RuntimeException("uh oh");
        }))
                .completesExceptionally()
                .completesExceptionally();

        Assertions.assertThat(atomicInteger)
                .hasValue(1);
    }

}

package com.ardetrick.lambda.assertions.api.io;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.io.IO.io;
import static com.jnape.palatable.lambda.io.IO.throwing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class IOAssert_completesNormally_Test {

    @Test
    public void should_pass_if_no_exception_is_thrown() {
        assertThat(io("a"))
                .completesNormally();
    }

    @Test
    public void should_fail_if_exception_is_thrown() {
        assertThatThrownBy(() ->
                assertThat(throwing(new RuntimeException("uh oh")))
                        .completesNormally()
        ).isInstanceOf(AssertionError.class)
                .hasMessage("IO did not complete normally. Threw exception <java.lang.RuntimeException: uh oh>");
    }

    @Test
    public void should_only_perform_io_once_when_called_multiple_times() {
        var atomicInteger = new AtomicInteger();
        assertThat(io(atomicInteger::getAndIncrement))
                .completesNormally()
                .completesNormally();

        assertThat(atomicInteger)
                .hasValue(1);
    }

}

package com.ardetrick.lambda.assertions.api.io;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.ardetrick.lambda.assertions.api.Assertions.assertThat;
import static com.jnape.palatable.lambda.io.IO.io;
import static com.jnape.palatable.lambda.io.IO.throwing;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class IOAssert_extractingResult_Test {

    @Test
    public void should_pass_when_completes_normally() {
        assertThat(io("a"))
                .extractingResult(InstanceOfAssertFactories.type(String.class))
                .isEqualTo("a");
    }

    @Test
    public void should_fail_when_completes_exceptionally() {
        assertThatThrownBy(() ->
                assertThat(throwing(new RuntimeException("uh oh")))
                        .extractingResult(InstanceOfAssertFactories.type(String.class))
        ).isInstanceOf(AssertionError.class)
                .hasMessage("IO did not complete normally. Threw exception <java.lang.RuntimeException: uh oh>");
    }

    @Test
    public void should_only_perform_io_once_when_called_multiple_times() {
        var atomicInteger = new AtomicInteger();
        assertThat(io(atomicInteger::getAndIncrement))
                .completesNormally()
                .extractingResult(InstanceOfAssertFactories.type(Integer.class));

        Assertions.assertThat(atomicInteger)
                .hasValue(1);
    }

}

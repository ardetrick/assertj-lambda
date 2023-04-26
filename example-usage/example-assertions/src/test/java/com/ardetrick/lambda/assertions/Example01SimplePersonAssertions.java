package com.ardetrick.lambda.assertions;

import com.ardetrick.lambda.assertions.domain.Name;
import com.ardetrick.lambda.assertions.domain.Person;
import org.junit.jupiter.api.Test;

import static com.ardetrick.lambda.assertions.domain.assertions.Assertions.assertThat;

public class Example01SimplePersonAssertions {

    @Test
    public void hasNameString() {
        var person = new Person(new Name("Alex"));

        assertThat(person)
                .hasName("Alex");
    }

    @Test
    public void hasNameName() {
        var person = new Person(new Name("Alex"));

        assertThat(person)
                .hasName(new Name("Alex"));
    }

}

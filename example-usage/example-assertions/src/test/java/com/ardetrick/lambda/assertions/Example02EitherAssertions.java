package com.ardetrick.lambda.assertions;

import com.ardetrick.lambda.assertions.domain.Name;
import com.ardetrick.lambda.assertions.domain.Person;
import com.ardetrick.lambda.assertions.domain.assertions.InstanceOfAssertFactories;
import com.jnape.palatable.lambda.adt.Either;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.ardetrick.lambda.assertions.domain.assertions.Assertions.*;

public class Example02EitherAssertions {

    @Test
    public void simpleEither() {
        assertThat(Either.right("a"))
                .isRight()
                .hasRightValueSatisfying(right -> assertThat(right).isNotBlank());

        var left = assertThat(Either.left("b"))
                .isLeft()
                .left();

        assertThat(left)
                .isEqualTo("b");
    }

    @Test
    public void personEither_extractAsObject() {
        var personEither = Either.right(new Person(new Name("Alex")));
        assertThat(personEither)
                .isRight()
                .extractingRightAs()
                .isEqualTo(new Person(new Name("Alex")));
    }

    @Test
    public void personEither_extractThenType() {
        var personEither = Either.right(new Person(new Name("Alex")));

        assertThat(personEither)
                .isRight()
                .extractingRightAs()
                .asInstanceOf(InstanceOfAssertFactories.PERSON)
                .hasName("Alex");
    }

    @Test
    public void personEither_extractType() {
        var personEither = Either.right(new Person(new Name("Alex")));

        assertThat(personEither)
                .asInstanceOf(InstanceOfAssertFactories.EITHER_EXCEPTION_OR_PERSON)
                .extractingRight()
                .hasName("Alex");
    }

    @Test
    public void personEither_verboseAssertThat() {
        var personEither = Either.<Exception, Person>right(new Person(new Name("Alex")));

        assertThatEitherExceptionOrPerson(personEither)
                .extractingRight()
                .hasName("Alex");
    }

    @Test
    public void personEither_verboseAssertThatStream() {
        var stream = Either.<Exception, Stream<Person>>right(Stream.of(new Person(new Name("Alex"))));

        assertThatEitherExceptionOrPersonStream(stream)
                .extractingRight()
                .hasSize(1)
                .first()
                .hasName("Alex");
    }

}

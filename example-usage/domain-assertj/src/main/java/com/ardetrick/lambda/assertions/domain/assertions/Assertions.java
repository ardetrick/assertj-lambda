package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.api.EitherAssert;
import com.ardetrick.lambda.assertions.domain.Person;
import com.jnape.palatable.lambda.adt.Either;

import java.util.stream.Stream;

public class Assertions extends org.assertj.core.api.Assertions implements com.ardetrick.lambda.assertions.api.Assertions  {

    public static PersonAssert assertThat(Person person) {
        return new PersonAssert(person);
    }

    public static <L, R> EitherAssert<L, R> assertThat(Either<L, R> either) {
        return new EitherAssert<>(either);
    }

    public static EitherExceptionOrPersonIterable assertThatEitherExceptionOrPersonIterable(Either<Exception, Iterable<Person>> streamEither) {
        return new EitherExceptionOrPersonIterable(streamEither);
    }

    public static EitherExceptionOrPersonStream assertThatEitherExceptionOrPersonStream(Either<Exception, Stream<Person>> streamEither) {
        return new EitherExceptionOrPersonStream(streamEither);
    }

    public static EitherExceptionOrPerson assertThatEitherExceptionOrPerson(Either<Exception, Person> streamEither) {
        return new EitherExceptionOrPerson(streamEither);
    }

}

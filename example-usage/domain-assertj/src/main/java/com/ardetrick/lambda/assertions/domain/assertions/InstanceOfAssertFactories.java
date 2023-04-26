package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.domain.Person;
import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.InstanceOfAssertFactory;

public class InstanceOfAssertFactories implements org.assertj.core.api.InstanceOfAssertFactories {

    public static InstanceOfAssertFactory<Person, PersonAssert> PERSON =
        new InstanceOfAssertFactory<>(Person.class, Assertions::assertThat);

    public static InstanceOfAssertFactory<?, EitherExceptionOrPerson> EITHER_EXCEPTION_OR_PERSON =
            new InstanceOfAssertFactory<>(Either.class, Assertions::assertThatEitherExceptionOrPerson);

    static InstanceOfAssertFactory<?, EitherExceptionOrPersonStream> eitherExceptionOrPersonStream() {
        return new InstanceOfAssertFactory<>(Either.class, Assertions::assertThatEitherExceptionOrPersonStream);
    }

    static InstanceOfAssertFactory<?, EitherExceptionOrPersonIterable> eitherExceptionOrPersonIterable() {
        return new InstanceOfAssertFactory<>(Either.class, Assertions::assertThatEitherExceptionOrPersonIterable);
    }

}

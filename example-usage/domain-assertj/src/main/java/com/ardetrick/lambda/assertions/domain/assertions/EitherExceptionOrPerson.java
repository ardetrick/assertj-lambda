package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.api.AbstractExtractableEitherAssert;
import com.ardetrick.lambda.assertions.api.ExtractableLeftAsException;
import com.ardetrick.lambda.assertions.domain.Name;
import com.ardetrick.lambda.assertions.domain.Person;
import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.InstanceOfAssertFactory;

import static com.ardetrick.lambda.assertions.domain.assertions.Assertions.assertThat;

public class EitherExceptionOrPerson
        extends
        AbstractExtractableEitherAssert<
                EitherExceptionOrPerson,
                Exception,
                Person,
                Exception,
                Person,
                AbstractThrowableAssert<?, Exception>,
                PersonAssert>
        implements
        ExtractableLeftAsException,
        ExtractableRightAsType<Person, PersonAssert> {

    protected EitherExceptionOrPerson(Either<Exception, Person> exceptionStreamEither) {
        super(exceptionStreamEither, EitherExceptionOrPerson.class);
    }

    @Override
    public InstanceOfAssertFactory<Person, PersonAssert> getInstanceOfAssertFactory() {
        return InstanceOfAssertFactories.PERSON;
    }

}

class Test {

    public static void main(String[] args) {
        Either<Exception, Person> either1 = Either.right(new Person(new Name("a")));
        assertThat(either1)
                .isRight()
                .extractingRightAs(InstanceOfAssertFactories.PERSON)
                .hasName("a");

        Either<Exception, Person> either2 = Either.left(new RuntimeException("error"));
        assertThat(either2)
                .isLeft()
                .extractingLeftAs(InstanceOfAssertFactories.THROWABLE)
                .hasMessage("error");
    }

}

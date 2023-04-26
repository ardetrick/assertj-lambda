package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.api.AbstractExtractableEitherAssert;
import com.ardetrick.lambda.assertions.api.ExtractableLeftAsException;
import com.ardetrick.lambda.assertions.api.ExtractableRightAsIterable;
import com.ardetrick.lambda.assertions.domain.Name;
import com.ardetrick.lambda.assertions.domain.Person;
import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractThrowableAssert;

import java.util.Set;

import static com.ardetrick.lambda.assertions.domain.assertions.Assertions.assertThat;

public class EitherExceptionOrPersonIterable
        extends
        AbstractExtractableEitherAssert<
                EitherExceptionOrPersonIterable,
                Exception,
                Iterable<Person>,
                Exception,
                Iterable<Person>,
                AbstractThrowableAssert<?, Exception>,
                AbstractIterableAssert<?, Iterable<Person>, Person, PersonAssert>>
        implements
        ExtractableLeftAsException,
        ExtractableRightAsIterable<Person, PersonAssert>
{

    protected EitherExceptionOrPersonIterable(Either<Exception, Iterable<Person>> exceptionStreamEither) {
        super(exceptionStreamEither, EitherExceptionOrPersonIterable.class);
    }

    @Override
    public Class<PersonAssert> rightAssertTypeClass() {
        return PersonAssert.class;
    }
}

class Test2 {

    public static void main(String[] args) {
        Either<Exception, Iterable<Person>> stream1 = Either.right(Set.of(new Person(new Name("a"))));
        assertThat(stream1)
                .asInstanceOf(InstanceOfAssertFactories.eitherExceptionOrPersonIterable())
                .extractingRight()
                .hasSize(1)
                .first()
                .hasName("a");

//        Either<Exception, Iterable<Person>> stream2 = Either.left(new RuntimeException("error"));
//        assertThat(stream2).extractingLeft()
//                .hasMessage("error");
    }

}

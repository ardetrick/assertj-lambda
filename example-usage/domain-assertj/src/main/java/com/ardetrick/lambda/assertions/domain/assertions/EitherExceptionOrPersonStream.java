package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.api.AbstractExtractableEitherAssert;
import com.ardetrick.lambda.assertions.api.ExtractableLeftAsException;
import com.ardetrick.lambda.assertions.api.ExtractableRightAsStream;
import com.ardetrick.lambda.assertions.domain.Name;
import com.ardetrick.lambda.assertions.domain.Person;
import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.AbstractThrowableAssert;

import java.util.List;
import java.util.stream.Stream;

import static com.ardetrick.lambda.assertions.domain.assertions.Assertions.assertThatEitherExceptionOrPersonStream;

public class EitherExceptionOrPersonStream
        extends
        AbstractExtractableEitherAssert<
                EitherExceptionOrPersonStream,
                Exception,
                Stream<Person>,
                Exception,
                List<Person>,
                AbstractThrowableAssert<?, Exception>,
                AbstractListAssert<?, List<Person>, Person, PersonAssert>>
        implements
        ExtractableLeftAsException,
        ExtractableRightAsStream<Person, PersonAssert> {

    protected EitherExceptionOrPersonStream(Either<Exception, Stream<Person>> exceptionStreamEither) {
        super(exceptionStreamEither, EitherExceptionOrPersonStream.class);
    }

    @Override
    public Class<PersonAssert> rightMappedAssertTypeClass() {
        return PersonAssert.class;
    }

}

class Test1 {

    public static void main(String[] args) {
        Either<Exception, Stream<Person>> stream1 = Either.right(Stream.of(new Person(new Name("a"))));
        assertThatEitherExceptionOrPersonStream(stream1)
                .extractingRight()
                .hasSize(1)
                .first()
                .hasName("a");

        Either<Exception, Stream<Person>> stream2 = Either.left(new RuntimeException("error"));
        assertThatEitherExceptionOrPersonStream(stream2).extractingLeft()
                .hasMessage("error");
    }

}

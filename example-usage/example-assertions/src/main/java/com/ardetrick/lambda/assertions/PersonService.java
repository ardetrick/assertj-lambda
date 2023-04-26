package com.ardetrick.lambda.assertions;

import com.ardetrick.lambda.assertions.domain.Person;
import com.jnape.palatable.lambda.adt.Either;

import java.util.Set;
import java.util.stream.Stream;

public interface PersonService {



    Either<Exception, Person> getEitherPerson();

    Either<Exception, Set<Person>> getPeopleAsSet();

    Either<Exception, Stream<Person>> getPeopleAsStream();

}

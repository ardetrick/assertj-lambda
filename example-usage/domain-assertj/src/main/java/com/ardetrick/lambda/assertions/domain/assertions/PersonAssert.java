package com.ardetrick.lambda.assertions.domain.assertions;

import com.ardetrick.lambda.assertions.domain.Name;
import com.ardetrick.lambda.assertions.domain.Person;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class PersonAssert extends AbstractAssert<PersonAssert, Person> {

    public PersonAssert(Person person) {
        super(person, PersonAssert.class);
    }

    public PersonAssert hasName(Name name) {
        isNotNull();
        if (!Objects.equals(actual.name(), name)) {
            failWithMessage("Expected person's name to be <%s> but was <%s>", name, actual.name().value());
        }
        return this;
    }

    public PersonAssert hasName(String name) {
        isNotNull();
        if (!Objects.equals(actual.name().value(), name)) {
            failWithMessage("Expected person's name to be <%s> but was <%s>", name, actual.name().value());
        }
        return this;
    }

}

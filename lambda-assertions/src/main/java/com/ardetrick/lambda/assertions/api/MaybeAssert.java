package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Maybe;
import com.jnape.palatable.lambda.monad.Monad;
import org.assertj.core.api.AbstractAssert;

public class MaybeAssert<VALUE> extends AbstractAssert<MaybeAssert<VALUE>, Maybe<VALUE>> {

    protected MaybeAssert(Maybe<VALUE> actual) {
        super(actual, MaybeAssert.class);
    }

    public MaybeAssert<VALUE> isJust() {
        actual.toOptional().ifPresentOrElse(
                $ -> {},
                () -> failWithMessage("Expected Just found Nothing")
        );
        return this;
    }

    public MaybeAssert<VALUE> isNothing() {
        actual.toOptional().ifPresentOrElse(
                value -> failWithMessage("Expected Nothing found Just <%s>", value),
                () -> {}
        );
        return this;
    }

    public MaybeAssert<VALUE> hasValue(VALUE value) {
        isJust();
        // TODO: use equals strategy
        if (!actual.toOptional().get().equals(value)) {
            failWithMessage("nExpecting:%n  <%s>%nto contain the instance (i.e. compared with ==):%n  <%s>%nbut did not.", actual, value);
        }
        return this;
    }

    //    public MaybeAssert<T> containsValue(T expectedValue) {
//        return actual.match(
//                a -> {
//                    failWithMessage("expected nothing");
//                    return this;
//                },
//                b -> {
//                    if (!b.equals(expectedValue)) {
//                        failWithMessage("does not equal");
//                    }
//                    return this;
//                }
//        );
//

    // hasValueSatisfying()
    // extractingValue()
    // extractingValueAs()

}

package com.ardetrick.lambda.assertions.api;

import com.jnape.palatable.lambda.adt.Either;
import org.assertj.core.api.InstanceOfAssertFactory;

public interface InstanceOfAssertFactories {

    @SuppressWarnings({"rawtypes", "unused", "unchecked", "RedundantSuppression"})
    static <L, R> InstanceOfAssertFactory<Either, EitherAssert<L, R>> either() {
        return new InstanceOfAssertFactory<>(Either.class, Assertions::<L, R>assertThat);
    }

    @SuppressWarnings({"rawtypes", "unused", "unchecked", "RedundantSuppression"})
    static <L, R> InstanceOfAssertFactory<Either, EitherAssert<L, R>> either(Class<L> leftType, Class<R> rightType) {
        return new InstanceOfAssertFactory<>(Either.class, Assertions::<L, R>assertThat);
    }

    //   static <K, V> InstanceOfAssertFactory<Map, MapAssert<K, V>> map(Class<K> keyType, Class<V> valueType) {
    //    return new InstanceOfAssertFactory<>(Map.class, Assertions::<K, V> assertThat);
    //  }

}

package todo;

import com.jnape.palatable.lambda.adt.Either;
import com.jnape.palatable.lambda.io.IO;

import java.util.List;

public class Main {

    public static IO<Either<Exception, List<String>>> example(String input) {
        return switch (input) {
            case "a" -> IO.io(() -> {
                System.out.println("here");
                return Either.right(List.of());
            });
            case "b" -> IO.io(Either.right(List.of("a")));
            case "c" -> IO.io(Either.right(List.of("a", "b")));
            case "d" -> IO.io(Either.left(new RuntimeException(input)));
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };
    }

}

record Pojo(String a, Integer b) {}
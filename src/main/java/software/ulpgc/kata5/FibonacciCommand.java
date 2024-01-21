package software.ulpgc.kata5;

import java.util.stream.IntStream;

public class FibonacciCommand implements Command {

    @Override
    public Output execute(Input input) {
        try{
            int number = Integer.parseInt(input.get(":number"));
            return isOutOfBound(number) ? outOfBoundOutput() : outputOf(number);
        } catch (NumberFormatException e) {
            return nanOutput();
        }
    }

    private Output nanOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 405;
            }

            @Override
            public String result() {
                return "Not a number";
            }
        };
    }

    private Output outputOf(int number) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(fibonacciOf(number));
            }
        };
    }

    private static int fibonacciOf(int number) {
        return IntStream.range(0,number)
                .reduce((a,b) -> b == 0 ? 0 : (b == 1 ? 1 : a + b))
                .orElse(0);
    }

    private Output outOfBoundOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 404;
            }

            @Override
            public String result() {
                return "Number out of bounds";
            }
        };
    }

    private boolean isOutOfBound(int number) {
        return number < 1 || number > 20;
    }
}

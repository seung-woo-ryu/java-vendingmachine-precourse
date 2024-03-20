package vendingmachine.view;

import java.util.function.Supplier;

public abstract class AbstractView {
    private String errorFormat = "[ERROR] %s\n";
    protected  <T> T commonInput(String inputMessage, String errorMessage, Supplier<T> supplier) {
        while (true) {
            System.out.println(inputMessage);
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                printError(errorMessage);
            }
        }
    }
    public void printError(String errorMessage) {
        System.out.println(String.format(errorFormat,errorMessage));
    }
}

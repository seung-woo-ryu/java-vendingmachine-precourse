package vendingmachine;

import vendingmachine.controller.Controller;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.View;

public class Application {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        View view = new View();
        Controller controller = new Controller(vendingMachine, view);

        controller.run();
    }
}

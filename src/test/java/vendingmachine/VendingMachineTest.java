package vendingmachine;

import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @Test
    void test() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.generateCoins(1000);
        System.out.println(vendingMachine);

    }

}
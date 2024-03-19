package vendingmachine;

import java.util.EnumMap;
import org.junit.jupiter.api.Test;
import vendingmachine.model.Coin;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.View;

class VendingMachineTest {

    @Test
    void test() {
        VendingMachine v = new VendingMachine();

        EnumMap<Coin, Long> coinMap = new EnumMap<>(Coin.class);
        coinMap.put(Coin.COIN_500, 0L);
        coinMap.put(Coin.COIN_100, 0L);
        coinMap.put(Coin.COIN_50, 0L);
        coinMap.put(Coin.COIN_10, 0L);

        View view = new View();
        view.inputClientMoney();



    }

}
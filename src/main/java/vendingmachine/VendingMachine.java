package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class VendingMachine {

    private EnumMap<Coin, Long> coinMap;

    public VendingMachine(int money) {
        this.coinMap = generateCoins(money);
    }

    private EnumMap<Coin, Long> generateCoins(int money) {
        // 유효성 검사하기

        while (money != 0) {
            Randoms.pickNumberInList(Arrays.asList(500, 100, 50, 10));
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<Coin, Long> entry : coinMap.entrySet()) {
            String tmp = String.format("%s원 - %s개\n", entry.getKey().getAmount(), entry.getValue());
            sb.append(tmp);
        }
        sb.append("\n");

        return sb.toString();
    }
}

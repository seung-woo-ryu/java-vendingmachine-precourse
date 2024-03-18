package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class VendingMachine {

    private EnumMap<Coin, Long> coinMap = new EnumMap<>(Coin.class);

    public VendingMachine() {
        init();
    }

    private void init() {
        coinMap.put(Coin.COIN_500, 0L);
        coinMap.put(Coin.COIN_100, 0L);
        coinMap.put(Coin.COIN_50, 0L);
        coinMap.put(Coin.COIN_10, 0L);
    }

    public void generateCoins(int money) {
        // 유효성 검사하기
        if (money % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 잘못된 동전 금액입니다. 10원 단위로 입력해주세요!");
        }

        while (money != 0) {
            int amount = Randoms.pickNumberInList(Coin.getCoinList());
            if (amount <= money) {
                Coin coin = Coin.getCoin(amount);
                coinMap.compute(coin, (key, oldValue) -> oldValue + 1);
                money -= amount;
            }
        }
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

package vendingmachine.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map.Entry;

public class CoinContainer {
    private EnumMap<Coin, Long> coin2quantity = new EnumMap<>(Coin.class);

    public CoinContainer() {
        coin2quantity.put(Coin.COIN_500, 0L);
        coin2quantity.put(Coin.COIN_100, 0L);
        coin2quantity.put(Coin.COIN_50, 0L);
        coin2quantity.put(Coin.COIN_10, 0L);
    }

    public void init(int money) {
        // 유효성 검사하기
        if (money % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 잘못된 동전 금액입니다. 10원 단위로 입력해주세요!");
        }

        while (money != 0) {
            int amount = Randoms.pickNumberInList(Coin.getCoinList());
            if (amount <= money) {
                Coin coin = Coin.getCoin(amount);
                coin2quantity.compute(coin, (key, oldValue) -> oldValue + 1);
                money -= amount;
            }
        }
    }

    public EnumMap<Coin, Long> returnCoins(int change) {
        EnumMap<Coin, Long> changeCoins = new EnumMap<>(Coin.class);

        for (Entry<Coin, Long> entry : coin2quantity.entrySet()) {
            int amount = entry.getKey().getAmount();
            int quantity = entry.getValue().intValue();

            Integer minQuantity = Collections.min(
                Arrays.asList(Math.floorDiv(change, amount), quantity));
            changeCoins.put(entry.getKey(), minQuantity.longValue());

            change -= amount * minQuantity;
        }

        return changeCoins;
    }
    public EnumMap<Coin, Long> getCoin2quantity() {
        return coin2quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<Coin, Long> entry : coin2quantity.entrySet()) {
            // ex) 500원 - 9개
            sb.append(entry.getKey().getAmount()).append("원 - ").append(entry.getValue()).append("개\n");
        }

        return sb.toString();
    }
}

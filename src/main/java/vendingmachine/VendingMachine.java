package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VendingMachine {

    private EnumMap<Coin, Long> coinMap = new EnumMap<>(Coin.class);

    public EnumMap<Coin, Long> getCoinMap() {
        return coinMap;
    }

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

    public EnumMap<Coin, Long> returnCoins(int change) {
        EnumMap<Coin, Long> changeCoins = new EnumMap<>(Coin.class);

        for(Entry<Coin,Long> entry: coinMap.entrySet()){
            int amount = entry.getKey().getAmount();
            int quantity = entry.getValue().intValue();

            Integer minQuantity = Collections.min(Arrays.asList(Math.floorDiv(change,amount), quantity));
            changeCoins.put(entry.getKey(), minQuantity.longValue());

            change -= amount * minQuantity;
        }

        return changeCoins;
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

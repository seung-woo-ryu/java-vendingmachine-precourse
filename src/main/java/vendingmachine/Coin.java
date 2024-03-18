package vendingmachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static Coin getCoin(int money){
        for (Coin c : Coin.values()) {
            if (c.getAmount() == money) {
                return c;
            }
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 동전 금액입니다.");
    }

    public static List<Integer> getCoinList() {
        return Arrays.stream(Coin.values()).map(c -> c.getAmount()).collect(Collectors.toList());
    }

}

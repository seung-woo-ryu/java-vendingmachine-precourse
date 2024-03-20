package vendingmachine.model;

import java.util.EnumMap;

public class VendingMachine {
    private ProductContainer productContainer;
    private CoinContainer coinContainer = new CoinContainer();;

    public void setProductContainer(String[] products) {
        this.productContainer = ProductContainer.of(products);
    }

    public void generateCoins(int money) {
        this.coinContainer.init(money);
    }
    public EnumMap<Coin, Long> getCoin2quantity() {
        return coinContainer.getCoin2quantity();
    }

    public EnumMap<Coin, Long> returnCoins(int change) {
        return this.coinContainer.returnCoins(change);
    }

    public boolean isAvailableForPurchase(int inputDeposit) {
        // 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우, 바로 종료
        return !(productContainer.isOutOfMoney(inputDeposit) || productContainer.isOutOfProduct());
    }

    public int buyProduct(String productName, int clientMoney) {
        return productContainer.buyProduct(productName, clientMoney);
    }
}

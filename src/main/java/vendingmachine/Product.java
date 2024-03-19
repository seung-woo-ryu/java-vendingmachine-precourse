package vendingmachine;

public class Product {

    private String name;
    private int price;

    public Product(String name, int price) {
        // 유효성 검사
        checkMoney(price);

        this.name = name;
        this.price = price;
    }

    private void checkMoney(int price) {
        if (price < 100) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 100원 이상입니다.");
        }

        if (price % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 10원으로 나누어 떨어져야 합니다.");
        }
    }
}

package vendingmachine.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductContainer {

    private static int NAME_IDX = 0;
    private static int PRICE_IDX = 1;
    private static int QUANTITY_IDX = 2;
    private Map<Product, Long> product2quantity;

    public ProductContainer(Map<Product, Long> product2quantity) {
        this.product2quantity = product2quantity;
    }

    public static ProductContainer of(String[] products) {
        Map<Product, Long> product2quantity = new HashMap<>();

        for (String product : products) {
            putNewProduct(product, product2quantity);
        }

        return new ProductContainer(product2quantity);
    }

    private static void putNewProduct(String product, Map<Product, Long> product2quantity) {
        String[] split = product
            .substring(1, product.length() - 1)
            .split(",");
        // todo: 위험.
        product2quantity.put(new Product(split[NAME_IDX], Integer.parseInt(split[PRICE_IDX])), Long.parseLong(split[QUANTITY_IDX]));
    }

    public boolean isOutOfProduct() {
        final Long allQuantity = product2quantity.values().stream().reduce(0L, Long::sum);
        return allQuantity.equals(0L);
    }
    public boolean isOutOfMoney(int inputDeposit) {
        final int minPrice = (product2quantity.keySet()
            .stream()
            .min(Comparator.comparing(Product::getPrice))
            .orElseThrow(IllegalArgumentException::new))
            .getPrice();

        return inputDeposit < minPrice;
    }
    public int buyProduct(String productName, int inputDeposit) {
        Product product = getAvaliableProduct(productName)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 적합하지 않는 상품이거나 갯수가 0인 상품입니다"));

        product2quantity.computeIfPresent(product, (p, quantity) -> quantity - 1);

        return inputDeposit - product.getPrice();
    }

    private Optional<Product> getAvaliableProduct(String productName) {
        for (Product product : product2quantity.keySet()) {
            if (product.getName().equals(productName) && product2quantity.get(product) != 0L) {
                return Optional.of(product);
            }
        }

        return Optional.empty();
    }
}

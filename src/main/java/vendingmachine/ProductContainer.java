package vendingmachine;

import java.util.HashMap;
import java.util.Map;

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
}

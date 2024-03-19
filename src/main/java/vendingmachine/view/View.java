package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class View {

    private String errorFormat = "[ERROR] %s\n";
    private String inputVendingMachineMoney = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private String inputProducts = "상품명과 가격, 수량을 입력해 주세요.";
    private String inputClientMoney = "투입 금액을 입력해 주세요.";
    private String inputBuyProduct = "투입 금액: %d원\n구매할 상품명을 입력해 주세요.";
    private String vendingMachineCoins = "자판기가 보유한 동전\n%s\n";
    private String change = "투입 금액: %d원\n잔돈\n%s";

    public int inputVendingMachineMoney() {
        return commonInput(inputVendingMachineMoney, "숫자를 입력해주세요.", ()-> Integer.parseInt(Console.readLine()));
    }

    public List<Product> inputProducts() {
        return commonInput(inputProducts, "'[상품명,가격,수량]'형식과 ';'을 사용해 상품리스트를 입력해주세요.",
            ()-> getProducts());
    }
    public Long inputClientMoney() {
        return commonInput(inputClientMoney, "숫자를 입력해주세요.", ()-> Long.parseLong(Console.readLine()));
    }

    private static List<Product> getProducts() {
        String productsConcatBySemiColon = Console.readLine();
        String[] products = productsConcatBySemiColon.split(";");
        // todo: 유효성 검사 필요.
        return null;
    }

    public <T> T commonInput(String inputMessage, String errorMessage, Supplier<T> supplier) {
        while (true) {
            System.out.println(inputMessage);
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(String.format(errorFormat,errorMessage));
            }
        }

    }
    public void printVendingMachineCoins(EnumMap<Coin,Long> coin2quantity) {
        StringBuilder sb = new StringBuilder();
        for (Entry<Coin, Long> entry : coin2quantity.entrySet()) {
            // ex) 500원 - 9개
            sb.append(entry.getKey().getAmount()).append("원 - ").append(entry.getValue()).append("개\n");
        }

        System.out.println(String.format(vendingMachineCoins,sb));
    }
}

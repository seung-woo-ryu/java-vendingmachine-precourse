package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import vendingmachine.model.Coin;
import vendingmachine.model.Product;

public class View extends AbstractView{
    private String inputVendingMachineMoney = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private String inputProducts = "상품명과 가격, 수량을 입력해 주세요.";
    private String inputClientMoney = "투입 금액을 입력해 주세요.";
    private String inputBuyProduct = "투입 금액: %d원\n구매할 상품명을 입력해 주세요.";
    private String vendingMachineCoins = "자판기가 보유한 동전\n%s\n";
    private String remainAndChange = "투입 금액: %d원\n잔돈\n";

    public int inputVendingMachineMoney() {
        return commonInput(inputVendingMachineMoney, "숫자를 입력해주세요.", ()-> Integer.parseInt(Console.readLine()));
    }

    public String[] inputProducts() {
        return commonInput(inputProducts, "'[상품명,가격,수량]'형식과 ';'을 사용해 상품리스트를 입력해주세요.",
            ()-> getProducts());
    }
    public int inputClientMoney() {
        return commonInput(inputClientMoney, "숫자를 입력해주세요.", ()-> Integer.parseInt(Console.readLine()));
    }
    // todo: 미완성
    public String inputBuyProduct(int amountInMachine, List<Product> products) {
        return commonInput(String.format(inputBuyProduct,amountInMachine), "존재하지 않는 상품입니다.", ()-> canBuyProduct(products));
    }
    // todo: 미완성
    private String canBuyProduct(List<Product> products) {
        return null;
    }
    // todo: 미완성
    private static String[] getProducts() {
        String productsConcatBySemiColon = Console.readLine();
        String[] products = productsConcatBySemiColon.split(";");
        // todo: 유효성 검사 필요.
        return null;
    }
    public void printVendingMachineCoins(EnumMap<Coin,Long> coin2quantity) {
        StringBuilder sb = new StringBuilder();
        for (Entry<Coin, Long> entry : coin2quantity.entrySet()) {
            // ex) 500원 - 9개
            sb.append(entry.getKey().getAmount()).append("원 - ").append(entry.getValue()).append("개\n");
        }

        System.out.println(String.format(vendingMachineCoins,sb));
    }

    public void printChange(int change,EnumMap<Coin,Long> coin2quantity) {
        System.out.println(String.format(remainAndChange,change));
        printVendingMachineCoins(coin2quantity);
    }
}

package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import vendingmachine.model.Coin;
import vendingmachine.model.CoinContainer;
import vendingmachine.model.Product;

public class View extends AbstractView{
    private String inputVendingMachineMoney = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private String inputProducts = "상품명과 가격, 수량을 입력해 주세요.";
    private String inputClientMoney = "투입 금액을 입력해 주세요.";
    private String inputBuyProduct = "투입 금액: %d원\n구매할 상품명을 입력해 주세요.";
    private String vendingMachineCoins = "자판기가 보유한 동전\n%s";
    private String remainAndChange = "투입 금액: %d원\n잔돈";

    public int inputVendingMachineMoney() {
        return commonInput(inputVendingMachineMoney, "숫자를 입력해주세요.", ()-> {
            int inputMoney = Integer.parseInt(Console.readLine());
            if (inputMoney % 10 != 0) {
                throw new IllegalArgumentException("[ERROR] 잘못된 동전 금액입니다. 10원 단위로 입력해주세요!");
            }
            return inputMoney;
        });
    }

    public String[] inputProducts() {
        return commonInput(inputProducts, "'[상품명,가격,수량]'형식과 ';'을 사용해 상품리스트를 입력해주세요.",
            ()-> getProducts());
    }
    public int inputClientMoney() {
        return commonInput(inputClientMoney, "숫자를 입력해주세요.", ()-> Integer.parseInt(Console.readLine()));
    }
    public String inputBuyProduct(int amountInMachine) {
        return commonInput(String.format(inputBuyProduct, amountInMachine), "알 수 없는 에러 입니다.", ()-> Console.readLine());
    }

    private static String[] getProducts() {
        String productsConcatBySemiColon = Console.readLine();
        String[] products = productsConcatBySemiColon.split(";");

        if (products == null || products.length == 0) {
            throw new IllegalArgumentException("[ERROR] 유효한 상품이 없습니다.");
        }
        // todo: 유효성 검사 필요.
        return products;
    }

    public void printVendingMachineCoins(EnumMap<Coin,Long> coin2quantity) {
        StringBuilder sb = new StringBuilder();
        for (Entry<Coin, Long> entry : coin2quantity.entrySet()) {
            // ex) 500원 - 9개
            sb.append(entry.getKey().getAmount()).append("원 - ").append(entry.getValue()).append("개\n");
        }

        System.out.println(String.format(vendingMachineCoins, sb));
    }

    public void printChange(int change, EnumMap<Coin,Long> coin2quantity) {
        System.out.println(String.format(remainAndChange,change));
        printVendingMachineCoins(coin2quantity);
    }
}

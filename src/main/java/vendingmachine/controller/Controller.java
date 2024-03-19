package vendingmachine.controller;

import vendingmachine.model.ProductContainer;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.View;

public class Controller {

    private VendingMachine vendingMachine;
    private View view;

    public Controller() {
        init();
    }

    private void init() {
        vendingMachine = new VendingMachine();
        view = new View();
    }

    public void run() {
        // ’자판기’ 보유하고 있는 금액 입력.
        // 금액에 맞는 ‘동전’ 랜덤 생성.
        vendingMachine.generateCoins(view.inputVendingMachineMoney());
        // ‘자판기’ 보유한 ‘동전’ 출력.
        view.printVendingMachineCoins(vendingMachine.getCoinMap());
        // “상품명”, “가격”, “수량” 입력 받아 ‘물품’ 생성
        ProductContainer productContainer = ProductContainer.of(view.inputProducts());
        // ‘투입 금액’ 입력
        // 상품 구매
        dealProcess(view.inputClientMoney());
    }

    private void dealProcess(int clientMoney) {
        while (true) {
            // view.inputBuyProduct();
            break;
        }
        view.printChange(clientMoney,vendingMachine.returnCoins(clientMoney));
    }
}

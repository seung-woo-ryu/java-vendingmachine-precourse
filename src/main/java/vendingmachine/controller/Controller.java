package vendingmachine.controller;

import vendingmachine.model.ProductContainer;
import vendingmachine.model.VendingMachine;
import vendingmachine.view.View;

public class Controller {

    private VendingMachine vendingMachine;
    private View view;

    public Controller(VendingMachine vendingMachine, View view) {
        this.vendingMachine = vendingMachine;
        this.view = view;
    }

    public void run() {
        // ’자판기’ 보유하고 있는 금액 입력.
        // 금액에 맞는 ‘동전’ 랜덤 생성.
        vendingMachine.generateCoins(view.inputVendingMachineMoney());
        // ‘자판기’ 보유한 ‘동전’ 출력.
        view.printVendingMachineCoins(vendingMachine.getCoin2quantity());
        // “상품명”, “가격”, “수량” 입력 받아 ‘물품’ 생성
        vendingMachine.setProductContainer(view.inputProducts());
        // ‘투입 금액’ 입력
        // 상품 구매
        dealProcess(view.inputClientMoney());
    }

    private void dealProcess(int clientMoney) {
        // 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우, 바로 종료
        while (vendingMachine.isAvailableForPurchase(clientMoney)) {
            try {
                final String productName = view.inputBuyProduct(clientMoney);
                clientMoney = vendingMachine.buyProduct(productName, clientMoney);
            } catch (IllegalArgumentException e){
                view.printError(e.getMessage());
            }
        }

        view.printChange(clientMoney, vendingMachine.returnCoins(clientMoney));
    }
}

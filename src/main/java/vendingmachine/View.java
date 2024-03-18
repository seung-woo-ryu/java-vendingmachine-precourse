package vendingmachine;

import camp.nextstep.edu.missionutils.Console;

public class View {

    public int inputMoney() {
        try {
            System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
            String input = Console.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자를 입력해주세요.");
        }
    }
}

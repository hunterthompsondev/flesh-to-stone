package local.fleshtostone;

import java.util.List;
import java.util.Scanner;

public class OutputManager {
    public int getDecision(final String text, final List<String> choices) {
        System.out.println(text);

        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + 1 + ": " + choices.get(i));
        }

        String inputStr;
        int input = 1;

        Scanner scanner = new Scanner(System.in);

        boolean valid = false;
        while (!valid) {
            System.out.print("Decision choice: ");
            inputStr = scanner.nextLine();
            try {
                input = Integer.parseInt(inputStr);
                valid = input >= 1 && input <= choices.size();

            } catch (Exception e) {
            }
        }

        scanner.close();

        return input - 1;
    }
}

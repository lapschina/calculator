import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exception {
        calc();
    }

    private static void calc() throws Exception {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new Exception("Некорректное выражение");
        }
        String[] data = exp.split(regexActions[actionIndex]);

        if (data.length > 2) {
            throw new Exception("Некорректное выражение");
        }

        if (converter.isRoman(data[0]) && converter.isRoman(data[1])) {
            int a, b;

            try {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } catch (Exception e) {
                throw new Exception("Некорректное значение");
            }

            if (a > 10 || b > 10) {
                throw new Exception("Значение не должно быть больше Х");
            }

            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            if (result <= 0) {
                throw new Exception("Ответ должен быть положительным числом");
            } else {
                System.out.println(converter.intToRoman(result));
            }
            return;
        }

        if (!converter.isRoman(data[0]) && !converter.isRoman(data[1])) {
            int a, b;

            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[1]);

            if (a > 10 || b > 10) {
                throw new Exception("Значение не должно быть больше 10");
            }

            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            System.out.println(result);

        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
    }
}

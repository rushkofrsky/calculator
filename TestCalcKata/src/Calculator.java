import java.util.Scanner;

// импортируем класс scanner
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    // Метод читает строку ввода и передает введенные значения в метод calc.
    // После обработки возвращает результат и выводит на экран результат.

    public static String calc(String input) {
        String[] tokens = input.trim().split("\\s+");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неправильные аргументы.");
        }
        // Метод calc проверяет корректность введенных аргументов:
        // Принимает введенные пользователем значения и разбивает на 3 элемента: первый аргумент, оператор, второй аргумент.
        // trim() удаляем пробелы в начале и конце строки.
        // Находим пробелы в строке проверяя на соответсвие регулярному выражению '\\s+". Метод split разбивает строку на токены.
        // Формируем массив tokens.
        // Проверяем длину массива tokens.
        // Если длина массива не равна 3, то выбрасываем исключение IllegalArgumentException с сообщением ""Неправильные аргументы."
        int a = parseNumber(tokens[0]);
        int b = parseNumber(tokens[2]);
        char op = tokens[1].charAt(0);
        // Парсим массив и присваиваем значения переменным.
        // Первый токен должен быть первым целым числом, второй токен оператором (символом), третий токен вторым цылем чилсом
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Неправильно. Числа должны быть от 1 до 10 включительно");
        }
        // Проверяем значения переменных введенных пользователем на соответвие условию: Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.
        // Если условие нарушено, выбрасываем исключение IllegalArgumentException с текстом "Неправильно. Числа должны быть от 1 до 10 включительно"
        int result;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неправильно. Допустимые операторы: +, -, * или /");
        }
        return formatResult(result, isRoman(tokens[0], tokens[2]));
    }
    // В зависимости от значения переменной op (оператора), выполняем соответвующую арифметическую операцию и сохраняет результат в переменную result
    //  Если пользователь ввел недопустимое значение, выбрасываем исключение IllegalArgumentException с текстом "Неправильно. Допустимые операторы: +, -, * или /"
    //  Метод возвращает отформатированный результат, который зависит от типа чисел.
    //  Если числа являются римскими цифрами, они будут отформатированы.

    public static boolean isRoman(String a, String b) {
        return a.matches("[IVX]+") && b.matches("[IVX]+");
    }

    // Проверяем введенные числа являются ли они римскими, методом matches, проверяем регулярным выражением наличие в строке символов "I", "V" и/или "X"
    // Если обе строки соответствуют этому регулярному выражению, метод возвращает true, иначе - false.
    public static int parseNumber(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return RomanNumeral.valueOf(s).toInt();
        }
    }

    public static String formatResult(int result, boolean roman) {
        if (roman) {
            return RomanNumeral.fromInt(result).toString();
        } else {
            return Integer.toString(result);
        }
    }
}

// Для реализации условия: Результатом операции деления является целое число, остаток отбрасывается.
// Преобразуем строку в целое число.
// Если это римское число, то используется метод toInt() класса RomanNumeral
// Если преобразование не удается, выбрасываем исключение NumberFormatException "Неверный формат. Должны быть арабские или римские числа"
enum RomanNumeral {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), IIX(8), IX(9), X(10), XL(40), L(50), XC(90), C(100);

    //Список римских чисел и соответствующее этому числу, арабское число.

    public final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    // Конструктор класса RomanNumeral принимает числовое значение и устанавливает его в качестве значения константы.
    public int getValue() {
        return value;
    }

    public static RomanNumeral fromInt(int n) {
        for (RomanNumeral numeral : RomanNumeral.values()) {
            if (numeral.getValue() == n) {
                return numeral;
            }
        }
        throw new IllegalArgumentException("Неправильное число" + n);
    }

    // Принимаем число и возвращаем соответсвующий элемент из перечисления enum RomanNumeral
    // Если ни один элемент не соответствует переданному числу, то выбрасываем исключение
    // IllegalArgumentException с сообщением "Неправильное число: n".
    public int toInt() {
        return value;
    }

    // Методы getValue() и toInt() возвращают числовое значение, соответствующее римской цифре.
    @Override
    public String toString() {
        return name();
    }
}
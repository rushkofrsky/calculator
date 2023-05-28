import java.util.Scanner;

// импортируем класс scanner
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
        input  = input.toUpperCase();
        boolean isArabic = input.matches("^\\d{1,2}[\\s-+/*]\\d{1,2}$");
        boolean isRoman = input.matches("^[VIX]{1,4}[\\s-+/*][VIX]{1,4}$");
        if (isArabic ^ isRoman) {
            throw new IllegalArgumentException("Оба числа должны быть римскими или арабскими");
        }
    }
    // Метод читает строку ввода. Если введенные числа соответсвуют регулярным выражениям и арабского и римского формата, выбрасывает исключение
    //"Оба числа должны быть римскими или арабскими"
    // После обработки возвращает и выводит на экран результат.


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
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
    XI(11), XII(12), XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20),
    XXI(21), XXII(22), XXIII(23), XXIV(24), XXV(25), XXVI(26), XXVII(27), XXVIII(28), XXIX(29), XXX(30),
    XXXI(31), XXXII(32), XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37), XXXVIII(38), XXXIX(39), XL(40),
    XLI(41), XLII(42), XLIII(43), XLIV(44), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50),
    LI(51), LII(52), LIII(53), LIV(54), LV(55), LVI(56), LVII(57), LVIII(58), LIX(59), LX(60),
    LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68), LXIX(69), LXX(70),
    LXXI(71), LXXII(72), LXXIII(73), LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80),
    LXXXI(81), LXXXII(82), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88), LXXXIX(89), XC(90),
    XCI(91), XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100);
    //Список римских чисел и соответствующее этому числу арабское число.

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

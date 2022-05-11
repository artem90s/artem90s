package Calculation;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    public static String calc (String input) throws Exception {
        int a=0;
        int b=0;
        String op="";
        String outputString="";

        String[]strings=input.split("\\W");
        String[]operator=input.split("\\w");

        if(strings.length<2){
            throw new Exception("т.к. строка не является математической операцией");
        }
        else if(strings.length>2){
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию");
        }

        try{a=Integer.parseInt(strings[0]);
        b=Integer.parseInt(strings[1]);}
        catch (NumberFormatException e){
            a=Operations.romanToArabic(strings[0]);
            b=Operations.romanToArabic(strings[1]);
            op=operator[operator.length-1];
            if(a>10 || b>10){
                throw new Exception("Нельзя использовать цифры больше 10");
            }
            outputString=Operations.arabicToRoman(Operations.operation(a,b,op));
            System.out.println(outputString);
            return outputString;}
        op=operator[operator.length-1];

        if(a>10 || b>10){
            throw new Exception("Нельзя использовать цифры больше 10");
        }


        outputString=Integer.toString(Operations.operation(a,b,op));
        System.out.println(outputString);
        return outputString;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        calc(sc.next());

    }
}
class Operations {
    static int operation (int a, int b, String op) throws Exception {
        switch (op) {
            case "+": return a+b;
            case "-": return a-b;
            case "*": return a*b;
            case "/": return a/b;
            default:throw new Exception("т.к. неизвестный арифметический знак");
        }

    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException("т.к. используются одновременно разные системы счисления");
        }

        return result;
    }
    public static String arabicToRoman(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("//т.к. в римской системе нет отрицательных чисел");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}

enum RomanNumeral {
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10);

    private int value;

    RomanNumeral(int value){
        this.value=value;
    }

    public int getValue() {
        return value;}

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }


}


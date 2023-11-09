package helper;

public class Operation {
    public static String addBinary(String firstOperand, String secondOperand) {
        int firstNumber = Integer.parseInt(firstOperand, 2);
        int secondNumber = Integer.parseInt(secondOperand, 2);

        int sum = firstNumber + secondNumber;

        return String.format("%016d", Integer.valueOf(Integer.toBinaryString(sum)));
    }

    public static String subtractBinary(String firstOperand, String secondOperand) {
        int firstNumber = Integer.parseInt(firstOperand, 2);
        int secondNumber = Integer.parseInt(secondOperand, 2);

        int subtraction = firstNumber - secondNumber;

        return String.format("%016d", Integer.valueOf(Integer.toBinaryString(subtraction)));
    }

    public static String shiftLeftLogic(String operand) {
        return operand.substring(1) + "0";
    }

    public static String shiftRightLogic(String operand) {
        return "0" + operand.substring(0, 15);
    }

    public static String shiftRightArithmetic(String operand) {
        return operand.charAt(0) + operand.substring(0, 15);
    }

    public static String OR(String firstOperand, String secondOperand) {
        int firstNumber = Integer.parseInt(firstOperand, 2);
        int secondNumber = Integer.parseInt(secondOperand, 2);

        int OR = firstNumber | secondNumber;

        return String.format("%016d", Integer.valueOf(Integer.toBinaryString(OR)));
    }

    public static String AND(String firstOperand, String secondOperand) {
        int firstNumber = Integer.parseInt(firstOperand, 2);
        int secondNumber = Integer.parseInt(secondOperand, 2);

        int AND = firstNumber & secondNumber;

        return String.format("%016d", Integer.valueOf(Integer.toBinaryString(AND)));
    }

    public static String setOnLessThanImmediate(String readData1, String operand) {
        int firstNumber = Integer.parseInt(readData1, 2);
        int secondNumber = Integer.parseInt(operand, 2);

        if (firstNumber < secondNumber)
            return "0000000000000001";
        else
            return "0000000000000000";
    }
}

package gpt;

import java.util.LinkedList;

public class ExpressionCalculator {
    public static double calculate(String expression) {
        LinkedList<Double> numberStack = new LinkedList<>();
        LinkedList<Character> operatorStack = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--;
                double num = Double.parseDouble(sb.toString());
                numberStack.push(num);
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performOperation(numberStack, operatorStack);
                }
                operatorStack.pop(); // Remove '(' from the operator stack
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && hasPrecedence(c, operatorStack.peek())) {
                    performOperation(numberStack, operatorStack);
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            performOperation(numberStack, operatorStack);
        }

        return numberStack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    private static void performOperation(LinkedList<Double> numberStack, LinkedList<Character> operatorStack) {
        double num2 = numberStack.pop();
        double num1 = numberStack.pop();
        char operator = operatorStack.pop();
        double result = 0.0;

        switch (operator) {
            case '+':
                // 2-5+3 == 2-(5-3)
                if (!operatorStack.isEmpty() && operatorStack.peek() == '-') {
                    result = num1 - num2;
                } else {
                    result = num1 + num2;
                }
                break;
            case '-':
                // 2-5-3 == 2-(5+3)
                if (!operatorStack.isEmpty() && operatorStack.peek() == '-') {
                    result = num1 + num2;
                } else {
                    result = num1 - num2;
                }
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        numberStack.push(result);
    }

    public static void main(String[] args) {
        String expression = "2-5+3";
        double result = calculate(expression);
        System.out.println("Result: " + result);
    }
}


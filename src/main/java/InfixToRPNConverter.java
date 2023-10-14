import java.util.*;

public class InfixToRPNConverter {
    public static String convertToRPN(String infixExpression) {
        StringBuilder output = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        String[] tokens = infixExpression.split(" ");
        for (String token : tokens) {
            char c = token.charAt(0);

            if (Character.isDigit(c)) {
                output.append(token).append(" ");
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '(' && hasHigherPrecedence(stack.peek(), c)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString().trim();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        int precedence1 = getOperatorPrecedence(op1);
        int precedence2 = getOperatorPrecedence(op2);
        return precedence1 >= precedence2;
    }

    private static int getOperatorPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        String infixExpression = "1 + ( 2 - 3 * 4 ) / 5 + 6";
        String rpnExpression = convertToRPN(infixExpression);
        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("RPN Expression: " + rpnExpression);
    }
}

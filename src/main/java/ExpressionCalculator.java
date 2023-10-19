import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionCalculator {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        while (true) {
            String expression = bufferedReader.readLine();
            try {
                expression = StringUtils.deleteWhitespace(expression);
                double res = expressionCalculator.eval(expression);
                System.out.println(expression + " = " + res);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public double eval(String expression) {
        checkArgument(expression);

        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char cur = expression.charAt(i);

            if (isDigitStart(cur, expression, i)) {
                Info info = getNum(expression, i);
                operandStack.push(info.value);
                i = info.endIdx;
                if (!operatorStack.isEmpty() && hasPriority(operatorStack.peek())) {
                    performCalculate(operandStack, operatorStack);
                }
            } else if (isOperator(cur)) {
                operatorStack.push(cur);
            } else if (cur == '(') {
                operatorStack.push(cur);
            } else if (cur == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performCalculate(operandStack, operatorStack);
                }
                operatorStack.pop(); // 左括号弹出
                if (!operatorStack.isEmpty() && hasPriority(operatorStack.peek())) {
                    performCalculate(operandStack, operatorStack);
                }
            } else if (cur == '.') {
                throw new IllegalArgumentException("表达式格式有误，数字不能以.开头");
            } else {
                throw new IllegalArgumentException("表达式格式有误，非法字符，idx: " + i);
            }
            i++;
        }
        while (!operatorStack.isEmpty()) {
            performCalculate(operandStack, operatorStack);
        }
        return operandStack.pop();
    }

    public static void performCalculate(Stack<Double> operandStack, Stack<Character> operatorStack) {
        double num2 = operandStack.pop();
        double num1 = operandStack.pop();
        char operator = operatorStack.pop();
        double res;
        switch (operator) {
            case '+':
                // 2-5+3 == 2-(5-3)
                if (!operatorStack.isEmpty() && operatorStack.peek() == '-') {
                    res = num1 - num2;
                } else {
                    res = num1 + num2;
                }
                break;
            case '-':
                // 2-5-3 == 2-(5+3)
                if (!operatorStack.isEmpty() && operatorStack.peek() == '-') {
                    res = num1 + num2;
                } else {
                    res = num1 - num2;
                }
                break;
            case '*': res = num1 * num2; break;
            case '/':
                if (num2 != 0) {
                    res = num1 / num2;
                    break;
                }
                throw new IllegalArgumentException("0不能做除数");
            default:
                throw new IllegalArgumentException("表达式格式有误，请检查括号匹配");
        }
        operandStack.push(res);
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean hasPriority(char operator) {
        return operator == '*' || operator == '/';
    }

    public static boolean isDigitStart(char c, String expression, int i) {
        if (Character.isDigit(c)) {
            return true;
        }
        return c == '-' && (i == 0 || expression.charAt(i - 1) == '(');
    }

    public static Info getNum(String expression, int start) {
        StringBuilder sb = new StringBuilder(expression.charAt(start) + "");
        int end = start + 1;
        int dotCnt = 0;

        for (; end < expression.length(); end++) {
            char cur = expression.charAt(end);
            if (cur == '.') {
                dotCnt++;
                if (dotCnt > 1) {
                    throw new IllegalArgumentException(expression + "数字部分包含多个小数点." + start);
                }
            } else if (!Character.isDigit(cur)) {
                break;
            }
            sb.append(cur);
        }
        return new Info(Double.parseDouble(sb.toString()), end - 1);
    }

    public static void checkArgument(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException("表达式为空");
        }
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == ')') continue;
            if (Character.isDigit(c)) continue;
            if (isOperator(c)) continue;
            if (c == '.') continue;
            throw new IllegalArgumentException("表达式包含非法字符" + c);
        }
    }

    static class Info {
        double value;
        int endIdx;

        public Info(double value, int endIdx) {
            this.value = value;
            this.endIdx = endIdx;
        }
    }
}

package gpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHistory {
    private static final int MAX_HISTORY_SIZE = 10;
    private static String[] history = new String[MAX_HISTORY_SIZE];
    private static int currentIndex = 0;
    private static int historySize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = reader.readLine()) != null) {
            if (input.isEmpty())
                continue;

            if (input.equals("\033[A")) { // 上箭头键
                previousInput();
            } else if (input.equals("\033[B")) { // 下箭头键
                nextInput();
            } else {
                addToHistory(input);
                System.out.println("Input: " + input);
            }
        }
    }

    private static void addToHistory(String input) {
        if (historySize + 1 > MAX_HISTORY_SIZE) {
            // 若历史记录已满，将最旧的记录移出队列
            System.arraycopy(history, 1, history, 0, MAX_HISTORY_SIZE - 1);
            currentIndex--;
        } else {
            historySize++;
        }

        history[currentIndex] = input;
    }

    private static void previousInput() {
        if (currentIndex > 0) {
            currentIndex--;
            System.out.println("Previous input: " + history[currentIndex]);
        }
    }

    private static void nextInput() {
        if (currentIndex < historySize - 1) {
            currentIndex++;
            System.out.println("Next input: " + history[currentIndex]);
        }
    }
}

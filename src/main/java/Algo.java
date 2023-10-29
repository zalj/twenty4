import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Algo {

    static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        calculateDeathLoop();
    }

    private static void calculateDeathLoop() throws IOException {
        int[] arr = new int[4];
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = input.readLine();
            if (in == null || in.length() == 0) {
                continue;
            }
            if (in.equals("quit")) {
                System.exit(1);
            }
            in = in.trim();
            in = in.replaceAll("[ ]{2,}", " ");
            String[] words = in.split(" ");
            if (words.length < 4) {
                if (words.length > 0) {
                    System.err.println("输入数不够");
                }
                continue;
            }
            arr[0] = Integer.parseInt(words[0]);
            arr[1] = Integer.parseInt(words[1]);
            arr[2] = Integer.parseInt(words[2]);
            arr[3] = Integer.parseInt(words[3]);
            int target = 24;
            if (words.length > 4) {
                target = Integer.parseInt(words[4]);
            }
            if (target == 0) {
                for (int i = 0; i < 150; i++) {
                    calc(arr, i);
                }
            } else {
                calc(arr, target);
            }
        }
    }

    public static final String[] operands = {"+", "-", "-", "*", "/", "/"};
    public static final String basicFormat = "%d %s %d";
    public static final String format_12_34 = "(" + basicFormat + ") %s (" + basicFormat + ") = %d";

    public static final String[] format_123_4 = {
            "((" + basicFormat + ") %s %d) %s %d = %d",
            "(%d %s (" + basicFormat + ")) %s %d = %d",
            "%d %s ((" + basicFormat + ") %s %d) = %d",
            "%d %s (%d %s (" + basicFormat + ")) = %d"
    };

    private static int doCalc(int[] nums, int target) {
        int[] solveCnt = new int[1];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) continue;
                int t1 = nums[i];
                int t2 = nums[j];
                double[] f12 = Basic.doCalc2(t1, t2);
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) continue;
                    int t3 = nums[k];
                    for (int p = 0; p < 4; p++) {
                        if (p == i || p == j || p == k) continue;
                        int t4 = nums[p];
                        int[] nn = {t1, t2, t3, t4};
                        double[] f34 = Basic.doCalc2(t3, t4);
                        // 12 和 34 组合
                        verify12_34(nn, target, f12, f34, solveCnt);

                        // 123 和 4 组合
                        verify123_4(nn, target, f12, t3, t4, solveCnt);
                    }
                }
            }
        }
        return solveCnt[0];
    }


    public static void calc(int[] nums, int target) {
        map.clear();
        int solveCnt = doCalc(nums, target);
        if (solveCnt == 0) {
            System.out.println("算" + target + "无解");
            return;
        }
        map.values().stream().map(Basic::deleteSpace).forEach(System.out::println);
    }

    public static void verify12_34(int[] nums, int target, double[] f12, double[] f34, int[] solveCnt) {
        for (int i = 0; i < 6; i++) {
            if (Double.isNaN(f12[i]) || f12[i] < 0) continue;
            for (int j = 0; j < 6; j++) {
                if (Double.isNaN(f34[j]) || f34[j] < 0) continue;
                double[] rr = Basic.doCalc2(f12[i], f34[j]);
                for (int rrl = 0; rrl < 6; rrl++) {
                    if (Math.abs(rr[rrl] - target) < 0.00000001d) {
                        int[] numsCopy = Arrays.copyOf(nums, 4);
                        print12_34(numsCopy, target, i, j, rrl);
                        solveCnt[0]++;
                    }
                }
            }
        }
    }

    public static void verify123_4(int[] nums, int target, double[] f12, int t3, int t4, int[] solveCnt) {
        for (int f12w = 0; f12w < 6; f12w++) {
            if (Double.isNaN(f12[f12w]) || f12[f12w] < 0) continue;
            double[] f123 = Basic.doCalc2(f12[f12w], t3);

            for (int f123w = 0; f123w < 6; f123w++) {
                if (Double.isNaN(f123[f123w]) || f123[f123w] < 0) continue;
                double[] f123_4 = Basic.doCalc2(f123[f123w], t4);
                for (int rrl = 0; rrl < 6; rrl++) {
                    if (Math.abs(f123_4[rrl] - target) < 0.00000001d) {
                        int[] numsCopy = Arrays.copyOf(nums, 4);
                        print123_4(numsCopy, target, f12w, f123w, rrl);
                        solveCnt[0]++;
                    }
                }
            }
        }
    }

    public static void print123_4(int[] nums, int target, int f12w, int f123w, int f1234w) {
        if (f12w == 2 || f12w == 5) {
            Basic.swap(nums, 0, 1);
        }
        int formatIdx = 0;
        if (f123w == 2 || f123w == 5) {
            Basic.swap(nums, 0, 1);
            Basic.swap(nums, 0, 2);
            int tmp = f12w;
            f12w = f123w;
            f123w = tmp;
            formatIdx += 1;
        }
        if (f1234w == 2 || f1234w == 5) {
            Basic.swap(nums, 0, 1);
            Basic.swap(nums, 0, 2);
            Basic.swap(nums, 0, 3);
            int tmp = f12w;
            f12w = f1234w;
            f1234w = tmp;
            tmp = f123w;
            f123w = f1234w;
            f1234w = tmp;
            formatIdx += 2;
        }
        String formatter = format_123_4[formatIdx];
        String res = String.format(formatter, nums[0], operands[f12w], nums[1], operands[f123w], nums[2], operands[f1234w], nums[3], target);
        map.putIfAbsent(Basic.simplify(res), res);
    }

    public static void print12_34(int[] nums, int target, int f12w, int f34w, int mw) {
        if (f12w == 2 || f12w == 5) {
            Basic.swap(nums, 0, 1);
        }
        if (f34w == 2 || f34w == 5) {
            Basic.swap(nums, 2, 3);
        }
        if (mw == 2 || mw == 5) {
            Basic.swap(nums, 0, 2);
            Basic.swap(nums, 1, 3);
            int tmp = f12w;
            f12w = f34w;
            f34w = tmp;
        }
        String res = String.format(format_12_34, nums[0], operands[f12w], nums[1], operands[mw], nums[2], operands[f34w], nums[3], target);
        map.putIfAbsent(Basic.simplify(res), res);
    }

}

class Basic {

    public static double[] doCalc2(double t1, double t2) {
        double[] res = new double[6];
        res[0] = t1 + t2;
        res[1] = t1 - t2;
        res[2] = t2 - t1;
        res[3] = t1 * t2;
        res[4] = t2 == 0 ? Double.NaN : t1 / t2;
        res[5] = t1 == 0 ? Double.NaN : t2 / t1;
        return res;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static String simplify(String str) {
        List<String> tokens = getTokens(deleteSpace(str));
        str = String.join(" ", tokens);
        return InfixToRPNConverter.convertToRPN(str);
    }

    public static String deleteSpace(String str) {
        char[] tgt = new char[str.length()];
        int right = 0;
        for (char c : str.toCharArray())
            if (c != ' ')
                tgt[right++] = c;
        return new String(tgt, 0, right);
    }

    public static List<String> getTokens(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int start = i;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                list.add(s.substring(start, i));
            } else {
                list.add(s.charAt(i) + "");
                i++;
            }
        }
        return list;
    }

}

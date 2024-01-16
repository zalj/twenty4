package leetcode;

import java.util.Arrays;

public class Main {
    // 小于等于 limit 的放左边，大于的放右边
    public void partition(int[] nums, int limit) {
        System.out.println(Arrays.toString(nums));
        int leIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= limit) {
                swap(nums, i, ++leIdx);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        String ans = minWindow(s, t);
        System.out.println(ans);
    }

    public static String minWindow(String s, String t) {
        int L = 0, R = 0;
        int[] tc = new int[128];
        for (int i = 0; i < t.length(); i++) {
            tc[t.charAt(i)]++;
        }
        int minL = -1, minR = -1, min = Integer.MAX_VALUE;
        int totalNeed = t.length();
        while (R < s.length()) {
            char r = s.charAt(R);
            // 当前 R 位置字符在 t 中存在
            if (tc[r] > 0) {
                totalNeed--;
            }
            tc[r]--;
            if (totalNeed == 0) {
                if (R - L + 1 < min) {
                    min = R - L + 1;
                    minL = L;
                    minR = R;
                }
                while (L < R) {
                    char l = s.charAt(L);
                    L++;
                    tc[l]++;
                    if (tc[l] > 0) {
                        totalNeed++;
                        break;
                    }
                    if (R - L + 1 < min) {
                        min = R - L + 1;
                        minL = L;
                        minR = R;
                    }
                }
            }
            R++;
        }
        return minL >= 0 ? s.substring(minL, minR + 1) : "";
    }

}

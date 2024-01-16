import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo();
        valid("551".toCharArray(), 0, 2);
        List<String> res = d.restoreIpAddresses("25525511135");
        res.forEach(System.out::println);
    }

    public static int eight(int x) {
        if (x < 6 || x % 2 == 1 || x == 10) {
            return 0;
        }
        int eight = x >> 3;
        int remain = (x & 7) >> 1;
        return remain == 0 ? eight : eight - 3 + remain;
    }

    public static int sixOrEight(int x) {
        if (x < 6 || x % 2 == 1 || x == 10) {
            return 0;
        }
        int eight = x >> 3;
        return (x & 7) == 0 ? eight : eight + 1;
    }

    // 先根
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            list.add(top.val);
            pushIfExist(top.right, stack);
            pushIfExist(top.left, stack);
        }
        return list;
    }

    // 中根
    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (pushIfExist(root, stack)) {
                root = root.left;
            } else {
                TreeNode top = stack.pop();
                list.add(top.val);
                root = top.right;
            }
        }
        return list;
    }

    // 后根
    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            list.add(top.val);
            pushIfExist(top.left, stack);
            pushIfExist(top.right, stack);
        }
        Collections.reverse(list);
        return list;
    }

    public static boolean pushIfExist(TreeNode node, Stack<TreeNode> stack) {
        if (node != null) {
            stack.push(node);
            return true;
        }
        return false;
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, s.toCharArray(), 0, new ArrayList<>());
        return res;
    }

    private void dfs(List<String> res, char[] chs, int index, List<String> path) {
        if (index == chs.length && path.size() == 4) {
            res.add(String.join(".", path));
            return;
        }
        if (path.size() >= 4) {
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (valid(chs, index, index + i - 1)) {
                path.add(new String(chs, index, i));
                dfs(res, chs, index + i, path);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean valid(char[] chs, int begin, int end) {
        if (begin < 0 || begin >= chs.length || end < 0 || end >= chs.length) {
            return false;
        }
        if (end < begin || end - begin > 2) {
            return false;
        }
        if (chs[begin] == '0' && begin < end) {
            return false;
        }
        int v = 0;
        for (int i = begin; i <= end; i++) {
            if (chs[i] < '0' || chs[i] > '9') {
                return false;
            }
            v = v * 10 + (chs[i] - '0');
        }
        return v < 256;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int wordLen = 0;
        List<String> res = new ArrayList<>();
        List<String> line = new ArrayList<>();

        for (String word : words) {
            wordLen += word.length();
            line.add(word);
            int totalLen = wordLen + line.size() - 1;
            if (totalLen == maxWidth) {
                res.add(String.join(" ", line));
                line = new ArrayList<>();
            } else if (totalLen > maxWidth) {
                line.remove(line.size() - 1);
                wordLen -= word.length();
                res.add(makeLine(line, wordLen, maxWidth));
                line = new ArrayList<>();
                line.add(word);
                wordLen = word.length();
            }
        }
        if (!line.isEmpty()) {
            res.add(makeLine(line, wordLen, maxWidth));
        }
        return res;
    }

    private static String makeLine(List<String> line, int lineWordLen, int maxWidth) {
        if (line.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (line.size() == 1) {
            sb.append(line.get(0));
            while (sb.length() < maxWidth) {
                sb.append(' ');
            }
            return sb.toString();
        }
        // every word (except last one) follow with a space
        int spaces = maxWidth - lineWordLen;
        int slots = line.size() - 1;
        final String baseSpaces = nSpace(spaces / slots);

        for (int i = 0; i < line.size(); i++) {
            sb.append(line.get(i));
            if (i == line.size() - 1) {
                break;
            }
            sb.append(baseSpaces);
            if (i < spaces % slots) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    private static String nSpace(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            n--;
            s.append(' ');
        }
        return s.toString();
    }

    public int[] recoverArray(int[] nums) {
        int m = nums.length;
        int n = nums.length >>> 1;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[0] || (nums[i] - nums[0]) % 2 != 0) {
                continue;
            }
            int[] ans = new int[n];
            boolean[] visited = new boolean[2 * n];
            int k = (nums[i] - nums[0]) >>> 1;
            ans[0] = nums[0] + k;

            int lowerSize = 1;
            int L1 = 1;
            int L2 = i + 1;
            visited[0] = true;
            visited[i] = true;
            // L1 从 1 开始，L2 从 i + 1 开始，最小值是 L1 指向的数，它一定在 lower 中
            while (lowerSize < n) {
                while (L1 < m && visited[L1]) {
                    L1++;
                }
                // 所有值都遍历过了
                if (L1 == m) {
                    break;
                }
                if (L2 <= L1) {
                    L2 = L1 + 1;
                }
                if (L2 == m) {
                    break;
                }
                if (nums[L2] - nums[L1] > 2 * k) {
                    break;
                } else if (nums[L2] - nums[L1] == 2 * k) {
                    visited[L1++] = true;
                    visited[L2++] = true;
                    ans[lowerSize++] = nums[L1] + k;
                } else {
                    L2++;
                }
            }
            if (lowerSize == n) {
                System.out.println(k);
                return ans;
            }
        }
        throw new IllegalArgumentException();
    }

    // #L#R#
    public void recursive(TreeNode root) {
        if (root == null) {
            return;
        }
        //
        recursive(root.left);
        //
        recursive(root.right);
        //
    }

    public String minNumber(int[] nums) {
        List<String> numStr = Arrays.stream(nums)
                .mapToObj(Integer::toString)
                .sorted((a, b) -> (a + b).compareTo(b + a))
                .collect(Collectors.toList());
        return String.join("", numStr);
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

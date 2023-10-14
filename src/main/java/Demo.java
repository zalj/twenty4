import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        for (int i = 1000; i <= 10000; i += 2) {
            int eight = eight(i);
            int six = (i - eight * 8) % 6 == 0 ? (i - eight * 8) / 6 : 0;
            int sixAndEight = sixOrEight(i);
            if (sixAndEight != six + eight) {
                System.err.println("Wrong!");
                continue;
            }
            if (sixAndEight == 0) {
                System.out.println(i + "不能使用 6 或 8 组成");
            } else {
                System.out.printf("%2d = %2d * 6 + %2d * 8, 共需要 %d 个数\n", i, six, eight, sixAndEight);
            }
        }
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
        return  (x & 7) == 0 ? eight : eight + 1;
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

    public String minNumber(int[] nums) {
        List<String> numStr = Arrays.stream(nums)
                .mapToObj(Integer::toString)
                .sorted((a, b) -> (a + b).compareTo(b + a))
                .collect(Collectors.toList());
        return String.join("", numStr);
    }
}

package gpt;

import java.util.Random;

public class BinarySearchTree {

    private TreeNode root;

    public BinarySearchTree() {
    }

    public static BinarySearchTree makeTree(int[] arr) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int a : arr) {
            bst.add(a);
        }
        return bst;
    }

    public boolean add(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return true;
        }
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null) {
            if (cur.val == val) {
                return false;
            }
            parent = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (parent.val > val) {
            parent.left = new TreeNode(val, parent);
        } else {
            parent.right = new TreeNode(val, parent);
        }
        return true;
    }

    public boolean remove(int val) {
        TreeNode node = getNode(val);
        if (node == null) {
            return false;
        }
        // 要删的是叶节点
        if (node.left == null && node.right == null) {
            if (node == root) {
                root = null;
                return true;
            }
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            node.parent = null;
        } else if (node.left == null) { // 左树为空
            node.right.parent = node.parent;
            if (node.parent == null) {
                root = node.right;
            } else {
                node.parent.right = node.right;
            }
        } else if (node.right == null) { // 右树为空
            node.left.parent = node.parent;
            if (node.parent == null) {
                root = node.left;
            } else {
                node.parent.left = node.left;
            }
        } else { // 左右都不为空，找右树最左节点代替当前节点
            TreeNode r = node.right;
            if (r.left == null) { // leftmost 就是当前右子树根节点
                r.left = node.left;
                r.left.parent = r;
                r.parent = node.parent;
                if (node == root) {
                    root = r;
                } else {
                    if (node.parent.left == node) {
                        node.parent.left = r;
                    } else {
                        node.parent.right = r;
                    }
                }
            } else {
                TreeNode leftmost = r.left;
                TreeNode leftmostParent = r;
                while (leftmost.left != null) {
                    leftmostParent = leftmost;
                    leftmost = leftmost.left;
                }
                leftmostParent.left = leftmost.right;
                leftmost.left = node.left;
                leftmost.right = node.right;
                node.left.parent = leftmost;
                node.right.parent = leftmost;
                leftmost.parent = node.parent;
                if (node == root) {
                    root = leftmost;
                } else {
                    if (node.parent.left == node) {
                        node.parent.left = leftmost;
                    } else {
                        node.parent.right = leftmost;
                    }
                }
            }
        }
        return true;
    }

    public boolean contains(int val) {
        if (root == null) {
            return false;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return true;
            }
            if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    private TreeNode getNode(int val) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return cur;
            }
            if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                '}';
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode parent) {
            this.val = val;
            this.parent = parent;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(30);
        }
        BinarySearchTree bst = BinarySearchTree.makeTree(arr);
        System.out.println(bst);
    }
}

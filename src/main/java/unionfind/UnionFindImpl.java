package unionfind;

import java.util.*;

public class UnionFindImpl<T> {

    Map<T, Node<T>> nodeMap = new HashMap<>();
    Map<Node<T>, Node<T>> fatherMap = new HashMap<>();
    Map<Node<T>, Integer> sizeMap = new HashMap<>();

    public UnionFindImpl(Collection<T> list) {
        for (T t : list) {
            Node<T> node = new Node<>(t);
            nodeMap.put(t, node);
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public UnionFindImpl(T[] arr) {
        for (T t : arr) {
            Node<T> node = new Node<>(t);
            nodeMap.put(t, node);
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public boolean isSameSet(T a, T b) {
        Node<T> na = nodeMap.get(a);
        Node<T> nb = nodeMap.get(b);
        if (na == null || nb == null) {
            return false;
        }
        return findHead(na) == findHead(nb);
    }

    public void union(T a, T b) {
        Node<T> na = nodeMap.get(a);
        Node<T> nb = nodeMap.get(b);
        if (na == null || nb == null) {
            return;
        }
        Node<T> fa = findHead(na);
        Node<T> fb = findHead(nb);
        if (fa == fb) {
            return;
        }
        int aSize = sizeMap.get(fa);
        int bSize = sizeMap.get(fb);
        Node<T> big = aSize >= bSize ? fa : fb;
        Node<T> small = big == fa ? fb : fa;
        fatherMap.put(small, big);
        sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
        sizeMap.remove(small);
    }

    private Node<T> findHead(Node<T> node) {
        List<Node<T>> path = new ArrayList<>();
        while (node != fatherMap.get(node)) {
            path.add(node);
            node = fatherMap.get(node);
        }
        for (Node<T> n : path) {
            fatherMap.put(n, node);
        }
        return node;
    }

    private static class Node<U> {
        U value;

        public Node(U value) {
            this.value = value;
        }
    }

}

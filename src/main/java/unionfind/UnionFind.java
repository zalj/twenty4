package unionfind;

public interface UnionFind<T> {
    boolean isSameSet(T a, T b);

    void union(T a, T b);
}

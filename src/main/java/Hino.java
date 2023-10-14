public class Hino {
    static int moveCount = 0;
    // 把从P[1]至P[k]的 k 个盘子从 FROM 移动到 TARGET，可以借助 OTHER
    public static <T> void func(int k, T FROM, T TARGET, T OTHER) {
        if (k == 1) {
            moveCount++;
            System.out.printf("将圆盘P[%2d]从 %s 移动到 %s\n", k, FROM, TARGET);
            return;
        }
        // 先把 P[1]至P[k-1] 移动到 OTHER, 借助 TARGET
        func(k - 1, FROM, OTHER, TARGET);
        // P[k] 移动至 TARGET
        System.out.printf("将圆盘P[%2d]从 %s 移动到 %s\n", k, FROM, TARGET);
        moveCount++;
        // 最后把 P[1]至P[k-1] 移动到 TARGET, 借助 FROM
        func(k - 1, OTHER, TARGET, FROM);
    }

    public static void func(int k) {
        func(k, "TA", "TB", "TC");
    }

    public static void main(String[] args) {
        func(13);
        System.out.println("移动次数：" + moveCount);
    }
}

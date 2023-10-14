package spittr;

import sun.misc.Unsafe;

import java.io.IOException;
import java.text.ChoiceFormat;
import java.util.*;

public class Test {
    static Unsafe o;
    static long offsetOfName;

    public static void main( String[] args ) throws NoSuchFieldException, IllegalAccessException, IOException {
        ChoiceFormat fmt = new ChoiceFormat(
                "-1#is negative| 0#is zero or fraction | 1#is one |1.0<is 1+ |2#is two |2<is more than 2.");
        System.out.println("Formatter Pattern : " + fmt.toPattern());

        System.out.println("Format with -INF : " + fmt.format(Double.NEGATIVE_INFINITY));
        System.out.println("Format with -1.0 : " + fmt.format(-1.0));
        System.out.println("Format with 0 : " + fmt.format(0));
        System.out.println("Format with 0.9 : " + fmt.format(0.9));
        System.out.println("Format with 1.0 : " + fmt.format(1));
        System.out.println("Format with 1.5 : " + fmt.format(1.5));
        System.out.println("Format with 2 : " + fmt.format(2));
        System.out.println("Format with 2.1 : " + fmt.format(2.1));
        System.out.println("Format with NaN : " + fmt.format(Double.NaN));
        System.out.println("Format with +INF : " + fmt.format(Double.POSITIVE_INFINITY));
    }

    private final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long, List<String>> map = new HashMap<>();
        Arrays.stream(strs).forEach(str -> map.computeIfAbsent(hash(str), e -> new ArrayList<>()).add(str));
        return new ArrayList<>(map.values());
    }

    public long hash(String str) {
        long hash = 1;
        for (char c : str.toCharArray()) {
            hash *= primes[c - 'a'];
        }
        return hash;
    }
}

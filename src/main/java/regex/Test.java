package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher("a");
        System.out.println(matcher.groupCount());
    }
}

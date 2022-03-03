package Week6.N1755;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var M = sc.nextInt();
        var N = sc.nextInt();
        var level = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine"};
        var map = new TreeMap<StringBuilder, Integer>();
        for (int n = M, i = 0; n <= N; n++, i++) {
            var sb = new StringBuilder();
            if (n > 9) {
                sb.append(level[n / 10]).append(level[n % 10]);
            } else {
                sb.append(level[n]);
            }
            map.put(sb, n);
        }
        var sb = new StringBuilder();
        var count = 0;
        for (var k : map.keySet()) {
            count++;
            sb.append(map.get(k)).append(' ');
            if (count == 10) {
                count = 0;
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
}

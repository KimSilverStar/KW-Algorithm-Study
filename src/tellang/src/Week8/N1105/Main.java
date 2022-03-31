package Week8.N1105;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var l = sc.next().toCharArray();
        var r = sc.next().toCharArray();
        var result = 0;
        if (l.length == r.length) {
            for (int i = 0; i < l.length; i++) {
                if (l[i] == r[i]) {
                    if (l[i] == '8')
                        result++;
                } else
                    break;
            }
        }
        System.out.println(result);
    }
}

package Week2.N17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var T = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            var string = br.readLine().toCharArray();
            var start = 0;
            var end = string.length - 1;
            var counter = 0;
            while (start < end) {
                if (string[start] != string[end]) {
                    counter = 1;
                    if (check(string, start + 1, end))
                        break;
                    else if (check(string, start, end - 1))
                        break;
                    counter = 2;
                    break;
                }
                start++;
                end--;
            }
            sb.append(counter).append('\n');
        }
        System.out.println(sb);
    }

    private static boolean check(char[] string, int start, int end) {
        while (start < end) {
            if (string[start] != string[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

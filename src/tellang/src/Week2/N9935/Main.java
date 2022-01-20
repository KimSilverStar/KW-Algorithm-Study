package Week2.N9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var ori = br.readLine();
        var sb = new StringBuilder();
        var target = br.readLine();
        var index = 0;
        var size = target.length();
        while (index < ori.length()) {
            sb.append(ori.charAt(index));
            if (sb.length() >= size && sb.charAt(sb.length() - 1) == target.charAt(size - 1)) {
                if (sb.substring(sb.length() - size).equals(target)) {
                    sb.delete(sb.length() - size, sb.length());
                }
            }
            index++;
        }
        if (sb.isEmpty())
            sb.append("FRULA");
        System.out.println(sb);
    }
}
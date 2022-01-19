package Week2.N9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var sb = new StringBuilder(br.readLine());
        var target = br.readLine();
        var stack = new Stack<Integer>();
        var index = 0;
        var size = target.length();
        while (index <= sb.length() - size) {
            while (sb.charAt(index) == target.charAt(0)) {
                if (sb.substring(index, index + size).equals(target))
                    sb.delete(index, index + size);
                else if (sb.charAt(index + 1) == target.charAt(0))
                    stack.push(index++);
                else
                    index++;
                if (index > sb.length() - size || sb.isEmpty())
                    break;
            }
            index++;
        }
        while (!stack.isEmpty()) {
            var pop = stack.pop();
            if (sb.length() < pop + size)
                continue;
            if (sb.substring(pop, pop + size).equals(target)) {
                sb.delete(pop, pop + size);
            }
        }
        if (sb.isEmpty())
            sb.append("FRULA");
        System.out.println(sb);
    }
}

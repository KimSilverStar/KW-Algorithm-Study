package Week1.N1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();
        getPostfix(infix);
    }

    public static void getPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        stack.push('(');
        Map<Character, Integer> level = new HashMap<>();
        level.put('(', -1);
        level.put('+', 0);
        level.put('-', 0);
        level.put('*', 1);
        level.put('/', 1);

        StringBuilder sb = new StringBuilder();
        for (var c : infix.toCharArray()) {
            if (c == ')') {
                do {
                    char opr = stack.pop();
                    if (opr == '(') {
                        break;
                    } else {
                        sb.append(opr);
                    }
                } while (!stack.isEmpty());
            } else if ('*' <= c && c <= '/') {
                while (level.get(stack.peek()) >= level.get(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            } else {
                if (c == '(')
                    stack.push(c);
                else
                    sb.append(c);
            }
        }
        while (!stack.isEmpty()) {
            char opr = stack.pop();
            if (opr != '(') {
                sb.append(opr);
            }
        }
        System.out.println(sb);
    }
}


package Week2.N9466;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var T = parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            var n = parseInt(br.readLine());
            var st = new StringTokenizer(br.readLine());
            var list = new ArrayList<Integer>();
            var switchArr = new boolean[n + 1];
            list.add(-1);
            for (int i = 1; i <= n; i++) {
                list.add(parseInt(st.nextToken()));
            }
            var result = n;
            for (int i = 1; i <= n; i++) {
                if (switchArr[i])
                    continue;
                var visited = new boolean[n + 1];
                var stack = new Stack<Integer>();
                stack.push(i);
//                visited[i] = true;
                while (!stack.isEmpty()) {
                    var peek = stack.peek();
                    visited[peek] = true;
                    var next = list.get(peek);
                    if (switchArr[next])
                        break;
                    else if (visited[next]) {
                        while (!stack.isEmpty()) {
                            var pop = stack.pop();
                            switchArr[pop] = true;
                            result--;
                            if (pop.equals(next))
                                break;
                        }
                        break;
                    } else {
                        stack.push(next);
                    }
                }
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}

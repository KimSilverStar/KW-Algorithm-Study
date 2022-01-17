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
            var isClear = new boolean[n + 1];
            list.add(-1);
            for (int i = 1; i <= n; i++) {
                list.add(parseInt(st.nextToken()));
            }
            var result = n;
            for (int i = 1; i <= n; i++) {
                if (isClear[i])
                    continue;
                var visited = new boolean[n + 1];
                var stack = new Stack<Integer>();
                stack.push(i);
                while (!stack.isEmpty()) {
                    var pop = stack.pop();
                    visited[pop] = true;
                    var next = list.get(pop);
                    if (!isClear[next]) {
                        if (visited[next]) {
                            result--;
                            isClear[next] = true;
                        }
                        stack.push(next);
                    }
                }
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}

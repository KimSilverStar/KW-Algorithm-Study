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
            var nthVisited = new int[n + 1];
            var step = new int[n + 1];
            list.add(-1);
            for (int i = 1; i <= n; i++) {
                list.add(parseInt(st.nextToken()));
            }
            var result = n;
            for (int i = 1; i <= n; i++) {
                if (nthVisited[i] > 0)
                    continue;
                var stack = new Stack<Integer>();
                stack.push(i);
                for (int j = 1; !stack.isEmpty(); j++) {
                    var pop = stack.pop();
                    nthVisited[pop] = j;
                    step[pop] = i;
                    var next = list.get(pop);
                    if (nthVisited[next] > 0) {
                        if (step[next] == i)
                            result -= (nthVisited[pop] - nthVisited[next] + 1);
                        break;
                    } else
                        stack.push(next);
                }
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}

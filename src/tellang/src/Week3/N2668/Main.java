package Week3.N2668;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine()) + 1;

        var arr = new int[N];
        var set = new TreeSet<Integer>();
        var count = 0;
        var isExcluded = new boolean[N];

        for (int i = 1; i < N; i++) {
            arr[i] = parseInt(br.readLine());
        }

        var stack = new Stack<Integer>();
        for (int i = 1; i < N; i++) {
            if (isExcluded[i]) {
                continue;
            }
            var isVisited = new int[N];
            stack.push(i);
            isExcluded[i] = true;
            isVisited[i] = 1;
            while (!stack.isEmpty()) {
                var pop = stack.pop();
                var next = arr[pop];
                if (isVisited[next] == 2)
                    break;
                else if (isVisited[next] == 1) {
                    set.add(next);
                    stack.push(next);
                    isVisited[next] = 2;
                    count++;
                } else {
                    if (isExcluded[next])
                        break;
                    else {
                        stack.push(next);
                        isExcluded[next] = true;
                        isVisited[next] = 1;
                    }
                }
            }
        }
        var sb = new StringBuilder();
        sb.append(count).append('\n');
        for (var n : set) {
            sb.append(n).append('\n');
        }
        System.out.println(sb);

    }
}

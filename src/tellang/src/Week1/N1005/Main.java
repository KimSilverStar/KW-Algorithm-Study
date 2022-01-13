package Week1.N1005;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var T = parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int j = 0; j < T; j++) {
            var st = new StringTokenizer(br.readLine());
            var N = parseInt(st.nextToken());
            var K = parseInt(st.nextToken());
            var buildCosts = new int[N + 1];
            var map = new LinkedHashMap<Integer, List<Integer>>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; st.hasMoreTokens(); i++) {
                buildCosts[i] = parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                var preLevel = parseInt(st.nextToken());
                var level = parseInt(st.nextToken());
                if (!map.containsKey(level)) {
                    var list = new ArrayList<Integer>();
                    list.add(preLevel);
                    map.put(level, list);
                } else
                    map.get(level).add(preLevel);
            }
            var W = parseInt(br.readLine());

            sb.append(getMaxCost(W, map, buildCosts)).append('\n');
        }
        System.out.println(sb);
    }

    public static int getMaxCost(int start, Map<Integer, List<Integer>> map, int[] costs) {
        var c = new int[costs.length];
        var stack = new Stack<Pair>();
        var max = 0;
        stack.push(new Pair(start, costs[start]));
        while (!stack.isEmpty()) {
            var present = stack.pop();
            if (map.get(present.node) == null) {
                if (max < present.cost)
                    max = present.cost;
            } else {
                for (var pre : map.get(present.node)) {

                    var cost = costs[pre] + present.cost;
                    if (c[pre] < cost) {
                        stack.push(new Pair(pre, cost));
                        c[pre] = cost;
                    }
                }
            }
        }
        return max;
    }
}

class Pair {

    int node, cost;

    public Pair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

package Week1.N1005;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        if (!map.containsKey(start))
            return costs[start];
        var max = 0;
        for (var node : map.get(start)) {
            var v = getMaxCost(node, map, costs);
            if (max < v)
                max = v;
        }
        return max + costs[start];
    }
}

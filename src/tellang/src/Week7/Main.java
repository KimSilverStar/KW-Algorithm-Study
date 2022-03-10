package Week7;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var TC = parseInt(br.readLine());
        var INF = 25_000_001;
        var sb = new StringBuilder();
        for (int t = 0; t < TC; t++) {
            var roads = new LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>>();
            var st = new StringTokenizer(br.readLine());
            var N = parseInt(st.nextToken());
            var M = parseInt(st.nextToken());
            var W = parseInt(st.nextToken());
            var hasNegativeLoop = false;
            var arr = new int[N + 1];
            Arrays.fill(arr, INF);
            for (int n = 1; n <= N; n++) {
                roads.put(n, new LinkedHashMap<>());
            }
            init(br, roads, M, true);
            init(br, roads, W, false);
            var start = roads.keySet().iterator().next();
            arr[start] = 0;
            for (int i = 1; i <= N; i++) {
                for (var startNode : roads.keySet()) {
                    var nextNodes = roads.get(startNode);
                    for (var nextV : nextNodes.keySet()) {
                        if (arr[nextV] > arr[startNode] + nextNodes.get(nextV)) {
                            arr[nextV] = arr[startNode] + nextNodes.get(nextV);
                            if (i == N) {
                                hasNegativeLoop = true;
                                break;
                            }
                        }
                    }
                    if (hasNegativeLoop)
                        break;
                }
            }
            if (hasNegativeLoop)
                sb.append("YES");
            else
                sb.append("NO");
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void init(BufferedReader br,
        LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> map, int count, boolean isRoad)
        throws IOException {
        StringTokenizer st;
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            var S = parseInt(st.nextToken());
            var E = parseInt(st.nextToken());
            var T = parseInt(st.nextToken());
            if (!isRoad)
                T *= -1;
            var nextNodes = map.get(S);
            if (!nextNodes.containsKey(E) || nextNodes.get(E) > T) {
                if (isRoad)
                    map.get(E).put(S, T);
                nextNodes.put(E, T);
            }

        }
    }

}

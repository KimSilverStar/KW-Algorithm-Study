package Week7.N1504;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var E = parseInt(st.nextToken());
        int V1 = 1, V2 = 2;
        var INF = 200_000_001;
        var arr = new int[3][N + 1];
        for (int y = 0; y < 3; y++) {
            for (int x = 1; x <= N; x++) {
                arr[y][x] = INF;
            }
        }
        var map = new LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>>();

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            var v1 = parseInt(st.nextToken());
            var v2 = parseInt(st.nextToken());
            var c = parseInt(st.nextToken());
            init(v1, map);
            init(v2, map);
            map.get(v1).put(v2, c);
            map.get(v2).put(v1, c);
        }
        st = new StringTokenizer(br.readLine());
        var v1 = parseInt(st.nextToken());
        var v2 = parseInt(st.nextToken());

        arr[0][0] = V1;
        arr[V1][0] = v1;
        arr[V2][0] = v2;

        arr[0][1] = 0;
        arr[V1][v1] = 0;
        arr[V2][v2] = 0;

        for (int y = 0; y < 3; y++) {
            var q = new PriorityQueue<Node>(Comparator.comparingInt(o -> o.val));
            var start = arr[y][0];
            if (map.get(start) == null) {
                init(start, map);
            }
            q.offer(new Node(0, start));
            while (!q.isEmpty()) {
                var p = q.poll();
                var nextNodes = map.get(p.vertex);
                for (var nextV : nextNodes.keySet()) {
                    if (arr[y][nextV] > p.val + nextNodes.get(nextV)) {
                        arr[y][nextV] = p.val + nextNodes.get(nextV);
                        q.offer(new Node(arr[y][nextV], nextV));
                    }
                }
            }
        }

        int result = Math.min(arr[0][v1] + arr[V1][v2] + arr[V2][N],
            arr[0][v2] + arr[V2][v1] + arr[V1][N]);
        if (result >= INF)
            result = -1;
        System.out.println(result);
    }

    private static void init(int v1, HashMap<Integer, LinkedHashMap<Integer, Integer>> map) {
        if (!map.containsKey(v1))
            map.put(v1, new LinkedHashMap<>());
    }

}

class Node {

    int vertex, val;

    public Node(int val, int vertex) {
        this.val = val;
        this.vertex = vertex;
    }
}

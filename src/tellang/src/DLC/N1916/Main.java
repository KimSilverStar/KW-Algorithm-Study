package DLC.N1916;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());
        var dijkstra = new int[N + 1];
        var MAX = 100_000 * 1000;
        
        var map = new ArrayList<HashMap<Integer, Integer>>();
        for (int i = 0; i <= N; i++) {
            dijkstra[i] = MAX;
            map.add(new HashMap<>());
        }

        for (int m = 0; m < M; m++) {
            var st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            if (map.get(s).getOrDefault(e, MAX) > v) {
                map.get(s).put(e, v);
            }
        }
        var st = new StringTokenizer(br.readLine());
        var s = parseInt(st.nextToken());
        var e = parseInt(st.nextToken());

        var q = new PriorityQueue<Node>(Comparator.comparingInt(node -> node.cost));
        q.offer(new Node(s, 0));
        dijkstra[s] = 0;
        while (!q.isEmpty()) {
            Node p = q.poll();
            var nextCost = map.get(p.index);
            var nextNodes = nextCost.keySet();
            for (var nextNode : nextNodes) {
                var newCost = p.cost + nextCost.get(nextNode);
                if (dijkstra[nextNode] > newCost) {
                    dijkstra[nextNode] = newCost;
                    q.offer(new Node(nextNode, newCost));
                }
            }
        }

        System.out.println(dijkstra[e]);
    }
}

class Node {

    int index, cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

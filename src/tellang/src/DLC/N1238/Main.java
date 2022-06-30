package DLC.N1238;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X, TO_X = 0, FROM_X = 1;

    public static void main(String[] args) throws IOException {
        var MAX = 2 * 100 * 1000;
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        X = parseInt(st.nextToken());
        var map = new ArrayList<ArrayList<Node>>();
        var r_map = new ArrayList<ArrayList<Node>>();
        var costs = new int[2][N + 1];
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
            r_map.add(new ArrayList<>());
        }

        Arrays.fill(costs[TO_X], MAX);
        Arrays.fill(costs[FROM_X], MAX);
        costs[TO_X][X] = 0;
        costs[FROM_X][X] = 0;

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());
            int Ti = parseInt(st.nextToken());

            map.get(s).add(new Node(e, Ti));
            r_map.get(e).add(new Node(s, Ti));
        }

        dijkstra(map, X, costs[FROM_X]);
        dijkstra(r_map, X, costs[TO_X]);

        var result = 0;
        for (int n = 1; n <= N; n++) {
            result = Math.max(result, costs[TO_X][n] + costs[FROM_X][n]);
        }
        System.out.println(result);
    }

    private static void dijkstra(ArrayList<ArrayList<Node>> map, int s, int[] dijkstra) {
        var q = new PriorityQueue<Node>(Comparator.comparingInt(node -> node.cost));
        q.offer(new Node(s, 0));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (var nextNode : map.get(poll.index)) {
                int newCost = dijkstra[poll.index] + nextNode.cost;
                if (dijkstra[nextNode.index] > newCost) {
                    dijkstra[nextNode.index] = newCost;
                    q.offer(new Node(nextNode.index, newCost));
                }
            }
        }
    }

}

class Node {

    int index, cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

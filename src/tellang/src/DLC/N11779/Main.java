package DLC.N11779;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        M = parseInt(br.readLine());
        var map = new ArrayList<HashMap<Integer, Integer>>();
        for (int i = 0; i <= N; i++) {
            map.add(new HashMap<>());
        }
        var costs = new int[N + 1];
        var route = new int[N + 1];
        var MAX = 1_000 * 100_000;
        Arrays.fill(costs, MAX);
        for (int m = 0; m < M; m++) {
            var st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());
            int cost = parseInt(st.nextToken());
            map.get(s).put(e, Math.min(map.get(s).getOrDefault(e, MAX), cost));
        }
        var st = new StringTokenizer(br.readLine());
        int s = parseInt(st.nextToken());
        int e = parseInt(st.nextToken());
        costs[s] = 0;
        var q = new PriorityQueue<Node>(Comparator.comparingInt(node -> node.cost));
        q.offer(new Node(s, 0));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (var newNode : map.get(poll.index).keySet()) {
                var newCost = poll.cost + map.get(poll.index).get(newNode);
                if (costs[newNode] > newCost) {
                    costs[newNode] = newCost;
                    q.offer(new Node(newNode, newCost));
                    route[newNode] = poll.index;
                }
            }
        }
        var sb = new StringBuilder();
        var index = e;
        var count = 0;
        while (index != s) {
            count++;
            sb.insert(0, index + " ");
            index = route[index];
        }
        sb.insert(0, s + " ");
        var result = new StringBuilder();
        result.append(costs[e]).append('\n')
            .append(++count).append('\n')
            .append(sb);
        System.out.println(result);
    }
}

class Node {

    int index, cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

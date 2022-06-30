package DLC.N4485;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static int N;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var sb = new StringBuilder();
        int t = 0;
        while (++t > 0) {
            N = parseInt(br.readLine());
            if (N == 0)
                break;
            var map = new int[N][N];
            var cost = new int[N][N];
            var MAX = 125 * 125 * 9;
            var move = new int[]{1, 0, -1, 0, 1};
            for (int y = 0; y < N; y++) {
                var st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    map[y][x] = parseInt(st.nextToken());
                    cost[y][x] = MAX;
                }
            }
            cost[0][0] = map[0][0];
            var q = new ArrayDeque<Node>();
            q.offer(new Node(0, 0));
            while (!q.isEmpty()) {
                Node poll = q.poll();
                for (int i = 0; i < 4; i++) {
                    var nextY = poll.y + move[i];
                    var nextX = poll.x + move[i + 1];
                    if (isValid(nextY, nextX) &&
                        (cost[nextY][nextX] > (cost[poll.y][poll.x] + map[nextY][nextX]))) {
                        cost[nextY][nextX] = cost[poll.y][poll.x] + map[nextY][nextX];
                        q.offer(new Node(nextY, nextX));
                    }
                }
            }
            sb.append("Problem ").append(t).append(": ").append(cost[N - 1][N - 1]).append('\n');
        }
        System.out.println(sb);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N &&
            x >= 0 && x < N;
    }
}

class Node {

    int y, x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

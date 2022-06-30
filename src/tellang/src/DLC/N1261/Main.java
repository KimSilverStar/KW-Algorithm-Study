package DLC.N1261;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static char WALL = '1';
    static int N, M;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        M = parseInt(st.nextToken());
        N = parseInt(st.nextToken());
        var move = new int[]{-1, 0, 1, 0, -1};
        var isWall = new boolean[N][M];
        var count = new int[N][M];
        for (int n = 0; n < N; n++) {
            Arrays.fill(count[n], 100 * 100);
        }
        count[0][0] = 0;
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                isWall[n][m] = str.charAt(m) == WALL;
            }
        }

        var q = new ArrayDeque<Node>();
        q.offer(new Node(0, 0));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (int i = 0; i < 4; i++) {
                var nextY = poll.y + move[i];
                var nextX = poll.x + move[i + 1];
                if (isValid(nextY, nextX)) {
                    int nextCount = count[poll.y][poll.x];
                    if (isWall[nextY][nextX])
                        nextCount++;
                    if (count[nextY][nextX] > nextCount) {
                        count[nextY][nextX] = nextCount;
                        q.offer(new Node(nextY, nextX));
                    }
                }
            }
        }
        System.out.println(count[N - 1][M - 1]);
    }

    public static boolean isValid(int y, int x) {
        return y >= 0 && y < N &&
            x >= 0 && x < M;
    }
}

class Node {

    int y, x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

package Week2.N2468;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int[][] levels;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        levels = new int[N][N];

        var maxHeight = 0;
        var maxSafeZoneCount = 0;
        var move = new int[]{0, 1, 0, -1, 0};
        for (int i = 0; i < N; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                levels[i][j] = parseInt(st.nextToken());
                if (maxHeight < levels[i][j])
                    maxHeight = levels[i][j];
            }
        }

        for (int rainLevel = 1; rainLevel <= maxHeight; rainLevel++) {
            var isVisited = new boolean[N][N];
            var safeZoneCount = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (isVisited[y][x] || levels[y][x] < rainLevel) {
                        continue;
                    }
                    var q = new ArrayDeque<Pair>();
                    q.offer(new Pair(y, x));
                    while (!q.isEmpty()) {
                        var poll = q.poll();

                        for (int i = 0; i < 4; i++) {
                            var next = new Pair(poll.y + move[i], poll.x + move[i + 1]);
                            if (isValid(next, rainLevel)) {
                                if (!isVisited(next, isVisited)) {
                                    q.offer(next);
                                    isVisited[next.y][next.x] = true;
                                }
                            }
                        }
                    }
                    safeZoneCount++;
                    if (maxSafeZoneCount < safeZoneCount)
                        maxSafeZoneCount = safeZoneCount;
                }
            }
        }
        System.out.println(maxSafeZoneCount);
    }

    private static boolean isVisited(Pair next, boolean[][] isVisited) {
        return isVisited[next.y][next.x];
    }

    private static boolean isValid(Pair pair, int rainLevel) {
        return pair.y >= 0 && pair.y < N &&
            pair.x >= 0 && pair.x < N &&
            levels[pair.y][pair.x] >= rainLevel;
    }
}

class Pair {

    int y, x;

    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

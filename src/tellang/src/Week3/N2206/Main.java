package Week3.N2206;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    final static int VOID = 0, WALL = 1, VISITED = 2;
    static int N, M;
    static int[][] map, afterBreackMap;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var move = new int[]{0, 1, 0, -1, 0};
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        map = new int[N][M];
        afterBreackMap = new int[N][M];
        for (int y = 0; y < N; y++) {
            var arr = br.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                int num = arr[x] - '0';
                map[y][x] = num;
                afterBreackMap[y][x] = num;
            }
        }

        var q = new ArrayDeque<Position>();
        var bq = new ArrayDeque<Position>();
        map[0][0] = VISITED;
        afterBreackMap[0][0] = VISITED;
        q.offer(new Position(0, 0, 1, false));
        var result = -1;
        while (!(q.isEmpty() && bq.isEmpty())) {

            if (!q.isEmpty()) {
                var poll = q.poll();
                if (poll.y == N - 1 && poll.x == M - 1) {
                    result = poll.step;
                    q.clear();
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    var y = poll.y + move[i];
                    var x = poll.x + move[i + 1];
                    if (isValid(y, x) && map[y][x] < VISITED) {
                        if (map[y][x] == WALL) {
                            if (!poll.didBreak) {
                                var next = new Position(poll, move[i], move[i + 1]);
                                afterBreackMap[next.y][next.x] = (VISITED + next.step);
                                bq.offer(next);
                            }
                        } else {
                            var next = new Position(poll, move[i], move[i + 1]);
                            map[next.y][next.x] = (VISITED + next.step);
                            afterBreackMap[next.y][next.x] = (VISITED + next.step);
                            q.offer(next);
                        }

                    }
                }
            } else {
                var poll = bq.poll();
                if (poll.y == N - 1 && poll.x == M - 1) {
                    if ((poll.step < result) || result == -1)
                        result = poll.step;
                }
                for (int i = 0; i < 4; i++) {
                    var y = poll.y + move[i];
                    var x = poll.x + move[i + 1];
                    if (isValid(y, x)) {
                        if (map[y][x] != WALL) {
                            if (afterBreackMap[y][x] == VOID) {
                                var next = new Position(poll, move[i], move[i + 1]);
                                afterBreackMap[y][x] = (VISITED + next.step);
                                bq.offer(next);
                            } else if (afterBreackMap[y][x] > poll.step + VISITED + 1) {
                                var next = new Position(poll, move[i], move[i + 1]);
                                afterBreackMap[y][x] = (VISITED + next.step);
                                bq.offer(next);
                            }
                        }
                    }
                }
            }


        }
        System.out.println(result);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N &&
            x >= 0 && x < M;
    }
}

class Position {

    int y, x, step;
    boolean didBreak;

    public Position(int y, int x, int step, boolean didBreak) {
        this.y = y;
        this.x = x;
        this.step = step;
        this.didBreak = didBreak;
    }

    public Position(Position ori, int yOffset, int xOffset) {
        this.y = ori.y + yOffset;
        this.x = ori.x + xOffset;
        this.step = ori.step + 1;
        this.didBreak = ori.didBreak;
    }
}

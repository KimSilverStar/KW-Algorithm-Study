package Week3.N2206;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    final static char VOID = '0', WALL = '1', VISITED = '2';
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var move = new int[]{0, 1, 0, -1, 0};
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        map = new char[N][M];
        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
        }

        var q = new ArrayDeque<Position>();
        var bq = new ArrayDeque<Position>();
        map[0][0] = VISITED;
        q.offer(new Position(0, 0, 1, false));
        var result = -1;
        while (!(q.isEmpty() && bq.isEmpty())) {
            Position poll;
            if (!q.isEmpty()) {
                poll = q.poll();
                if (poll.y == N - 1 && poll.x == M - 1) {
                    result = poll.step;
                    q.clear();
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    if (isValid(poll, move[i], move[i + 1])) {
                        if (getStatus(poll, move[i], move[i + 1]) == WALL) {
                            if (!poll.didBreak) {
                                var next = new Position(poll, move[i], move[i + 1]);
                                next.didBreak = true;
                                map[next.y][next.x] = (char) (VISITED + next.step);
                                bq.offer(next);
                            }
                        } else {
                            var next = new Position(poll, move[i], move[i + 1]);
                            map[next.y][next.x] = (char) (VISITED + next.step);
                            q.offer(next);
                        }

                    }
                }
            } else {
                poll = bq.poll();
                if (poll.y == N - 1 && poll.x == M - 1) {
                    if ((poll.step < result) || result == -1)
                        result = poll.step;
//                    break;
                }
                for (int i = 0; i < 4; i++) {
                    if (isValid(poll, move[i], move[i + 1])) {
                        char status = getStatus(poll, move[i], move[i + 1]);
                        if ((status == VOID) || (status != WALL && (status > (VISITED + poll.step
                            + 1)))) {
                            var next = new Position(poll, move[i], move[i + 1]);
                            map[next.y][next.x] = (char) (VISITED + next.step);
                            bq.offer(next);
                        }

                    }
                }
            }


        }
        System.out.println(result);
    }

    static char getStatus(Position ori, int yOffset, int xOffset) {
        var y = ori.y + yOffset;
        var x = ori.x + xOffset;
        return map[y][x];
    }

    static boolean isValid(Position ori, int yOffset, int xOffset) {
        var y = ori.y + yOffset;
        var x = ori.x + xOffset;
        return y >= 0 && y < N &&
            x >= 0 && x < M &&
            map[y][x] < VISITED;
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

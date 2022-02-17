package Week5.N2638;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        var isVisited = new boolean[N][M];
        var move = new int[]{0, 1, 0, -1, 0};
        var map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = parseInt(st.nextToken());
            }
        }
        var q = new ArrayDeque<Position>();
        var dq = new ArrayDeque<Position>();
        dq.offer(new Position(0, 0));
        var result = -1;
        while (!dq.isEmpty()) {
            q = dq.clone();
            dq.clear();
            result++;
            while (!q.isEmpty()) {
                var poll = q.poll();
                for (int i = 0; i < 4; i++) {
                    var y = poll.y + move[i];
                    var x = poll.x + move[i + 1];
                    if (isValid(y, x) && !isVisited[y][x]) {
                        if (map[y][x] > 0) {
                            map[y][x]++;
                            if (map[y][x] > 2) {
                                dq.offer(new Position(y, x));
                                isVisited[y][x] = true;
                            }
                        } else {
                            q.offer(new Position(y, x));
                            isVisited[y][x] = true;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    public static boolean isValid(int y, int x) {
        return y >= 0 && y < N &&
            x >= 0 && x < M;
    }
}

class Position {

    int y, x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

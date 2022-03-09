package Week7.N2665;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    static char BLACK = '0';
    static int MAX = 2500;
    static int VISITED = 0;
    static int UNVISITED = MIN_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        var move = new int[]{0, 1, 0, -1, 0};
        var map = new int[N][N];
        var isBlack = new boolean[N][N];

        for (int y = 0; y < N; y++) {
            var arr = br.readLine().toCharArray();
            for (int x = 0; x < N; x++) {
                if (arr[x] == BLACK) {
                    map[y][x] = MAX;
                    isBlack[y][x] = true;
                } else {
                    map[y][x] = UNVISITED; // 1빼면 MAX_VALUE
                }
            }
        }
        var q = new ArrayDeque<Pair>();
        q.offer(new Pair(0, 0));
        map[0][0] = VISITED;
        while (!q.isEmpty()) {
            var p = q.poll();

            for (int i = 0; i < 4; i++) {
                var y = p.y + move[i];
                var x = p.x + move[i + 1];
                if (isValid(y, x) && map[y][x] != VISITED) {
                    var value = map[p.y][p.x];
                    if (isBlack[y][x]) {
                        if (map[y][x] > value + 1) {
                            q.offer(new Pair(y, x));
                            map[y][x] = value + 1;
                        }
                    } else {
                        if (map[y][x] - 1 > value - 1) {
                            q.offer(new Pair(y, x));
                            map[y][x] = value;
                        }
                    }
                }
            }
        }
        System.out.println(map[N - 1][N - 1]);
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N &&
            x >= 0 && x < N;
    }
}

class Pair {

    int y, x;

    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

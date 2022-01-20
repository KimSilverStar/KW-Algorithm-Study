package Week2.N14940;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int n, m, DEFAULT = -1, WALL = 0, SPACE = 1, START = 2;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        result = new int[n][m];
        var move = new int[]{0, 1, 0, -1, 0};
        Position start = null;
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                var num = parseInt(st.nextToken());
                if (num == SPACE) {
                    result[y][x] = DEFAULT;
                } else if (num == START) {
                    start = new Position(y, x);
                    result[y][x] = DEFAULT;
                } else
                    result[y][x] = WALL;
            }
        }
        var q = new ArrayDeque<Position>();
        q.offer(start);
        result[start.y][start.x] = 0;
        while (!q.isEmpty()) {
            var poll = q.poll();
            var step = result[poll.y][poll.x];
            for (int i = 0; i < 4; i++) {
                if (isValid(poll.y + move[i], poll.x + move[i + 1])) {
                    var next = new Position(poll.y + move[i], poll.x + move[i + 1]);
                    if (!isVisited(next)) {
                        q.offer(next);
                        result[poll.y + move[i]][poll.x + move[i + 1]] = step + 1;
                    }
                }
            }
        }

        System.out.println(convert(result));
    }

    static StringBuilder convert(int[][] matrix) {
        var sb = new StringBuilder();
        for (var arr : matrix) {
            for (var num : arr) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        return sb;
    }

    static boolean isVisited(Position next) {
        return result[next.y][next.x] != DEFAULT;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < n &&
            x >= 0 && x < m &&
            result[y][x] == DEFAULT;
    }
}

class Position {

    int y, x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

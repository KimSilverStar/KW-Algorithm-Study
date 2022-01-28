package Week3.N1600;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int W, H;
    static String WALL = "1";

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var K = parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        W = parseInt(st.nextToken());
        H = parseInt(st.nextToken());

        var map = new boolean[H][W][K + 1];
        var move = new int[]{0, -1, 0, 1, 0};
        var horseMove = new int[]{1, 2, -1, 2, 1, -2, -1, -2, 1};

        for (int y = 0; y < H; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < W; x++) {
                var node = st.nextToken();
                if (node.equals(WALL)) {
                    map[y][x][0] = true;
                }
            }
        }

        var q = new ArrayDeque<Position>();
        var min = -1;

        q.offer(new Position(0, 0, 0, 0));
        map[0][0][0] = true;

        while (!q.isEmpty()) {
            var poll = q.poll();

            if (poll.y == H - 1 && poll.x == W - 1) {
                if (min == -1 || min > poll.cost)
                    min = poll.cost;
            }

            int nextCost = poll.cost + 1;
            for (int i = 0; i < 4; i++) {
                var y = poll.y + move[i];
                var x = poll.x + move[i + 1];
                if (isValid(y, x) && !map[y][x][0] && !map[y][x][poll.horseSkill]) {
                    q.offer(new Position(y, x, nextCost, poll.horseSkill));
                    map[y][x][poll.horseSkill] = true;
                }
            }
            if (poll.horseSkill < K) {
                for (int i = 0; i < 8; i++) {
                    var y = poll.y + horseMove[i];
                    var x = poll.x + horseMove[i + 1];
                    var nextSkillCount = poll.horseSkill + 1;
                    if (isValid(y, x) && !map[y][x][0] && !map[y][x][nextSkillCount]) {
                        q.offer(new Position(y, x, nextCost, nextSkillCount));
                        map[y][x][nextSkillCount] = true;
                    }
                }

            }
        }

        System.out.println(min);
    }

    static boolean isValid(int y, int x) {

        return y >= 0 && y < H &&
            x >= 0 && x < W;
    }
}

class Position {

    int y, x, cost, horseSkill;

    public Position(int y, int x, int cost, int horseSkill) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.horseSkill = horseSkill;
    }
}
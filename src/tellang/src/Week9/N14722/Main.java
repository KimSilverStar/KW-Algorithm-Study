package Week9.N14722;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] value;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        var nextDrink = new int[]{1, 2, 0};
        var move = new int[]{0, -1, 0};
        value = new int[N + 1][N + 1];
        var milkMap = new int[N + 1][N + 1];
        var lastMilk = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            lastMilk[i][0] = 2;
            lastMilk[0][i] = 2;
        }
        for (int y = 1; y <= N; y++) {
            var st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                milkMap[y][x] = parseInt(st.nextToken());
            }
        }
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                for (int i = 0; i < 2; i++) {
                    var beforeY = y + move[i];
                    var beforeX = x + move[i + 1];
                    if (canDrink(nextDrink, lastMilk, milkMap, y, x, beforeY, beforeX)) {
                        if (value[y][x] < value[beforeY][beforeX] + 1) { // ??? ??
                            lastMilk[y][x] = milkMap[y][x];
                            value[y][x] = value[beforeY][beforeX] + 1;
                        }
                    } else {
                        if (value[y][x] <= value[beforeY][beforeX]) {
                            value[y][x] = value[beforeY][beforeX];
                            lastMilk[y][x] = lastMilk[beforeY][beforeX];
                        }
                    }
                }
            }
        }
        System.out.println(value[N][N]);
    }

    private static boolean canDrink(int[] nextDrink, int[][] lastMilk, int[][] milkMap, int y,
        int x,
        int beforeY, int beforeX) {
        return nextDrink[lastMilk[beforeY][beforeX]] == milkMap[y][x];
    }

}

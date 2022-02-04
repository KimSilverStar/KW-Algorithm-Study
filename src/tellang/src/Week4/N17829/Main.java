package Week4.N17829;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int[][] matrix;
    static int[] move = {0, 1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        matrix = new int[N][N];
        for (int y = 0; y < N; y++) {
            var st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                matrix[y][x] = parseInt(st.nextToken());
            }
        }
        System.out.println(polling(0, 0, N));
    }

    public static int polling(int y, int x, int size) {
        var list = new ArrayList<Integer>();
        if (size == 2) {
            for (int i = 0; i < 4; i++) {
                var newY = y + move[i];
                var newX = x + move[i + 1];
                list.add(matrix[newY][newX]);
            }
        } else {
            var nextSize = size / 2;
            for (int i = 0; i < 4; i++) {
                var newY = y + move[i] * nextSize;
                var newX = x + move[i + 1] * nextSize;
                list.add(polling(newY, newX, nextSize));
            }
        }
        list.sort(Comparator.reverseOrder());
        return list.get(1);
    }
}

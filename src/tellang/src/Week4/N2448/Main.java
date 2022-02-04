package Week4.N2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[][] matrix;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = Integer.parseInt(br.readLine());
        matrix = new char[N][N * 2];
        getStar(0, 0, N);
        var result = new StringBuilder();
        for (int y = N - 1; y >= 0; y--) {
            for (int x = 0; x < N * 2; x++) {
                if (matrix[y][x] == '*')
                    result.append(matrix[y][x]);
                else
                    result.append(' ');
            }
            result.append('\n');
        }
        System.out.println(result);
    }

    public static void getStar(int y, int x, int step) {

        if (step == 3)
            getStar(y, x);
        else if (step % 2 == 0) {
            var nextStep = step / 2;
            getStar(y + nextStep, x + nextStep, nextStep);
            getStar(y, x, nextStep);
            getStar(y, x + step, nextStep);
        }
    }

    public static void getStar(int y, int x) {
        for (int i = 0; i < 5; i++) {
            matrix[y][x + i] = '*';
        }
        for (int i = 1; i < 5; i += 2) {
            matrix[y + 1][x + i] = '*';
        }
        matrix[y + 2][x + 2] = '*';
    }
}

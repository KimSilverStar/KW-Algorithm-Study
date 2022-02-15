package Week5.N11660;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        matrix = new int[N + 1][N + 1];
        var spilt = new int[4];
        var sb = new StringBuilder();
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < N; x++) {
                matrix[y][x] += parseInt(st.nextToken());
                matrix[y][x + 1] = matrix[y][x];
            }
            matrix[y][N] += parseInt(st.nextToken());
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            var result = 0;
            for (int i = 0; i < 4; i++) {
                spilt[i] = parseInt(st.nextToken());
            }
            int x1 = spilt[1];
            int x2 = spilt[3];
            int y1 = spilt[0];
            int y2 = spilt[2];
            for (int y = y1; y <= y2; y++) {
                int xOffset = x1 - 1;
                result += (matrix[y][x2] - matrix[y][xOffset]);
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
}

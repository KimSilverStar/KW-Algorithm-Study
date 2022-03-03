package Week6.N1149;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var matrix = new int[N + 1][5];
        for (int n = 1; n <= N; n++) {
            var st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 3; i++) {
                matrix[n][i] = parseInt(st.nextToken());
                matrix[n][i] += min(matrix[n - 1][i - 1], matrix[n - 1][i + 1]);
            }
            matrix[n][0] = matrix[n][3];
            matrix[n][4] = matrix[n][1];
        }
        Arrays.sort(matrix[N]);
        System.out.println(matrix[N][0]);
    }

}

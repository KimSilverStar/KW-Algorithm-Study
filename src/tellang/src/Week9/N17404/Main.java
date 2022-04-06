package Week9.N17404;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var RGB = new int[3][N + 1][5];
        var cost = new int[N + 1][3];
        for (int n = 1; n < N + 1; n++) {
            var st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 3; i++) {
                cost[n][i] = parseInt(st.nextToken());
            }
        }
        var MAX_VALUE = 1000 * 1000 + 1;
        for (int s = 0; s < 3; s++) {
            for (int rgb = 0; rgb < 5; rgb++) {
                RGB[s][1][rgb] = MAX_VALUE;
            }
            RGB[s][1][s + 1] = cost[1][s];
            RGB[s][1][0] = RGB[s][1][3];
            RGB[s][1][4] = RGB[s][1][1];
            for (int n = 2; n < N; n++) {
                for (int rgb = 1; rgb <= 3; rgb++) {
                    RGB[s][n][rgb] = (cost[n][rgb - 1] + Math.min(RGB[s][n - 1][rgb - 1],
                        RGB[s][n - 1][rgb + 1]));
                }
                RGB[s][n][0] = RGB[s][n][3];
                RGB[s][n][4] = RGB[s][n][1];
            }
            for (int rgb = 1; rgb <= 3; rgb++) {
                if ((s + 1) == rgb)
                    RGB[s][N][rgb] = MAX_VALUE;
                else
                    RGB[s][N][rgb] = (cost[N][rgb - 1] + Math.min(RGB[s][N - 1][rgb - 1],
                        RGB[s][N - 1][rgb + 1]));
            }

        }
        var result = MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (RGB[i][N][j] < result)
                    result = RGB[i][N][j];
            }
        }
        System.out.println(result);
    }
}

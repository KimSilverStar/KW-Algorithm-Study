package DLC.N2156;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int DRINK = 0, CUT = 1;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var list = new int[N];
        var result = new int[2][N];
        for (int n = 0; n < N; n++) {
            list[n] = parseInt(br.readLine());
        }
        result[DRINK][0] = list[0];
        if (N > 1) {
            result[DRINK][1] = list[0] + list[1];
            result[CUT][1] = list[0];
            for (int n = 2; n < N; n++) {
                result[DRINK][n] = Math.max(result[CUT][n - 1] + list[n],
                    result[CUT][n - 2] + list[n - 1] + list[n]);
                result[CUT][n] = Math.max(result[DRINK][n - 1], result[CUT][n - 1]);
            }
        }
        System.out.println(Math.max(result[DRINK][N - 1], result[CUT][N - 1]));
    }
}

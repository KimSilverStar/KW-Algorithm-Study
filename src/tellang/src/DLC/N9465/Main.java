package DLC.N9465;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var result = new StringBuilder();

        var T = parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            result.append(getStickerCount(br)).append('\n');
        }
        System.out.println(result);
    }

    private static int getStickerCount(BufferedReader br) throws IOException {
        var N = parseInt(br.readLine());
        var stickers = new int[2][N];
        var result = new int[2][N];
        for (int y = 0; y < 2; y++) {
            var st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                stickers[y][x] = parseInt(st.nextToken());
                result[y][x] = stickers[y][x];
            }
        }

        if (N > 1) {
            for (int n = 0; n < N - 2; n++) {
                for (int r = 0; r < 2; r++) {
                    for (int i = 1; i < 3; i++) {
                        result[r][n + i] = max(result[r][n + i],
                            result[getOtherRow(r)][n] + stickers[r][n + i]);
                    }
                }
            }

            for (int r = 0; r < 2; r++) {
                result[r][N - 1] = max(result[r][N - 1],
                    result[getOtherRow(r)][N - 2] + stickers[r][N - 1]);
            }
        }

        return Math.max(result[0][N - 1], result[1][N - 1]);
    }

    private static int getOtherRow(int r) {
        return r == 0 ? 1 : 0;
    }
}

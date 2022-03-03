package Week6.N9251;

import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var arrY = br.readLine().toCharArray();
        var arrX = br.readLine().toCharArray();
        var LCS = new int[arrY.length + 1][arrX.length + 1];
        for (int y = 1; y <= arrY.length; y++) {
            for (int x = 1; x <= arrX.length; x++) {
                if (arrY[y - 1] == arrX[x - 1])
                    LCS[y][x] = (LCS[y - 1][x - 1] + 1);
                else
                    LCS[y][x] = max(LCS[y - 1][x], LCS[y][x - 1]);

            }
        }
        System.out.println(LCS[arrY.length][arrX.length]);
    }
}

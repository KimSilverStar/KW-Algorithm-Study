package DLC.N1038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, count = -1;
    static long result = -1;
    static int[] arr = new int[12];

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N <= 1022) {
            backTracking(10);
        }
        System.out.println(result);
    }

    static void backTracking(int index) {
        if (count <= N) {
            if (index < 0) {
                if (count == N) {
                    long result = 0;
                    for (int i = 0; i <= 10; i++) {
                        result += Math.pow(10, i) * arr[i];
                    }
                    Main.result = result;
                }
                count++;
            } else {
                int max = arr[index + 1];
                if (max == 0) {
                    arr[index] = 0;
                    backTracking(index - 1);
                    max = 9;
                } else
                    max--;

                for (int i = index; i <= max; i++) {
                    arr[index] = i;
                    backTracking(index - 1);
                }
            }

        }
    }
}

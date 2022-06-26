package DLC.N1629;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var A = parseInt(st.nextToken());
        var B = parseInt(st.nextToken());
        var C = parseInt(st.nextToken());
        long result = modular(A, B, C);
        System.out.println(result);

    }

    public static long modular(int A, int B, int C) {

        if (B == 1) {
            return (A % C);
        } else {
            long mod = modular(A, B / 2, C);
            long result = mod * mod;
            if (B % 2 == 0) {
                return result % C;
            } else {
                return (result % C * (A % C)) % C;
            }
        }
    }
}

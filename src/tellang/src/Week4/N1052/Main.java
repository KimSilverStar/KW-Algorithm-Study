package Week4.N1052;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var K = parseInt(st.nextToken());
        var size = toBinaryString(N).length() - 1;
        var result = 0;
        for (int i = size; i >= 0; i--) {
            var shifted = N >> i;
            if ((1 & shifted) == 1) {
                K--;
                if (K == 0) {
                    if ((shifted << i) == N)
                        break;
                    else {
                        result = ((shifted + 1) << i) - N;
                    }
                }
            }
        }
        System.out.println(result);
    }
}

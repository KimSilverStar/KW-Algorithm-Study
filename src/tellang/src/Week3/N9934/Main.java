package Week3.N9934;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var K = parseInt(br.readLine());
        var count = 0;
        var st = new StringTokenizer(br.readLine());
        var list = new ArrayList<String>();
        list.add("-1");
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
            count++;
        }
        var leafCount = count - (pow(2, K - 1) - 1);
        var offset = -1;
        var sbList = new ArrayList<StringBuilder>();
        for (int i = 0; i < count; i++) {
            sbList.add(new StringBuilder());
        }
        for (int i = 1; i <= count; i++) {
            for (int j = K - 1; (offset) <= j; j--) {
                String s = list.get(i);
                if (i % pow(2, j) == 0) {
                    sbList.get(j).append(s).append(' ');
                    break;
                }
                if (j == 1)
                    leafCount--;
                if (leafCount == 0)
                    offset++;
            }
        }
        var result = new StringBuilder();
        for (int i = K - 1; i >= 0; i--) {
            result.append(sbList.get(i)).append('\n');
        }
        System.out.println(result);
    }
}

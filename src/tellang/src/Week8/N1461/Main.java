package Week8.N1461;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var M = parseInt(st.nextToken());

        var positives = new ArrayList<Integer>();
        var negatives = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());

        positives.add(0);
        negatives.add(0);
        for (int n = 0; n < N; n++) {
            int pos = parseInt(st.nextToken());
            if (pos > 0)
                positives.add(pos);
            else
                negatives.add(pos);
        }

        positives.sort(Comparator.reverseOrder());
        negatives.sort(Comparator.naturalOrder());
        var result = 0;
        var posI = 0;
        var negaI = 0;

        if (positives.get(0) > abs(negatives.get(0))) {
            posI = M;
            result += positives.get(0);
        } else {
            negaI = M;
            result += abs(negatives.get(0));
        }

        for (; posI < positives.size(); posI += M) {
            int val = positives.get(posI);
            result += 2 * val;
        }
        for (; negaI < negatives.size(); negaI += M) {
            int val = abs(negatives.get(negaI));
            result += 2 * val;
        }

        System.out.println(result);
    }

}

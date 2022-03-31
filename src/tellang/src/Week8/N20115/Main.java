package Week8.N20115;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var q = new PriorityQueue<Double>(Comparator.reverseOrder());
        var st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            q.offer(Double.parseDouble(st.nextToken()));
        }

        while (q.size() > 1) {
            var r = q.poll();
            double l = q.poll();
            if (l > r)
                l += r / 2;
            else
                l = l / 2 + r;
            q.offer(l);
        }
        System.out.println(q.poll());
    }

}

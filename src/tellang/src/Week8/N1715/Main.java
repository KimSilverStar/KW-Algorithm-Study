package Week8.N1715;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        var q = new PriorityQueue<Integer>();
        for (int n = 0; n < N; n++) {
            q.offer(parseInt(br.readLine()));
        }
        var result = 0;
        while (q.size() > 1) {
            var r = q.poll();
            var l = q.poll();
            var next = r + l;
            result += next;
            q.offer(next);
        }
        System.out.println(result);
    }

}

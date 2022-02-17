package Week2.N16953;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var A = parseInt(st.nextToken());
        var B = parseInt(st.nextToken());
        var q = new ArrayDeque<Pair>();
        q.offer(new Pair(A, 0));
        var count = -2;
        while (!q.isEmpty()) {
            var poll = q.poll();
            if (poll.a == B) {
                count = poll.count;
                break;
            }
            long next = poll.a * 2;
            if (next <= B)
                q.offer(new Pair(next, poll.count + 1));
            next = poll.a * 10 + 1;
            if (next <= B)
                q.offer(new Pair(next, poll.count + 1));
        }
        System.out.println(count + 1);
    }
}

class Pair {

    long a;
    int count;

    public Pair(long a, int count) {
        this.a = a;
        this.count = count;
    }
}

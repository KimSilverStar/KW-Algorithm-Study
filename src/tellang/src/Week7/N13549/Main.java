package Week7.N13549;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int MAX_LEN = 100_001;

    public static void main(String[] args) throws IOException {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var N = parseInt(st.nextToken());
        var K = parseInt(st.nextToken());

        var arr = new int[MAX_LEN];
        for (int i = 0; i < MAX_LEN; i++) {
            arr[i] = MAX_VALUE;
        }
        var q = new PriorityQueue<Position>(Comparator.comparingInt(o -> o.val));
        q.offer(new Position(N, 0));
        arr[N] = 0;
        while (!q.isEmpty()) {
            var poll = q.poll();

            var nextIndex = poll.index - 1;
            var nextValue = poll.val + 1;
            if (isValid(nextIndex) && arr[nextIndex] > nextValue) {
                arr[nextIndex] = nextValue;
                q.offer(new Position(nextIndex, nextValue));
            }
            nextIndex = poll.index + 1;
            if (isValid(nextIndex) && arr[nextIndex] > nextValue) {
                arr[nextIndex] = nextValue;
                q.offer(new Position(nextIndex, nextValue));
            }
            nextIndex = poll.index * 2;
            nextValue = poll.val;
            if (isValid(nextIndex) && arr[nextIndex] > nextValue) {
                arr[nextIndex] = nextValue;
                q.offer(new Position(nextIndex, nextValue));
            }
        }
        System.out.println(arr[K]);
    }

    public static boolean isValid(int index) {
        return index >= 0 && index < MAX_LEN;
    }
}

class Position {

    int index, val;

    public Position(int index, int val) {
        this.index = index;
        this.val = val;
    }
}

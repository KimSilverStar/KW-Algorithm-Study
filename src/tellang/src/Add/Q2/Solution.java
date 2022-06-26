package Add.Q2;

import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {

    public static int solution(int n, int[] times) {

        var result = new int[n + 1];
        int MAX = 1_000_000 * 2_000 + 1;
        Arrays.fill(result, MAX);
        var q = new ArrayDeque<Node>();
        q.offer(new Node(0, 1));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (int i = 0; i < times.length; i++) {
                var nextN = poll.num + i + 1;
                var nextTime = poll.time + times[i];
                if (nextN <= n) {
                    if (result[nextN] > nextTime && poll.num >= i + 1) {
                        result[nextN] = nextTime;
                        q.offer(new Node(nextTime, nextN));
                    }
                } else
                    break;
            }
        }
        return result[n];
    }

    public static void main(String[] args) {
        var arr = new int[]{2, 4, 5};
        solution(5, arr);
    }
}

class Node {

    int time, num;

    public Node(int time, int num) {
        this.time = time;
        this.num = num;
    }
}
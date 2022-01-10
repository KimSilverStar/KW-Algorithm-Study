package Week1.N11268;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    final static int POP_Q = 0, ARRAY_IS_EMPTY = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new PriorityQueue<>(
            (o1, o2) -> {
                if (abs(o1)==abs(o2))
                    return o1-o2;
                else {
                    return abs(o1) - abs(o2);
                }
            });


        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n==POP_Q)
                if (q.isEmpty())
                    sb.append(ARRAY_IS_EMPTY).append("\n");
                else
                    sb.append(q.poll()).append("\n");
            else
                q.add(n);
        }
        System.out.println(sb);
    }
}

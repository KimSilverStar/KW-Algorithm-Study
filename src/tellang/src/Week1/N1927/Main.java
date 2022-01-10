package Week1.N1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int POP = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == POP) {
                if (q.isEmpty())
                    sb.append('0').append("\n");
                else
                    sb.append(q.poll()).append("\n");
            } else {
                q.add(n);
            }
        }
        System.out.println(sb);
    }
}

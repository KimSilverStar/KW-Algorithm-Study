package Week1.N2164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.addLast(i);
        }

        while (q.size() > 1) {
            q.pollFirst();
            q.addLast(q.pollFirst());
        }
        System.out.println(q.pollFirst());
    }
}

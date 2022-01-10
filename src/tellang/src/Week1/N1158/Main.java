package Week1.N1158;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        int N = sc.nextInt();
        int K = sc.nextInt();
        sb.append('<');
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }
        while (!q.isEmpty()) {
            for (int i = 0; i < K-1; i++) {
                q.add(q.poll());
            }
            sb.append(q.poll());
            if (!q.isEmpty()) {
                sb.append(", ");
            }
        }
        sb.append('>');
        System.out.println(sb);
    }
}

package Week1.N2075;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var N = parseInt(br.readLine());
        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stacks.add(new Stack<>());
        }
        for (int i = 0; i < N; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                stacks.get(j).push(parseInt(st.nextToken()));
            }
        }
        var answer = 0;
        for (int i = 0; i < N; i++) {
            stacks.sort(((o1, o2) -> o2.peek() - o1.peek()));
            answer = stacks.get(0).pop();
        }
        System.out.println(answer);
    }
}
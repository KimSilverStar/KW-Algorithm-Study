package Week2.N1987;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int R, C;

    static char[][] matrix;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        var move = new int[]{0, 1, 0, -1, 0};
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        matrix = new char[R][C];
        for (int i = 0; i < R; i++) {
            var string = br.readLine().toCharArray();
            matrix[i] = string;
        }
        var max = 0;
        var stack = new Stack<Step>();
        stack.push(new Step(0, 0, 1, new boolean['Z' - 'A' + 1]));
        while (!stack.isEmpty()) {
            var pop = stack.pop();
            pop.visited[getChar(pop) - 'A'] = true;
            if (max < pop.count)
                max = pop.count;
            for (int i = 0; i < 4; i++) {
                var next = new Step(pop.y + move[i], pop.x + move[i + 1], pop.count + 1,
                    pop.visited);
                if (isValid(next)) {
                    if (!isVisited(next)) {
                        stack.push(next);
                    }
                }
            }
        }
        System.out.println(max);
    }

    public static char getChar(Step step) {
        return matrix[step.y][step.x];
    }

    public static boolean isVisited(Step step) {
        return step.visited[getChar(step) - 'A'];
    }

    private static boolean isValid(Step step) {
        return step.y >= 0 && step.y < R &&
            step.x >= 0 && step.x < C;
    }
}

class Step {

    int y, x, count;
    boolean[] visited;

    public Step(int y, int x, int count, boolean[] visited) {
        this.y = y;
        this.x = x;
        this.count = count;
        this.visited = visited.clone();
    }
}

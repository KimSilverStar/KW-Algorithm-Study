package Week2.N14502;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int MAX = 0;
    public static int[][] matrix;
    public static List<Position> space = new ArrayList<>();
    static int N, M;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        var spaceSize = 0;
        matrix = new int[N][M];
        var move = new int[]{0, 1, 0, -1, 0};

        var viruses = new ArrayList<Position>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                var token = parseInt(st.nextToken());
                if (token == Status.space.ordinal()) {
                    spaceSize++;
                    space.add(new Position(i, j));
                } else if (token == Status.virus.ordinal())
                    viruses.add(new Position(i, j));
                matrix[i][j] = token;
            }
        }


        for (int i = 0; i < spaceSize; i++) {
            for (int j = 0; j < spaceSize; j++) {
                for (int k = 0; k < spaceSize; k++) {
                    if (i != j && j != k && k!= i){
                        var presentSpaceSize = spaceSize;
                        var matrix= addWall(new int[]{i, j, k});

                        presentSpaceSize-=3;
                        var q = new ArrayDeque<Position>();
                        for(var v: viruses)
                            q.offer(v);
                        while (!q.isEmpty()){
                            var p = q.poll();
                            if (presentSpaceSize > MAX) {
                                for (int l = 0; l < 4; l++) {
                                    var position = new Position(p, move[l], move[l + 1]);
                                    if (validPos(position) && isVoid(matrix, position)) {
                                        presentSpaceSize--;
                                        diffuse(matrix, position);
                                        q.offer(position);
                                    }
                                }
                            }
                        }
                        if (presentSpaceSize > MAX) {
                            MAX = presentSpaceSize;
                        }
                    }
                }
            }
        }
        System.out.println(MAX);
    }

    private static boolean isVoid(int[][] matrix, Position newPosition){
        return matrix[newPosition.y][newPosition.x] == Status.space.ordinal();
    }

    private static void diffuse(int[][] matrix, Position position){
        matrix[position.y][position.x] = Status.virus.ordinal();
    }

    private static boolean validPos(Position position){
        return position.y >= 0 && position.y < N &&
            position.x >= 0 && position.x < M;
    }

    private static int[][] addWall(int[] arr) {
        var clone = deepCopy(matrix);
        for (var i: arr){
            var spa = space.get(i);
            clone[spa.y][spa.x] = Status.wall.ordinal();
        }
        return clone;
    }
    private static int[][] deepCopy(int[][] matrix){
        var clone = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(matrix[i], 0, clone[i], 0, M);
        }
        return clone;
    }
}
class Position {
    int y, x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Position(Position ori, int y, int x) {
        this.y = y + ori.y;
        this.x = x + ori.x;
    }

    @Override
    public String toString() {
        return "Position{" +
            "y=" + y +
            ", x=" + x +
            '}';
    }
}
enum Status {
    space, wall, virus
}

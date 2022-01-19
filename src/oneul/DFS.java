/*
 * 1987번
 */
import java.io.*;
import java.util.StringTokenizer;


public class DFS {

    static char[][] grid;
    static boolean[] know = new boolean[26];
    static int r, c;
    static int max_depth = 0;
    static int[] dxs = {-1, 1, 0, 0};
    static int[] dys = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j  = 0; j < c; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < 26; i++) {
            know[i] = false;
        }

        know[grid[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(max_depth);
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && !know[grid[x][y] - 'A'];
    }

    public static void dfs(int x, int y, int depth) {
        if (max_depth < depth) {
            max_depth = depth;
        }

        for(int i = 0; i < 4; i++) {
            int new_x = x + dxs[i];
            int new_y = y + dys[i];

            if (canGo(new_x, new_y)) {
                know[grid[new_x][new_y] - 'A'] = true;
                dfs(new_x, new_y, depth + 1);
                know[grid[new_x][new_y] - 'A'] = false;
            }
        }
    }
}

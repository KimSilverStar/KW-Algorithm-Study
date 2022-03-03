package Week5.N2174;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int A, B, N, M;
    static Robot[] robots;
    static int[] move = new int[]{0, 1, 0, -1, 0};
    static char[] wises = new char[]{'E', 'N', 'W', 'S'};
    static Map<Character, Integer> wiseIndex = new HashMap<>();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        A = parseInt(st.nextToken());
        B = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        var sb = new StringBuilder();

        wiseIndex.put('E', 0);
        wiseIndex.put('N', 1);
        wiseIndex.put('W', 2);
        wiseIndex.put('S', 3);
        robots = new Robot[N + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            var x = parseInt(st.nextToken());
            var y = parseInt(st.nextToken());
            char wise = st.nextToken().charAt(0);
            robots[n] = new Robot(y, x, wiseIndex.get(wise));
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            var num = parseInt(st.nextToken());
            var action = st.nextToken().charAt(0);
            var count = parseInt(st.nextToken());
            if (action == 'F') {
                int collisionNum = isCollision(num, count);
                if (collisionNum == -1) {
                    if (!isValid(num, count)) {
                        sb.append("Robot ").append(num).append(" crashes into the wall");
                        System.out.println(sb);
                        return;
                    }
                } else {
                    sb.append("Robot ").append(num).append(" crashes into robot ")
                        .append(collisionNum);
                    System.out.println(sb);
                    return;
                }
            } else if (action == 'L') {
                int wiseIndex = robots[num].wiseIndex;
                if (wiseIndex == 3) {
                    robots[num].wiseIndex = 0;
                } else {
                    robots[num].wiseIndex++;
                }
            } else if (action == 'R') {
                int wiseIndex = robots[num].wiseIndex;
                if (wiseIndex == 0) {
                    robots[num].wiseIndex = 3;
                } else {
                    robots[num].wiseIndex--;
                }
            }
        }
        System.out.println("OK");
    }

    private static boolean isValid(Robot robot) {
        return robot.x >= 1 && robot.x <= A &&
            robot.y >= 1 && robot.y <= B;
    }

    private static boolean isValid(int num, int count) {
        var robot = robots[num];
        robot.y += (move[robot.wiseIndex] * count);
        robot.x += (move[robot.wiseIndex + 1] * count);
        return isValid(robot);
    }

    private static int isCollision(int num, int count) {
        for (int i = 1; i <= N; i++) {
            if (i == num)
                continue;
            var robot = robots[i];
            var wise = wises[robots[num].wiseIndex];
            switch (wise) {
                case 'W' -> {
                    if (robot.y == robots[num].y &&
                        (robot.x < robots[num].x) && (robot.x >= (robots[num].x - count)))
                        return i;
                }
                case 'E' -> {
                    if (robot.y == robots[num].y &&
                        (robot.x > robots[num].x) && (robot.x <= (robots[num].x + count)))
                        return i;
                }
                case 'N' -> {
                    if (robot.x == robots[num].x &&
                        (robot.y < robots[num].y) && (robot.y <= (robots[num].y + count)))
                        return i;
                }
                case 'S' -> {
                    if (robot.x == robots[num].x &&
                        (robot.y < robots[num].y) && (robot.y >= (robots[num].y - count)))
                        return i;
                }
            }

        }
        return -1;
    }
}

class Robot {

    int y, x, wiseIndex;
//    char wise;

    public Robot(int y, int x, int wiseIndex) {
        this.y = y;
        this.x = x;
        this.wiseIndex = wiseIndex;
    }

    @Override
    public String toString() {
        return "Robot{" +
            "y=" + y +
            ", x=" + x +
            ", wise=" + wiseIndex +
            '}';
    }
}

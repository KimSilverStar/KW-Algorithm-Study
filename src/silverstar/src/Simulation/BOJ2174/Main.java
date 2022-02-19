package Simulation.BOJ2174;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - Robot 클래스 정의
   => 로봇의 위치, 방향 저장
 - Command 클래스 정의
   => 명령을 내릴 로봇 번호, 명령 종류, 반복 횟수 저장
 - 로봇 명령 수행
   1) 방향 전환: L, R
     - L: E -> N -> W -> S -> E ...
     - R: E -> S -> W -> N -> E ...
   2) 전진: F
     - 로봇의 방향으로 위치(x 또는 y) 값 증가

2. 자료구조
 - Robot[]: 각 로봇 번호 별 로봇의 위치, 방향 저장
 - Command[]: 입력 명령들

3. 시간 복잡도
 - 전체 명령 개수 M x 각 명령의 반복 횟수 만큼 반복
   => 최대 100 x 100 >> 2억
*/

class Robot {
	public int x, y;
	public char direction;

	public Robot(int x, int y, char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}

class Command {
	public int robotIdx;		// 명령 수행할 로봇 번호
	public char kind;			// 명령 종류
	public int count;			// 명령 반복 횟수

	public Command(int robotIdx, char kind, int count) {
		this.robotIdx = robotIdx;
		this.kind = kind;
		this.count = count;
	}
}

public class Main {
	static int a, b;				// 땅 가로, 세로 크기
	static int n, m;				// 로봇 개수, 명령 개수
	static Robot[] robots;			// 각 로봇들의 위치, 방향
	static Command[] commands;		// 각 명령들

	static int crashedRobotIdx;		// 충돌한 로봇 번호 (예시 y번 번호)
	static StringBuilder sb = new StringBuilder();

	static void solution() {
		// 각 입력 명령을 차례로 실행
		for (int i = 1; i <= m; i++) {
			Command current = commands[i];
			Robot robot = robots[current.robotIdx];

			for (int j = 0; j < current.count; j++) {
				if (current.kind == 'L')
					turnLeft(robot);
				else if (current.kind == 'R')
					turnRight(robot);
				else if (current.kind == 'F') {
					goFront(robot);

					// 전진 후, 벽과 충돌 검사
					if (isCrashedWall(robot)) {
						sb.append("Robot ").append(current.robotIdx)
								.append(" crashes into the wall");
						return;
					}
					// 전진 후, 로봇과 충돌 검사
					if (isCrashedRobot(robot)) {
						sb.append("Robot ").append(current.robotIdx)
								.append(" crashes into robot ").append(crashedRobotIdx);
						return;
					}
				}
			}
		}

		sb.append("OK");
	}

	static void turnLeft(Robot robot) {
		if (robot.direction == 'E') robot.direction = 'N';
		else if (robot.direction == 'N') robot.direction = 'W';
		else if (robot.direction == 'W') robot.direction = 'S';
		else if (robot.direction == 'S') robot.direction = 'E';
	}

	static void turnRight(Robot robot) {
		if (robot.direction == 'E') robot.direction = 'S';
		else if (robot.direction == 'S') robot.direction = 'W';
		else if (robot.direction == 'W') robot.direction = 'N';
		else if (robot.direction == 'N') robot.direction = 'E';
	}

	static void goFront(Robot robot) {
		if (robot.direction == 'E') robot.x++;
		else if (robot.direction == 'S') robot.y--;
		else if (robot.direction == 'W') robot.x--;
		else if (robot.direction == 'N') robot.y++;
	}

	static boolean isCrashedWall(Robot robot) {
		if (robot.y < 1 || robot.y > b ||
				robot.x < 1 || robot.x > a)
			return true;
		return false;
	}

	static boolean isCrashedRobot(Robot robot) {
		for (int i = 1; i <= n; i++) {
			if (robot == robots[i])
				continue;

			if (robot.y == robots[i].y &&
					robot.x == robots[i].x) {
				crashedRobotIdx = i;
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());		// 땅의 가로
		b = Integer.parseInt(st.nextToken());		// 땅의 세로

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());		// 로봇 개수
		m = Integer.parseInt(st.nextToken());		// 명령 개수

		robots = new Robot[n + 1];			// [1] ~ [n] 사용
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char direction = st.nextToken().charAt(0);

			robots[i] = new Robot(x, y, direction);
		}

		commands = new Command[m + 1];		// [1] ~ [m] 사용
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int robotIdx = Integer.parseInt(st.nextToken());
			char kind = st.nextToken().charAt(0);
			int count = Integer.parseInt(st.nextToken());

			commands[i] = new Command(robotIdx, kind, count);
		}

		solution();
		System.out.println(sb);
	}
}

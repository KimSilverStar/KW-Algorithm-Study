package DFS_BFS.BOJ1600;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 최단 거리 / 최소 동작 => BFS
 - 말 처럼 동작한 횟수에 따라 탐색 경로 구분
   1) boolean[][][] check: 해당 지점 좌표, 말 처럼 동작한 횟수로 구분
   2) Queue 에 (현재 지점 좌표, 말 처럼 동작한 횟수, 전체 동작 횟수) 저장

 1) 원숭이
   - 다음 지점이 평지이고, 아직 방문 안한 경우
 2) 말
   - 다음 지점이 평지이고, 아직 방문 안한 경우
     + 말 처럼 더 동작 가능한 경우

2. 자료구조
 - boolean[][][]: 해당 지점 좌표, 말 처럼 동작한 횟수에 따른 방문 여부
 - Queue<Point>, LinkedList<Point>: BFS
   => Point: 해당 지점 좌표, 말 처럼 동작한 횟수, 전체 동작 횟수

3. 시간 복잡도
 - 총 BFS 1회 수행
*/

class Point {
	public int y, x;			// 현재 지점 좌표
	public int horseCount;		// 현재 지점까지 말 처럼 동작한 횟수
	public int totalCount;		// 현재 지점까지 전체 동작(말 + 원숭이) 횟수

	public Point(int y, int x, int horseCount, int totalCount) {
		this.y = y;
		this.x = x;
		this.horseCount = horseCount;
		this.totalCount = totalCount;
	}
}

public class Main {
	static int k;				// 원숭이가 말 처럼 동작 가능한 횟수
	static int w, h;			// h x w 행렬
	static int[][] map;
	static int minCount = Integer.MAX_VALUE;		// 출력 값: 전체 최소 동작 횟수

	static Queue<Point> queue = new LinkedList<>();
	static boolean[][][] check;
	static int[] dy = { -1, 1, 0, 0 };		// 원숭이로 이동
	static int[] dx = { 0, 0, -1, 1 };
	static int[] hdy = { -2, -2, -1, -1, +1, +1, +2, +2 };		// 말 처럼 이동
	static int[] hdx = { -1, +1, -2, +2, -2, +2, -1, +1 };

	static void bfs() {
		while (!queue.isEmpty()) {
			Point current = queue.remove();

			if (current.y == h - 1 && current.x == w - 1) {		// 목표 지점
				minCount = current.totalCount;
				return;
			}

			// 원숭이로 동작
			for (int i = 0; i < 4; i++) {
				int ny = current.y + dy[i];
				int nx = current.x + dx[i];

				if (!isValid(ny, nx))
					continue;

				if (map[ny][nx] == 0 &&
						!check[ny][nx][current.horseCount]) {
					check[ny][nx][current.horseCount] = true;
					queue.add(new Point(
							ny, nx, current.horseCount, current.totalCount + 1
					));
				}
			}

			// 말 처럼 동작
			for (int i = 0; i < 8; i++) {
				int ny = current.y + hdy[i];
				int nx = current.x + hdx[i];

				if (!isValid(ny, nx))
					continue;

				if (current.horseCount + 1 > k)
					continue;

				// 말 처럼 더 동작 가능한 경우
				if (map[ny][nx] == 0 &&
						!check[ny][nx][current.horseCount + 1]) {
					check[ny][nx][current.horseCount + 1] = true;
					queue.add(new Point(
							ny, nx, current.horseCount + 1, current.totalCount + 1
					));
				}
			}
		}
	}

	static boolean isValid(int y, int x) {
		return (0 <= y && y < h && 0 <= x && x < w);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		check = new boolean[h][w][k + 1];		// [0][0][0] ~ [h-1][w-1][k] 사용
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		check[0][0][0] = true;
		queue.add(new Point(0, 0, 0, 0));
		bfs();

		if (minCount != Integer.MAX_VALUE)
			System.out.println(minCount);
		else
			System.out.println(-1);
	}
}

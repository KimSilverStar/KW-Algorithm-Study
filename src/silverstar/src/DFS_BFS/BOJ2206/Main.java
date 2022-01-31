package DFS_BFS.BOJ2206;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 최단 경로 -> BFS
 - 벽을 부순 여부에 따라 탐색 방문 확인 구분
   => boolean[][][] check: [y][x][벽 부순 여부(0, 1)]

 1) 다음 지점이 벽인 경우
   case 1) 이때까지 벽을 부순 적 없고, 다음 지점을 아직 방문 안한 경우
          - 벽 부수고 이동

 2) 다음 지점이 벽이 아닌 경우
   case 1) 이때까지 벽을 안부순 경우 (!current.destroyed)
   case 2) 이때까지 벽을 부순적 있는 경우 (current.destroyed)

   case 1) 이때까지 벽을 안부순 경우 (!check[ny][nx][0])
   case 2) 이때까지 벽을 부순적 있는 경우 (!check[ny][nx][1])

2. 자료구조
 - Queue<Point>, LinkedList<Point>: BFS
   => Point: 현재 지점 좌표,
 - boolean[][][]: 방문 확인

3. 시간 복잡도
 - BFS 1번 수행
*/

class Point {
	public int y, x;
	public int distance;		// 현재 지점까지 이동한 거리
	public boolean destroyed;	// 현재 지점까지 이동하면서 벽 부순 여부

	public Point (int y, int x, int distance, boolean destroyed) {
		this.y = y;
		this.x = x;
		this.distance = distance;
		this.destroyed = destroyed;
	}
}

public class Main {
	static int n, m;				// n x m 행렬
	static char[][] map;
	static int minDistance = Integer.MAX_VALUE;			// 출력 값: 최단 거리

	static Queue<Point> queue = new LinkedList<>();
	static boolean[][][] check;		// [y][x][0 or 1]
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void bfs() {
		while (!queue.isEmpty()) {
			Point current = queue.remove();

			// 목표 지점에 도달한 경우
			if (current.y == n - 1 &&
					current.x == m - 1) {
				minDistance = current.distance;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int ny = current.y + dy[i];
				int nx = current.x + dx[i];

				if (ny < 0 || ny >= n || nx < 0 || nx >= m)
					continue;

				if (map[ny][nx] == '1') {		// 다음 지점이 벽인 경우
					// 이때까지 벽을 부순 적 없고, 다음 지점을 아직 방문 안한 경우
					if (!current.destroyed && !check[ny][nx][0]) {
						check[ny][nx][1] = true;		// 벽 부수고 이동
						queue.add(new Point(ny, nx, current.distance + 1, true));
					}
				}
				else {		// 다음 지점이 벽이 아닌 경우 => 그냥 이동
					// 이때까지 벽을 부순 적 없고, 다음 지점을 아직 방문 안한 경우
					if (!current.destroyed && !check[ny][nx][0]) {
						check[ny][nx][0] = true;
						queue.add(new Point(ny, nx, current.distance + 1, false));
					}
					// 이때까지 벽을 부순 적 있고, 다음 지점을 아직 방문 안한 경우
					if (current.destroyed && !check[ny][nx][1]) {
						check[ny][nx][1] = true;
						queue.add(new Point(ny, nx, current.distance + 1, true));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		check = new boolean[n][m][2];
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = input.charAt(j);
		}

		check[0][0][0] = true;
		queue.add(new Point(0, 0, 1, false));
		bfs();

		if (minDistance != Integer.MAX_VALUE)
			System.out.println(minDistance);
		else
			System.out.println(-1);
	}
}

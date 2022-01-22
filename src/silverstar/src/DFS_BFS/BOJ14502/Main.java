package DFS_BFS.BOJ14502;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 빈 칸들 중에서, 추가할 벽 3개 선택
   => Backtracking
 - 벽 3개 선택 후, 바이러스 퍼져나가는 영역 탐색 시작
   => 바이러스가 인접한 빈 칸으로 퍼져나가므로, BFS 사용
   => [0, 0] ~ [n, m] 확인, 바이러스 칸(2)이면 BFS 시작

2. 자료구조
 - Queue<Point>, LinkedList<Point>: BFS
 - boolean[][]: 방문 확인

3. 시간 복잡도

*/

class Point {
	public int row;
	public int col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main {
	static int n, m;				// n x m 행렬
	static int[][] originMap;		// 입력 행렬에 벽 3개 추가
	static int[][] virusMap;		// 벽 3개 추가 후, 바이러스 퍼뜨릴 맵
	static int originWallCount;

	static int minVirusCount = Integer.MAX_VALUE;
	static int virusCount;
	static Queue<Point> queue = new LinkedList<>();
	static boolean[][] check;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	/* Backtracking 으로 추가할 벽 3개 선택 후, BFS 로 바이러스 영역 탐색 */
	/* wallCount: 현재까지 선택된 벽 개수 */
	static void solution(int wallCount) {
		// 벽 3개 선택 완료한 경우, 바이러스 영역 탐색 시작
		if (wallCount == 3) {
			check = new boolean[n][m];		// 초기화
			virusCount = 0;
			virusMap = new int[n][m];		// originMap 을 Deep Copy
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++)
					virusMap[i][j] = originMap[i][j];
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					// 바이러스 칸(2)이고 아직 방문 안한 경우 => BFS 시작
					if (virusMap[i][j] == 2 && !check[i][j])
						bfs(i, j);
				}
			}

			minVirusCount = Math.min(minVirusCount, virusCount);
			return;
		}

		// 벽 3개 선택
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 빈 칸(0)인 경우
				if (originMap[i][j] == 0) {
					originMap[i][j] = 1;		// 벽 추가
					solution(wallCount + 1);

					originMap[i][j] = 0;		// 재귀호출 복귀 시점: 벽 복구
				}
			}
		}
	}

	static void bfs(int row, int col) {
		check[row][col] = true;
		queue.add(new Point(row, col));
		virusCount++;

		while (!queue.isEmpty()) {
			Point current = queue.remove();

			for (int i = 0; i < 4; i++) {
				int nextRow = current.row + dy[i];
				int nextCol = current.col + dx[i];

				if (0 <= nextRow && nextRow < n &&
					0 <= nextCol && nextCol < m) {
					// 다음 칸이 빈 칸(0)이고, 아직 방문 안한 경우 => 바이러스 퍼짐
					if (virusMap[nextRow][nextCol] == 0
							&& !check[nextRow][nextCol]) {
						check[nextRow][nextCol] = true;
						virusMap[nextRow][nextCol] = 2;
						queue.add(new Point(nextRow, nextCol));
						virusCount++;
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
		originMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
				if (originMap[i][j] == 1)
					originWallCount++;
			}
		}

		// 추가할 벽 3개 선택 후, 바이러스 영역 탐색
		solution(0);

		// 안전 영역 최대 크기 = (전체 칸 수) - (기존 벽 수 + 3) - (최소 바이러스 영역)
		int maxSafeCount = (n * m) - (originWallCount + 3) - minVirusCount;
		System.out.println(maxSafeCount);
	}
}

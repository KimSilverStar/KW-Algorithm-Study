package DFS_BFS.BOJ10026;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 단순히 탐색하는 영역의 수를 찾는 문제 => BFS 가 더 쉬움
 - 입력 행렬을 [0, 0] ~ [n, n] 확인
   => 아직 방문 안한 경우, 탐색 시작

2. 자료구조
 - Queue<Point>, LinkedList<Point>: BFS
 - boolean[][]: 방문 확인

3. 시간 복잡도
 - 대충 O(V + E) = O(5V)
   => 5 x n^2 = 5 x 10^4
   => 정상인, 적록색약 총 2번 수행
   => 2 x 5 x 10^4 = 10^5 << 1억 (1초)
*/

class Point {
	public int row;
	public int col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main_BFS {
	static int n;				// n x n 행렬
	static char[][] map;
	static int count1;			// 정상인이 본 구역의 수
	static int count2;			// 적록색약이 구역의 수

	static Queue<Point> queue;
	static boolean[][] check;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	// 정상인
	static void bfs1() {
		while (!queue.isEmpty()) {
			Point current = queue.remove();

			for (int i = 0; i < 4; i++) {
				int nextRow = current.row + dy[i];
				int nextCol = current.col + dx[i];

				if (0 <= nextRow && nextRow < n &&
					0 <= nextCol && nextCol < n) {
					// 색이 같고 아직 방문 안한 경우, 탐색 확장
					boolean isSameColor = map[current.row][current.col] == map[nextRow][nextCol];
					if (isSameColor && !check[nextRow][nextCol]) {
						check[nextRow][nextCol] = true;
						queue.add(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}

	// 적록색약
	static void bfs2() {
		while (!queue.isEmpty()) {
			Point current = queue.remove();

			for (int i = 0; i < 4; i++) {
				int nextRow = current.row + dy[i];
				int nextCol = current.col + dx[i];

				if (0 <= nextRow && nextRow < n &&
						0 <= nextCol && nextCol < n) {
					boolean isSameColor = map[current.row][current.col] == map[nextRow][nextCol];
					boolean isSimilarColor1 = (
							map[current.row][current.col] == 'R'
									&& map[nextRow][nextCol] == 'G'
					);
					boolean isSimilarColor2 = (
							map[current.row][current.col] == 'G'
									&& map[nextRow][nextCol] == 'R'
					);

					// 색이 같거나 구분 못하는 색이고 아직 방문 안한 경우, 탐색 확장
					if (!check[nextRow][nextCol] && (isSameColor
							|| isSimilarColor1 || isSimilarColor2)) {
						check[nextRow][nextCol] = true;
						queue.add(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		// 정상인
		queue = new LinkedList<>();
		check = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!check[i][j]) {
					count1++;
					check[i][j] = true;
					queue.add(new Point(i, j));
					bfs1();
				}
			}
		}

		// 적록색약
		queue = new LinkedList<>();
		check = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!check[i][j]) {
					count2++;
					check[i][j] = true;
					queue.add(new Point(i, j));
					bfs2();
				}
			}
		}

		System.out.println(count1 + " " + count2);
	}
}

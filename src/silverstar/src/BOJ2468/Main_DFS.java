package BOJ2468;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - Brute Force + DFS
 - 행렬 입력 받으면서, 지역의 최대 높이 구함
 - 비의 양: 0 ~ 최대 높이 - 1
   (비의 양이 0 이면, 모든 지역이 하나의 안전 영역으로 1개,
    비의 양이 최대 높이 이상이면, 모든 지역이 물에 잠겨서 안전 영역 0개)
   => 범위 안에서 비의 양을 1씩 늘려가면서 탐색 수행
 - 해당 지점이 잠기지 않았고, 아직 방문 안한 경우 탐색 확장

2. 자료구조
 - boolean[][]: 방문 확인

3. 시간 복잡도
 - DFS / BFS 의 시간 복잡도: O(V + E)
   => V: n^2, E: 한 vertex 당 4개 edge 가정하면 4V
   => O(V + E) = O(5V) = O(5 x n^2)
   => n 최대값 대입: 5 x 10^4
 - 비의 양에 따라 반복
   => 최대 100회 반복하면, 총 시간 복잡도 5 x 10^6 << 1억 (1초)
*/

public class Main_DFS {
	static int n;				// n x n 행렬
	static int[][] heights;
	static int maxHeight;		// 지역 최대 높이
	static int maxCount;		// 최대 개수
	static int count;			// 탐색 중인 영역의 개수

	static boolean[][] check;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void dfs(int row, int col, int rain) {
		check[row][col] = true;

		for (int i = 0; i < 4; i++) {
			int nextRow = row + dy[i];
			int nextCol = col + dx[i];

			if (0 <= nextRow && nextRow < n &&
				0 <= nextCol && nextCol < n) {
				// 물에 잠기지 않았고, 아직 방문 안한 경우
				if (heights[nextRow][nextCol] > rain
						&& !check[nextRow][nextCol])
					dfs(nextRow, nextCol, rain);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		heights = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				heights[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, heights[i][j]);
			}
		}

		// 비의 양에 따라 반복 확인
		for (int rain = 0; rain < maxHeight; rain++) {
			check = new boolean[n][n];
			count = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 물에 잠기지 않았고, 아직 방문 안한 경우
					if (heights[i][j] > rain
							&& !check[i][j]) {
						count++;
						dfs(i, j, rain);
					}
				}
			}

			maxCount = Math.max(maxCount, count);
		}

		System.out.println(maxCount);
	}
}

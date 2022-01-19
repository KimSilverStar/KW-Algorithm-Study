package DFS_BFS.BOJ1987;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 이동 가능한 최대 칸 수
   => 조건을 만족하는 모든 경우의 수를 확인해야 함
   => Backtracking + DFS
 - 재귀 종료 조건: 이동 가능한 지점이 없는 경우

2. 자료구조
 - boolean[]: 방문 확인
   => 길이 26 (대문자 알파벳 A ~ Z)

3. 시간 복잡도
*/

public class Main {
	static int r, c;				// r행 c열
	static char[][] board;
	static int maxCount;			// 출력값: 이동 가능한 최대 칸 수

	static boolean[] check = new boolean[26];
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	/* count: 이동한 거리 */
	static void dfs(int row, int col, int count) {
		for (int i = 0; i < 4; i++) {
			int nextRow = row + dy[i];
			int nextCol = col + dx[i];

			if (0 <= nextRow && nextRow < r &&
				0 <= nextCol && nextCol < c) {
				// 아직 방문 안한 알파벳인 경우
				char nextAlpha = board[nextRow][nextCol];
				if (!check[nextAlpha - 'A']) {
					check[nextAlpha - 'A'] = true;
					dfs(nextRow, nextCol, count + 1);

					// 재귀 호출 복귀 시점: 방문 확인 배열 복구
					check[nextAlpha - 'A'] = false;
				}
			}
		}

		maxCount = Math.max(maxCount, count);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		for (int i = 0; i < r; i++)
			board[i] = br.readLine().toCharArray();

		char start = board[0][0];
		check[start - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(maxCount);
	}
}

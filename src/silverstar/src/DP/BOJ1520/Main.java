package DP.BOJ1520;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - DFS + DP
 - 시작 지점 [0][0] ~ 끝 지점 [m-1][n-1]로 이동하면서,
   경로 개수를 DP 배열에 채워나감

 1) DP 배열 정의: int[][] dp
   - dp[y][x]: [y][x] 지점 -> 끝 지점 [m-1][n-1]로 가는 경로 개수
     => Top-Down 방식: DFS 재귀호출이 종료되면서, 경로 개수가 재귀적으로 갱신되어서 더해짐
     => Bottom-Up 방식으로 하면 경로 개수가 갱신되었을 때,
        이전 경로의 dp[][] 값도 함께 갱신해줘야 함
   - 출력 경로 개수: dp[0][0]

 2) 규칙 및 점화식
   - 초기식: dp[m-1][n-1] = 1


2. 자료구조
 - int[][] dp: DP 배열
   ① 자료형: 최대값 대략 500 x 500 = 250,000 << 21억 이므로, int 가능
   ② 메모리: 최대 4 x 500 x 500 byte = 10^6 byte = 1 MB


3. 시간 복잡도
 - 인접 행렬로 구현한 DFS 수행 시, 시간 초과 발생
   => DFS + DP 로 시간 단축
*/

public class Main {
	static int m, n;				// m x n 행렬
	static int[][] map;
	static boolean[][] finished;	// 탐색 완료 여부
	static int[][] dp;
	static int h;					// 출력, 경로 개수
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void dfs(int y, int x) {
		if (y == m - 1 && x == n - 1)	// 끝 지점 도착한 경우
			return;

		if (finished[y][x])		// 이미 끝 지점까지 탐색 완료한 경우
			return;

		finished[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (!isValid(ny, nx))
				continue;

			if (map[y][x] > map[ny][nx]) {		// 내리막 길
				if (finished[ny][nx])			// 이미 다음 지점부터 끝 지점까지 탐색 완료
					dp[y][x] += dp[ny][nx];		// => 더 이상 다음 지점 탐색 X
				else {
					dfs(ny, nx);
					dp[y][x] += dp[ny][nx];
				}
			}
		}
	}

	static boolean isValid(int y, int x) {
		return (0 <= y && y < m)
				&& (0 <= x && x < n);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[m][n];
		finished = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[m][n];
		dp[m-1][n-1] = 1;		// 초기식: 끝 지점 -> 끝 지점의 경로 개수 1개

		dfs(0, 0);

		h = dp[0][0];
		System.out.println(h);
	}
}

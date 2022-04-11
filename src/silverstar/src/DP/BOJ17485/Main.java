package DP.BOJ17485;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - 3가지 이동 방향: 왼쪽 아래, 아래, 오른쪽 아래
   => 각 지점을 각 이동 방향으로 이동 했을 때, 최소값을 저장
   ex) (3, 5) 지점을 이전 지점으로부터 [왼쪽 아래] 방향으로 이동했을 때, 연료 최소값

 1) DP 배열 정의: int[][][] dp
   - dp[y][x][k]: [y][x] 지점으로 k 방향으로 이동했을 때, 최소 연료 값
     (이동 방향 k: 왼쪽 아래 0, 아래 1, 오른쪽 아래 2)
   - 출력: 마지막 행 원소 중, 최소값

 2) 규칙 및 점화식
   - dp[y][x][0] = min(dp[y-1][x+1][1], dp[y-1][x+1][2]) + map[y][x]
   - dp[y][x][1] = min(dp[y-1][x][0], dp[y-1][x][2]) + map[y][x]
   - dp[y][x][2] = min(dp[y-1][x-1][0], dp[y-1][x-1][1]) + map[y][x]
   - 초기식
     ① 첫 열 dp[][0][2] = INF, 끝 열 dp[][m-1][0] = INF
        (INF: 올 수 없는 방향)
     ② 첫 행: 각 지점의 연료 map[y][x]


2. 자료구조
 - int[][][] dp: DP 배열
   ① 자료형: 최대값 10^3 x 10^3 x 100 = 10^8 << 21억 이므로, int 가능
   ② 메모리: 4 x 10^3 x 10^3 x 3 byte = 12 x 10^6 byte = 12 MB


3. 시간 복잡도
 - DP 배열 채우기: O(n x m)
 - 출력, 최소값 찾기: O(m x 3)
*/

public class Main {
	static int n, m;			// n x m 행렬
	static int[][] map;
	static int[][][] dp;
	static int minCost = Integer.MAX_VALUE;	// 출력, 최소 연료

	static void solution() {
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (isValid(i - 1, j + 1))
					dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + map[i][j];

				if (isValid(i - 1, j))
					dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + map[i][j];

				if (isValid(i - 1, j - 1))
					dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + map[i][j];
			}
		}

		// minCount 찾기 => 마지막 행 원소 중, 최소값
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < 3; k++)
				minCost = Math.min(minCost, dp[n-1][j][k]);
		}
	}

	static boolean isValid(int y, int x) {
		return (y >= 0 && y < n)
				&& (x >= 0 && x < m);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		dp = new int[n][m][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				// 초기식
				if (i == 0) {
					dp[i][j][0] = map[i][j];
					dp[i][j][1] = map[i][j];
					dp[i][j][2] = map[i][j];
				}

				if (j == 0)
					dp[i][j][2] = Integer.MAX_VALUE;
				else if (j == m - 1)
					dp[i][j][0] = Integer.MAX_VALUE;
			}
		}

		solution();
		System.out.println(minCost);
	}
}

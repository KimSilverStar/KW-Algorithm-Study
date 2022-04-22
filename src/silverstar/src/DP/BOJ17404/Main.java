package DP.BOJ17404;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 1) DP 배열 정의: int[][] dp
   - dp[i][j]: [1]번 집 ~ [i]번 집까지 색칠, [i]번 집은 [j] 색으로 색칠할 때의 최소 비용
     => 집의 색 j: Red 0, Green 1, Blue 2

 2) 규칙 및 점화식
   - [1]번 집과 마지막 [n]번 집의 색이 달라야 함
     => [1]번 집의 색을 고정하고, [2]번 ~ [n]번 집의 색을 정하면서 DP 배열 채움


2. 자료구조
 - int[][] dp: DP 배열
   ① 자료형: 원소 최대값 대략 10^3 x 10^3 = 10^6 << 21억 이므로, int 가능
   ② 메모리: 4 x 10^3 x 3 byte = 12 KB


3. 시간 복잡도
 - DP 배열 채우기: O(3 x n)
   => n 최대값 대입: 3 x 10^3 << 0.5억
*/

public class Main {
	static int n;						// 집의 개수
	static int[][] costs;				// 각 집의 RGB 색칠 비용
	static int[][] dp;
	static final int INF = 1_000_000;	// DP 배열 원소 최대값 (최대 누적 비용) = 10^3 x 10^3
	static int minCost = INF;			// 출력, 최소 비용

	static void solution() {
		for (int firstColor = 0; firstColor < 3; firstColor++) {
			// [1]번 집의 색 고정
			if (firstColor == 0) {
				dp[1][0] = costs[1][0];
				dp[1][1] = INF;
				dp[1][2] = INF;
			}
			else if (firstColor == 1) {
				dp[1][1] = costs[1][1];
				dp[1][0] = INF;
				dp[1][2] = INF;
			}
			else if (firstColor == 2) {
				dp[1][2] = costs[1][2];
				dp[1][0] = INF;
				dp[1][1] = INF;
			}

			// 나머지 [2]번 ~ [n]번 집 색칠
			for (int i = 2; i <= n; i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];

				// 마지막 [n]번 집의 색 != [1]번 집의 색
				if (i == n)
					dp[i][firstColor] = INF;
			}

			// 모든 집을 칠하는 최소 비용 minCost 갱신
			for (int j = 0; j < 3; j++)
				minCost = Math.min(minCost, dp[n][j]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		costs = new int[n + 1][3];			// [1][] ~ [n][] 사용
		dp = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++)
				costs[i][j] = Integer.parseInt(st.nextToken());
		}

		solution();
		System.out.println(minCost);
	}
}

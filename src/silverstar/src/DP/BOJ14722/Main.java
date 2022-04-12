package DP.BOJ14722;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - 우유 순서: 딸기(0) -> 초코(1) -> 바나나(2)
 - 최근에 마신 우유 종류에 따라
   현재 위치의 우유를 마실 수 있는지 여부가 결정됨
   => 최근 마신 "우유 종류를 구분"하여, DP 배열을 채움

 1) DP 배열 정의: int[][][] dp
   - dp[y][x][k]: [y][x] 지점까지 [k] 우유를 최근에 마셨을 때, 마신 우유 최대 개수
   - 출력, 최대 우유 개수: dp[n-1][n-1][k] 중, 최대값

 2) 규칙 및 점화식
   - 현재 위치의 우유 종류 currentMilk 와 각 [k] 우유 종류에 따라 DP 배열 채움


2. 자료구조
 - int[][][] dp: DP 배열
   ① 자료형: 최대값 대략 10^3 x 10^3 = 10^6 << 21억 이므로, int 가능
   ② 메모리: 최대 4 x 10^3 x 10^3 x 3 byte = 12 x 10^6 byte = 12 MB


3. 시간 복잡도
 - DP 배열 채우기: O(n^2)
   => n 최대값 대입: (10^3)^2 = 10^6 << 1억
*/

public class Main {
	static int n;				// n x n 행렬
	static int[][] map;
	static int[][][] dp;
	static int maxCount;		// 출력, 마실 수 있는 우유 최대 개수

	static void solution() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int currentMilk = map[i][j];

				if (currentMilk == 0) {
					dp[i][j][0] = Math.max(dp[i-1][j][2] + 1, dp[i][j-1][2] + 1);
				}
				else {
					dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j-1][0]);
				}

				if (currentMilk == 1 && dp[i][j][0] > dp[i][j][1]) {
					dp[i][j][1] = Math.max(dp[i - 1][j][0] + 1, dp[i][j - 1][0] + 1);
				}
				else {
					dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j-1][1]);
				}

				if (currentMilk == 2 && dp[i][j][1] > dp[i][j][2]) {
					dp[i][j][2] = Math.max(dp[i-1][j][1] + 1, dp[i][j-1][1] + 1);
				}
				else {
					dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i][j-1][2]);
				}
			}
		}

		// maxCount 찾기 => dp[n][n][k] 중, 최대값
		for (int k = 0; k < 3; k++)
			maxCount = Math.max(maxCount, dp[n][n][k]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		map = new int[n+1][n+1];			// [1][1] ~ [n][n] 사용
		dp = new int[n+1][n+1][3];			// [0]행, [0]열은 패딩
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solution();
		System.out.println(maxCount);
	}
}

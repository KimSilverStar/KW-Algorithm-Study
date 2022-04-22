package DP.BOJ10942;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 1) DP 배열 정의: boolean[][] dp
   - dp[i][j]: numbers[i] ~ numbers[j]까지의 수열에서 회문 여부

 2) 규칙 및 점화식
   - 초기식 1) 대각선 dp[i][i] = true
   - 초기식 2) 대각선 한 칸 위
   - 점화식: dp[i][j] = (numbers[i] == numbers[j]) && dp[i+1][j-1]


2. 자료구조
 - boolean[][] dp: DP 배열
   => 메모리 최대: 2,000 x 2,000 byte = 4 x 10^6 byte = 4 MB


3. 시간 복잡도
 - DP 배열 채우기: O(n^2)
   => n 최대값 대입: 4 x 10^6 << 2.5억
*/

class Pair {
	public int s, e;

	public Pair (int s, int e) {
		this.s = s;
		this.e = e;
	}
}

public class Main {
	static int n;				// 자연수 개수
	static int[] numbers;
	static int m;				// 질문 개수
	static Pair[] pairs;
	static boolean[][] dp;

	static void solution() {
		// 초기식
		for (int i = 1; i <= n; i++) {
			// 대각선
			dp[i][i] = true;

			// 대각선 한 칸 위
			if (i + 1 <= n && numbers[i] == numbers[i+1])
				dp[i][i+1] = true;
		}

		// 점화식
		for (int step = 2; step < n; step++) {
			for (int i = 1; i < n; i++) {
				if (i + step <= n)
					dp[i][i+step] = (numbers[i] == numbers[i+step] && dp[i+1][i+step-1]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		numbers = new int[n + 1];			// [1] ~ [n] 사용
		for (int i = 1; i <= n; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		m = Integer.parseInt(br.readLine());
		pairs = new Pair[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			pairs[i] = new Pair(s, e);
		}

		dp = new boolean[n + 1][n + 1];
		solution();

		StringBuilder sb = new StringBuilder();
		for (Pair p : pairs) {
			if (dp[p.s][p.e])
				sb.append(1).append("\n");
			else
				sb.append(0).append("\n");
		}
		System.out.println(sb);
	}
}

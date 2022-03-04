package DP.BOJ2133;
import java.io.*;

/*
1. 풀이
 - (3 x 홀수) 크기의 벽은 채울 수 없음

 1) DP 배열 정의: int[] dp
   - dp[i]: 3 x i 크기의 벽을 채우는 경우의 수
   - 초기식: dp[홀수] = 0, dp[2] = 3

 2) 규칙 및 점화식
   - (3 x 짝수) 크기의 벽만 고려
     => dp[짝수]
   - dp[2] = 3
   - (3 x 4) 부터는 특수 케이스 2개씩 존재
   ex ①) 3 x 4
   		 - (3 x 2), (3 x 2)
   		 - (3 x 4) 의 특수 케이스
   		 => (dp[2] x dp[2]) + 2
   ex ②) 3 x 6
   		 - (3 x 4), (3 x 2)
   		 - (3 x 2), (3 x 4)
   		   => (3 x 4), (3 x 2) 와 중복을 피하기 위해,
   		      (3 x 4)를 (3 x 4)의 특수 케이스로 채움
   		 - (3 x 6) 의 특수 케이스
   		 => (dp[4] x dp[2]) + (dp[2] x 2) + 2
   ex ③) 3 x 8
   		 - (3 x 6), (3 x 2)
   		 - (3 x 4), (3 x 4)
   		   => (3 x 6), (3 x 2) 와 중복을 피해가 위해,
   		      한쪽 (3 x 4)를 (3 x 4)의 특수 케이스로 채움
   		 - (3 x 2), (3 x 6)
   		   => 중복을 피하기 위해,
   		      (3 x 6)을 (3 x 6)의 특수 케이스로 채움
   		 - (3 x 8) 의 특수 케이스
   		 => (dp[6] x dp[2]) + (dp[4] x 2) + (dp[2] x 2) + 2

   - 점화식: dp[i] = (dp[i-2] x dp[2]) + (dp[i-4] x 2) +
   					 ... + (dp[2] x 2) + 2


2. 자료구조
 - int[] dp


3. 시간 복잡도
 - 2중 for 문 (n / 2)^2 만큼 반복: 대략 O(n^2)
   => n 최대값 대입: 30^2 = 900 << 2억
*/

public class Main {
	static int n;			// (3 x n) 크기의 벽
	static int count;		// 출력, 벽 채우는 경우의 수
	static int[] dp;

	static void solution() {
		dp[2] = 3;		// 초기식

		for (int i = 4; i <= n; i += 2) {		// dp[짝수] 항 채워나감
			// dp[i] = (dp[i-2] x dp[2])
			//		   + (dp[i-4] x 2) + (dp[i-6] x 2) + ... + (dp[2] x 2)
			//		   + 2

			dp[i] += (dp[i - 2] * dp[2]);		// (3 x i-2) + (3 x 2) 로 채우는 모든 경우의 수
			for (int j = i - 4; j >= 2; j -= 2)	// 중복을 제거하고 채우는 경우의 수
				dp[i] += (dp[j] * 2);
			dp[i] += 2;			// (3 x i) 크기의 벽을 채우는 특수 케이스 2개
		}

		count = dp[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];		// [1] ~ [n] 사용

		if (n % 2 != 0)				// (3 x 홀수) 크기의 벽 => 못 채움
			System.out.println(0);
		else {
			solution();
			System.out.println(count);
		}
	}
}

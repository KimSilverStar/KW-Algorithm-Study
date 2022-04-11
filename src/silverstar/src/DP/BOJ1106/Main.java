package DP.BOJ1106;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 고객을 적어도(최소) c명을 늘림
   => c명, c+1명, c+2명, ..., c+100명
 - 고객을 적어도 i명을 늘림
   => i명, i+1명, i+2명, ..., i+100명

 1) DP 배열 정의: int[] dp
   - dp[i]: 고객을 i명 만큼 늘리는데 드는 최소 비용
   - 출력, 고객을 최소 c명을 늘리는데 드는 최소 비용
     => dp[c] ~ dp[c + 100] 중, 최솟값

 2) 규칙 및 점화식
   - dp[i] = min(dp[i], dp[i - cost] + cost)


2. 자료구조
 - int[] dp: DP 배열
   ① 자료형: 최대 (c + 100) x 100
      => c 최대값 대입: 1,100 x 100 = 110,000 << 21억 이므로, int 가능
   ② 메모리: 최대 4 x 1,100 byte ~= 4KB


3. 시간 복잡도
 - DP 배열 채우기: 대략 O(n x (c+100))
   => n 최대값 대입: 200 x 1,100 = 22 x 10^4
 - 출력 최소값 찾기: O(100)
 => 총 시간 복잡도: 대략 22 x 10^4 << 2억
*/

public class Main {
	static int c, n;			// 목표 최소 영업 인원 수 c, 도시의 개수 n
	static int[] dp;
	static int minCost = Integer.MAX_VALUE;		// 출력, 최소 비용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		dp = new int[c + 101];
		Arrays.fill(dp, (c + 100) * 100);
		dp[0] = 0;

		for (int city = 0; city < n; city++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());

			// customer 명 ~ (c+100) 명 확인
			for (int i = customer; i <= c + 100; i++)
				dp[i] = Math.min(dp[i], dp[i - customer] + cost);
		}

		// minCost 찾기
		for (int i = c; i <= c + 100; i++)
			minCost = Math.min(minCost, dp[i]);

		System.out.println(minCost);
	}
}

package BruteForce.BOJ2961;
import java.io.*;
import java.util.*;

/*
1. 풀이
 - 재료를 선택하는 모든 조합(중복 X, 순서 상관 X) 구하여 확인
   => Backtracking 으로 재료 선택 O / 선택 X
 - n 개 재료에 대해, 1개를 선택하는 조합 ~ n개를 선택하는 조합 차례로 확인

2. 자료구조
 - Taste[]: 각 재료의 신 맛, 쓴 맛

3. 시간 복잡도
 - 조합 C(n, k) = n! / (n-k)! x k!
 - n개 재료에 대해 1개 선택 조합, 2개 선택 조합, ..., n개 선택 조합
   => C(n, 1) + C(n, 2) + ... + C(n, n)
 - n 최대값 대입: C(10, 1) + C(10, 2) + ... + C(10, 10)
   => C(10, 5) = 252 이므로 가능
*/

public class Main {
	static int n;						// 재료 개수
	static Taste[] tastes;				// 각 재료들의 신 맛, 쓴 맛, 맛 차이
	static int minDiff = Integer.MAX_VALUE;
	static List<Integer> selected;		// 선택된 재료들

	/* 재료 선택 조합 C(n, k) 구성하여 확인 */
	static void combination(int k, int startIdx) {
		// k 개 재료 선택 완료한 경우 => 맛 차이 계산
		if (selected.size() == k) {
			int totalSour = 1;			// 전체 신 맛 = 각 재료들의 신 맛의 곱
			int totalBitter = 0;		// 전체 쓴 맛 = 각 재료들의 쓴 맛의 합

			for (int idx : selected) {
				totalSour *= tastes[idx].sour;
				totalBitter += tastes[idx].bitter;
			}

			int diff = Math.abs(totalSour - totalBitter);
			minDiff = Math.min(minDiff, diff);
			return;
		}

		// 재료 조합 구성
		for (int i = startIdx; i < n; i++) {
			selected.add(i);
			combination(k, i + 1);

			// 재귀 호출 복귀 => 선택 복구
			selected.remove(Integer.valueOf(i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		tastes = new Taste[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			tastes[i] = new Taste(sour, bitter);
		}

		// C(n, 1), C(n, 2), ..., C(n, n) 확인
		for (int i = 1; i <= n; i++) {
			selected = new ArrayList<>();
			combination(i, 0);
		}

		System.out.println(minDiff);
	}
}

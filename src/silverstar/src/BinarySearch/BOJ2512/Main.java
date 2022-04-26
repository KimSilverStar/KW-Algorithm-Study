package BinarySearch.BOJ2512;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 - 예산 상한액의 금액에 따라, 분배 예산이 정해짐
   => 예산 상한액을 1원 ~ m원 까지 이진 탐색
 - Init Call: binarySearch(1, m)
 - mid: 상한액
 - sum: 해당 상한액에 따른 분배 예산 합

 1) 분배 예산 합 sum > 총 예산 m 인 경우
   - start = start, end = mid - 1 로 다시 탐색
 2) 분배 예산 합 sum < 총 예산 m 인 경우
   - 배정한 예산 최대값 갱신
   - start = mid + 1, end = end 로 다시 탐색
 3) 분배 예산 합 sum == 총 예산 m 인 경우
   - 탐색 종료


2. 자료구조
 - int[]: 입력, 각 지방의 요청 예산


3. 시간 복잡도
 - 이진 탐색 n번 수행: O(n x log_2 m)
   => n, m 최대값 대입: 10^4 x log_2 10^9 = (9 x 10^4) log_2 10 ~= 27 x 10^4 << 1억
*/

public class Main {
	static int n;					// 지방 개수
	static int[] requestCosts;		// 각 지방의 요청 예산
	static int m;					// 총 예산
	static int maxCost;				// 배정한 예산 최대값

	static void binarySearch(int start, int end) {
		if (start > end)
			return;

		int mid = (start + end) / 2;	// 상한액
		int sum = 0;					// 분배 예산 총합
		int max = 0;					// 배정한 예산 최대값

		for (int i = 0; i < n; i++) {
			if (requestCosts[i] <= mid) {
				sum += requestCosts[i];
				max = Math.max(max, requestCosts[i]);
			}
			else {
				sum += mid;
				max = Math.max(max, mid);
			}
		}

		if (sum > m) {
			binarySearch(start, mid - 1);
		}
		else if (sum < m) {
			maxCost = Math.max(maxCost, max);
			binarySearch(mid + 1, end);
		}
		else {
			maxCost = max;
			return;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		requestCosts = new int[n];
		for (int i = 0; i < n; i++) {
			requestCosts[i] = Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(br.readLine());

		binarySearch(1, m);
		System.out.println(maxCost);
	}
}

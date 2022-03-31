package Greedy.BOJ2437;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 추를 가벼운 무게 순으로 정렬했을 때,
   인접한 추와 무게 차이가 작아야 더 촘촘히 무게 합 구성 가능
 - [0] ~ [i]번째 추의 무게 누적합
   = [0] ~ [i]번째 추로 구성 가능한 최대 무게

 - n개 추들을 무게 작은 순으로 정렬
 - ([0]번째 추 ~ [i]번째 추 까지의 무게 누적합 + 1) < 다음 [i+1]번째 추의 무게인 경우
   => ([0]번째 추 ~ [i]번째 추 까지의 무게 누적합 + 1) 무게 구성 불가능

2. 자료구조
 - int[]: 각 추의 무게

3. 시간 복잡도
 - 배열 정렬: O(n log_2 n)
   => n 최대값 대입: 10^3 log_2 10^3 = (3 x 10^3) log_2 10 ~= 9 x 10^3
 - 누적합 더해가며 무게 구성 여부 확인: O(n)
   => n 최대값 대입: 10^3
 => 전체 시간 복잡도: O(n + n log_2 n)
 => n 최대값 대입: (9 x 10^3) + 10^3 = 10^4 << 1억
*/

public class Main {
	static int n;				// 추 개수
	static int[] weights;		// 각 추의 무게
	static int minSum;			// n개 추로 구성할 수 없는 최소 무게 합

	static void solution() {
		int sum = 0;			// 추 무게 누적합

		for (int i = 0; i < n; i++) {
			if (sum + 1 < weights[i]) {
				minSum = sum + 1;
				return;
			}

			sum += weights[i];
		}

		minSum = sum + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		weights = new int[n];
		for (int i = 0; i < n; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(weights);

		solution();
		System.out.println(minSum);
	}
}

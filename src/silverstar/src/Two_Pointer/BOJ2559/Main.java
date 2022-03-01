package Two_Pointer.BOJ2559;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 연속적인 날짜의 온도 합
   => 연속적인 특징을 이용하여 투 포인터 사용

 - 최초 앞에서부터 k 개의 합을 구함
 - 2개의 포인터 ptr1, ptr2 가 각각 [0], [k] 에서 시작
 - 다음을 ptr2 가 [n-1]을 가리킬 때까지 반복
 1) 합에서 ptr1 이 가리키는 값을 빼고, ptr1++
 2) 합에서 ptr2 가 가리키는 값을 더하고, ptr2++

2. 자료구조
 - int maxSum: 출력, 연속한 날짜의 최대 온도 합
   => 최대 10^5 x 100 = 10^7 << 21억 이므로, int 가능

3. 시간 복잡도
 - 대략 반복문을 수열 길이 n 만큼 반복: O(n)
*/

public class Main {
	static int n, k;				// 전체 날짜 수 n, 연속 날짜 수 k
	static int[] temperatures;
	static int maxSum;
	static int sum;

	static void solution() {
		// 최초 앞에서부터 k 개의 합을 구함
		for (int i = 0; i < k; i++)
			sum += temperatures[i];

		maxSum = sum;

		int ptr1 = 0;
		int ptr2 = k;

		while (ptr2 <= n - 1) {
			sum -= temperatures[ptr1++];
			sum += temperatures[ptr2++];
			maxSum = Math.max(maxSum, sum);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		temperatures = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			temperatures[i] = Integer.parseInt(st.nextToken());
		}

		solution();
		System.out.println(maxSum);
	}
}

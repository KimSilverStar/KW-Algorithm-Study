package Two_Pointer.BOJ1806;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - "이 수열에서 연속된 수들의 부분합 중 ~"
   => 연속된 특징을 이용하여 투 포인터 사용

 - 2개의 포인터 ptr1, ptr2 모두 [0]에서 시작
 - ptr2 가 마지막 원소를 넘어갈 때까지 다음을 반복
 1) 원소 합 < s 인 경우
   - 원소 합 >= s 가 될 때까지, ptr2 를 오른쪽으로 이동 (ptr2++)
 2) 원소 합 >= s 인 경우
   - 원소 합 < s 가 될 때까지, ptr1 을 오른쪽으로 이동 (ptr1++)

2. 자료구조
 - int s: 목표 최소 부분합
   => 최대 10^8 (1억) << 21억 이므로, int 가능

3. 시간 복잡도
 - 대략 반복문을 수열 길이만큼 반복: O(n)
*/

public class Main {
	static int n, s;
	static int[] numbers;
	static int sum;
	static int minLen = Integer.MAX_VALUE;

	static void solution() {
		int ptr1 = 0;
		int ptr2 = 0;

		while (true) {
			if (sum >= s) {
				// 원소 합 < s 가 될 때까지, ptr1 을 오른쪽으로 이동 (ptr1++)
				sum -= numbers[ptr1];
				minLen = Math.min(minLen, ptr2 - ptr1);
				ptr1++;
			}
			else if (ptr2 == n)
				break;
			else		// sum < s && ptr2 != n
				// 원소 합 >= s 가 될 때까지, ptr2 를 오른쪽으로 이동 (ptr2++)
				sum += numbers[ptr2++];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		numbers = new int[n];
		for (int i = 0; i < n; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		solution();

		if (minLen == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(minLen);
	}
}

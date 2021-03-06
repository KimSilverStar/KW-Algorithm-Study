package Two_Pointer.BOJ20922;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - n 최대값: 2 x 10^5
   => 시간 복잡도 O(n^2) 미만이어야 함 (n x n 2중 for 문 사용 X)
 - "연속 부분 수열" => 투 포인터

 - 2개의 포인터 ptr1, ptr2가 [0]에서 시작
 - 다음을 ptr2가 마지막 원소를 가리킬 때까지 반복
   1) ptr2 가 가리키는 원소의 등장 횟수 < k 인 경우
     - 현재 ptr2 가 가리키는 원소의 등장 횟수 + 1
     - ptr2 를 오른쪽으로 한 칸 이동 (ptr2++)
   2) ptr2 가 가리키는 원소의 등장 횟수 >= k 인 경우
     - ptr2 가 가리키는 원소의 등장 횟수 < k 가 될 때까지,
       ptr1 을 오른쪽으로 한 칸씩 이동 (ptr1++)

2. 자료구조
 - int[] count: 원소 등장 횟수 카운트
   ex) count[10] = 2 이면, 원소 10 이 2번 등장
   => [1] ~ [100,000] 사용
   => 메모리: 4 x 10^5 byte = 0.4 MB

3. 시간 복잡도
 - 대략 O(n)
   => n 최대값 대입: 2 x 10^5 << 1억
*/

public class Main {
	static int n, k;			// 수열 길이 n, 최대 동일 원소 개수 k
	static int[] numbers;
	static int maxLen = Integer.MIN_VALUE;		// 출력, 최장 연속 부분수열의 길이
	static int[] count = new int[100001];		// 원소 등장 횟수

	static void solution() {
		int ptr1 = 0;
		int ptr2 = 0;

		// ptr2 가 마지막 원소를 가리킬 때까지 반복
		while (ptr2 < n) {
			int numPtr2 = numbers[ptr2];		// ptr2 가 가리키는 수열의 원소

			if (count[numPtr2] < k) {
				count[numPtr2]++;
				ptr2++;
			}
			else {
				count[ numbers[ptr1] ]--;
				ptr1++;
			}

			maxLen = Math.max(maxLen, ptr2 - ptr1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		numbers = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		solution();
		System.out.println(maxLen);
	}
}

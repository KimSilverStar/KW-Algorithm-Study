package Backtracking.BOJ1182;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 아이디어
 *** 백트래킹을 이용한 브루트 포스로 모든 조합에 대해 확인
 - 입력 수열의 원소 [0] ~ [n-1] 까지 차례로 확인
 - 각 원소에 대해 2가지 경우: 선택 O / 선택 X
 - 재귀 종료 조건: 입력 수열의 마지막 원소까지 확인한 경우
 - 예외 처리) 구성한 부분수열 중, 공집합은 제외
   => 입력 수열의 원소에서 1개도 선택하지 않은 경우는 제외

2. 자료구조
 - int[]: 입력 수열
 - boolean[]: 부분 수열 선택

3. 시간 복잡도
 - 입력 수열의 각 원소에 대해 2가지 경우 존재 (선택 O / 선택 X)
   => 재귀 호출 매번 각 2번
 - 시간 복잡도: O(2^n)
   => n 최대값 대입: 2^20 = 1,048,576 << 2억
*/

public class Main {
	static int n;					// n개 정수
	static int s;					// 부분수열의 목표 합
	static int[] numbers;			// 입력 수열
	static int count;				// 출력: 부분수열의 합 == s 인 경우의 수

	static boolean[] check;
	static int selectCount;			// 구성한 부분수열의 원소 개수 (입력 수열에서 선택한 개수)

	static void solution(int depth) {
		if (depth == n) {				// 배열의 마지막 원소까지 모두 확인한 경우
			if (selectCount == 0)
				return;

			// 구성한 부분 수열의 합 계산
			int sum = 0;

			for (int i = 0; i < n; i++) {
				if (check[i])
					sum += numbers[i];
			}

			if (sum == s)
				count++;

			return;
		}

		// 부분 수열 구성
		check[depth] = true;				// 선택 O
		selectCount++;
		solution(depth + 1);

		check[depth] = false;				// 선택 X
		selectCount--;
		solution(depth + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		numbers = new int[n];
		check = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		solution(0);
		System.out.println(count);
	}
}


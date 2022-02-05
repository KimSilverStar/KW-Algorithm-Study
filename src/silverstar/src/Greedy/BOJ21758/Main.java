package Greedy.BOJ21758;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 3개의 케이스로 나누어서 각각 확인
 1) 벌통 맨 오른쪽에 고정, 벌 1 맨 왼쪽 고정, 벌 2 위치 선택
 2) 벌통 맨 왼쪽에 고정, 벌 1 맨 오른쪽 고정, 벌 2 위치 선택
 3) 벌 1 맨 왼쪽 고정, 벌 2 맨 오른쪽 고정, 벌통 위치 선택

 - 벌이 채집한 꿀 양 계산을 위해 누적합을 미리 계산하여 저장

2. 자료구조
 - long[] toRightTotal
 - long[] toLeftTotal

3. 시간 복잡도
 - 2개의 누적합 배열 저장: O(2 x n)
 - 3개의 케이스 확인 O(3 x n)
 => 총 시간 복잡도: O(5 x n)
 => n 최대값 대입: 5 x 10^5 >> 1억
*/

public class Main {
	static int n;
	static int[] honeys;			// 각 장소의 꿀 양
	static long maxCount;

	static long total;				// 모든 장소의 꿀 양 합
	static long[] toRightTotal;
	static long[] toLeftTotal;

	/* 벌통 맨 오른쪽에 고정, 벌 1 맨 왼쪽 고정, 벌 2 위치 선택 */
	static void case1() {
		long bee1, bee2;		// 벌 1, 2 가 채집한 꿀 양

		for (int i = 1; i <= n - 2; i++) {
			bee1 = total - honeys[0] - honeys[i];
			bee2 = total - toRightTotal[i];
			maxCount = Math.max(maxCount, bee1 + bee2);
		}
	}

	/* 벌통 맨 왼쪽에 고정, 벌 1 맨 오른쪽 고정, 벌 2 위치 선택 */
	static void case2() {
		long bee1, bee2;		// 벌 1, 2 가 채집한 꿀 양

		for (int i = 1; i <= n - 2; i++) {
			bee1 = total - honeys[n - 1] - honeys[i];
			bee2 = total - toLeftTotal[i];
			maxCount = Math.max(maxCount, bee1 + bee2);
		}
	}

	/* 벌 1 맨 왼쪽 고정, 벌 2 맨 오른쪽 고정, 벌통 위치 선택 */
	static void case3() {
		long bee1, bee2;		// 벌 1, 2 가 채집한 꿀 양

		for (int i = 1; i <= n - 2; i++) {
			bee1 = toRightTotal[i] - honeys[0];
			bee2 = toLeftTotal[i] - honeys[n - 1];
			maxCount = Math.max(maxCount, bee1 + bee2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		honeys = new int[n];
		toRightTotal = new long[n];
		toLeftTotal = new long[n];

		long temp = 0;
		for (int i = 0; i < n; i++) {
			honeys[i] = Integer.parseInt(st.nextToken());

			temp += honeys[i];
			toRightTotal[i] = temp;
		}
		total = toRightTotal[n - 1];

		temp = 0;
		for (int i = n - 1; i >= 0; i--) {
			temp += honeys[i];
			toLeftTotal[i] = temp;
		}

		case1();
		case2();
		case3();

		System.out.println(maxCount);
	}
}

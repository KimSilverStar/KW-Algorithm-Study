package BinarySearch.BOJ11663;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 각 선분에 대해 이진 탐색 2번 수행
   => 시작 점에 대해 이진 탐색, 끝 점에 대해 이진 탐색
   => 각 선분에 포함되는 최소 좌표 인덱스, 최대 좌표 인덱스 구함

2. 자료구조
 - int[]: 입력 점들
 - Pair[]: 입력 선분들 (각 선분의 시작, 끝 점 저장)

3. 시간 복잡도
 - 배열 정렬: O(n log_2 n)
 - 1개 선분에 대해 이진 탐색 2번 수행: O(2 x log_2 n)
   => m개 선분에 대해 이진 탐색: O(2 x m x log_2 n)
 => 총 시간 복잡도: O((n + 2m) x log_2 n)
 => n, m 최대값 대입: (3 x 10^5) x log_2 10^5 = (15 x 10^5) log_2 10 ~= 45 x 10^5 << 1억
*/

public class Main_Recursive {
	static int n, m;				// 점 개수 n, 선분 개수 m
	static int[] positions;			// n개 점
	static Pair[] liens;			// 각 선분의 시작, 끝 점
	static StringBuilder sb = new StringBuilder();
	static int minIdx, maxIdx;

	static void solution() {
		// 각 선분에 포함되는 최소 좌표 인덱스, 최대 좌표 인덱스 구함
		for (int i = 0; i < m; i++) {
			minIdx = Integer.MAX_VALUE;
			binarySearch1(0, n - 1, liens[i].startPos);

			maxIdx = Integer.MIN_VALUE;
			binarySearch2(0, n - 1, liens[i].endPos);

			int count = 0;
			if (minIdx <= maxIdx)
				count = maxIdx - minIdx + 1;
			sb.append(count).append("\n");
		}
	}

	/* 선분에 포함되는 최소 좌표 인덱스 minIdx 구함 */
	static void binarySearch1(int startIdx, int endIdx, int target) {
		if (startIdx > endIdx)
			return;

		int midIdx = (startIdx + endIdx) / 2;
		if (target < positions[midIdx]) {
			minIdx = Math.min(minIdx, midIdx);
			binarySearch1(startIdx, midIdx - 1, target);
		}
		else if (target > positions[midIdx]) {
			binarySearch1(midIdx + 1, endIdx, target);
		}
		else {
			minIdx = midIdx;
			return;
		}
	}

	/* 선분에 포함되는 최대 좌표 인덱스 maxIdx 구함 */
	static void binarySearch2(int startIdx, int endIdx, int target) {
		if (startIdx > endIdx)
			return;

		int midIdx = (startIdx + endIdx) / 2;
		if (target < positions[midIdx]) {
			binarySearch2(startIdx, midIdx - 1, target);
		}
		else if (target > positions[midIdx]) {
			maxIdx = Math.max(maxIdx, midIdx);
			binarySearch2(midIdx + 1, endIdx, target);
		}
		else {
			maxIdx = midIdx;
			return;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		positions = new int[n];
		for (int i = 0; i < n; i++) {
			positions[i] = Integer.parseInt(st.nextToken());
		}

		liens = new Pair[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startPos = Integer.parseInt(st.nextToken());
			int endPos = Integer.parseInt(st.nextToken());

			liens[i] = new Pair(startPos, endPos);
		}

		Arrays.sort(positions);		// 이진 탐색을 위한 배열 정렬

		solution();
		System.out.println(sb);
	}
}

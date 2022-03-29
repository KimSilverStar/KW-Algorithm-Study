package Greedy.BOJ20115;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 절반을 버리고 합치므로, 절반을 버리는 드링크는 양이 작아야 함
 1) 드링크 양 배열을 작은 순으로 정렬
 2) 배열의 맨 뒤 원소(가장 큰 양)를 선택하여, 앞에서부터 합쳐나감

2. 자료구조
 - int[]: 각 드링크의 양

3. 시간 복잡도
 - 배열 정렬: O(n log2 n)
 - 드링크 합치기: O(n)
*/

public class Main {
	static int n;					// 드링크 개수
	static int[] amounts;			// 각 드링크의 양
	static double maxAmount;		// 출력, 최대 드링크 양

	static void solution() {
		maxAmount += amounts[n - 1];

		for (int i = 0; i < n - 1; i++) {
			maxAmount += (double) amounts[i] / 2;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		amounts = new int[n];
		for (int i = 0; i < n; i++)
			amounts[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(amounts);

		solution();
		System.out.println(maxAmount);
	}
}

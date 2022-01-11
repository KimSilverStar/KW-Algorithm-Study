package DataStructure.PriorityQueue.BOJ2075;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
1. 풀이
 - 1차원 배열 arr 에 모든 수 입력
 - 정렬하여 n 번째 큰 수 출력

2. 자료구조
 - int[]: 행렬의 모든 수들 저장

3. 시간 복잡도
 - 2중 for 문에서 배열 입력: O(n^2)	(n: 입력 n)
 - 배열 정렬: O(n lg n)		(n: 정렬 item 개수)
 => 총 O(n^2 + n^2 lg n^2) => n^2 + (2n^2 lg n)
 => 입력 n 최대값 대입: 1,500^2 + (2 * 1,500^2 lg 1,500)
    = 2,250,000 + (4,500,000 lg 1,500)
    = 2,250,000 + 4,500,000 * (lg 15 + lg 100)
    = 2,250,000 + 4,500,000 * (lg 15 + 2)
    ~= 2,250,000 + 4,500,000 * 3
    ~= 15,750,000 << 1억 (1초)
*/

public class Main_Sort {
	static int n;			// n x n 행렬에서 n 번째 큰 수 찾기
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		numbers = new int[n * n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				numbers[n * i + j] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		System.out.println(numbers[numbers.length - n]);
	}
}

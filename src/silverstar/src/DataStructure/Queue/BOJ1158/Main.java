package DataStructure.Queue.BOJ1158;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

/*
1. 풀이
 - Queue 에 1 ~ n 번의 사람 저장
 - Queue 가 empty 할 때까지 다음을 반복
   1) (k-1) 명을 Queue 의 앞에서 뽑아서 뒤로 추가
   2) 이후, 1명을 Queue 에서 삭제 및 출력

2. 자료구조
 - Queue<Integer>: 1 ~ n 번의 사람 저장

3. 시간 복잡도
 - Queue 에 n명 저장: O(n)
 - Queue 가 empty 할 때까지 반복: n
   => 1회 반복에서 Queue 에서 총 k 명 제거: k
   => 총 반복: O(nk)
 => 총 시간 복잡도: O(n + nk)
 => n, k 최대값 대입: 5,000 + 5,000 * 5,000 = 25,005,000 << 2억 (2초)
*/

public class Main {
	static int n, k;	// 1 ~ n 번의 사람, 반복적으로 k 번째 사람 제거
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder("<");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++)
			queue.add(i);

		while (!queue.isEmpty()) {
			for (int i = 1; i <= k - 1; i++) {		// k-1 명을 뒤로 보냄
				int first = queue.remove();
				queue.add(first);
			}
			sb.append(queue.remove());

			if (!queue.isEmpty())
				sb.append(", ");
		}

		sb.append(">");
		System.out.println(sb.toString());
	}
}

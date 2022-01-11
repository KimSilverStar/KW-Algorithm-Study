package DataStructure.PriorityQueue.BOJ2075;
import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

/*
1. 풀이
 - PriorityQueue 에 최대 n 개 수들이 저장되도록 관리하면서 저장
   => 새로운 item 삽입 시 Heap 재정렬이 요구되므로, 최소한의 개수 n 개로 유지
 - 최초 n 개의 수들을 PriorityQueue 에 저장하고,
   이후 부터는 add(new item), remove() 반복

2. 자료구조
 - PriorityQueue<Integer>

3. 시간 복잡도
 - PriorityQueue 의 시간 복잡도
   => 삽입 / 삭제: O(log n)	(n: 노드 개수)
*/

public class Main_PriorityQueue {
	static int n;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				int number = Integer.parseInt(st.nextToken());

				pq.add(number);
				if (i != 0)			// 최초에만 n개 입력
					pq.remove();
			}
		}

		System.out.println(pq.peek());
	}
}

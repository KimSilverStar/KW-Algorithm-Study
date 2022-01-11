package DataStructure.Queue.BOJ2164;
import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

/*
1. 풀이
 - Queue 에 1 ~ n 카드 저장
 - Queue 에 카드 1장이 남을 때까지 다음을 반복
   1) Queue 의 맨 앞 카드를 버림
   2) Queue 의 그 다음 카드를 뽑아서 맨 뒤에 추가

2. 자료구조
 - Queue<Integer>: 카드 저장

3. 시간 복잡도
 - 대충 O(n)
*/

public class Main {
	static int n;		// 1 ~ n 카드
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++)
			queue.add(i);

		while (!queue.isEmpty()) {
			if (queue.size() == 1)
				break;

			queue.remove();		// 맨 앞 카드 버림

			int popped = queue.remove();
			queue.add(popped);
		}

		System.out.println(queue.peek());
	}
}

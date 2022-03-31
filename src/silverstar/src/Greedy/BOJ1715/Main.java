package Greedy.BOJ1715;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 - 합쳐진 카드는 나중에 다른 카드와 다시 합쳐지므로,
   카드 개수가 많은 카드 묶음은 나중에 합쳐야 함
   => 카드 개수 적은 순으로 합쳐나감

 1) 우선순위 큐에 카드 개수 저장
   - 카드 개수 적은 순으로 정렬
 2) 카드 개수 적은 순으로 2개 뽑아서,
    합친 카드 개수를 다시 우선순위 큐에 추가
   - 우선순위 큐 삭제 2번, 삽입 1번
   - 우선순위 큐에 원소 1개 남을 때까지 반복

2. 자료구조
 - PriorityQueue<Integer>

3. 시간 복잡도
 - PriorityQueue 의 삽입/삭제 시간 복잡도: O(log_2 n)
 - n개 카드 묶음을 (n-1)번 합침
   => 삽입 + 삭제 횟수: 3(n-1)
 => 전체 시간 복잡도: 대략 O(3n log_2 n)
 => n 최대값 대입: (3 x 10^5) log_2 10^5 = (15 x 10^5) log_2 10
 					~= 45 x 10^5 << 2억
*/

public class Main {
	static int n;			// 카드 묶음 개수
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int minCount;	// 출력, 최소 비교 횟수

	static void solution() {
		while (pq.size() > 1) {
			int card1 = pq.remove();
			int card2 = pq.remove();

			int mergedCard = card1 + card2;
			minCount += mergedCard;
			pq.add(mergedCard);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++)
			pq.add(Integer.parseInt(br.readLine()));


		solution();
		System.out.println(minCount);
	}
}

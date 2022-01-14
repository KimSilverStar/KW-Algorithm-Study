package DataStructure.PriorityQueue.BOJ1374;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
1. 풀이
 1) 강의 배열을 입력받은 후, 시작 시간이 빠른 순으로 정렬
 2) 시작 시간이 빠른 강의부터 PriorityQueue 에 추가
   - PriorityQueue: 강의를 종료 시간이 빠른 순으로 정렬하며 저장
   - 새로 추가되는 강의의 시작 시간 >= pq.remove() 에서 나온 강의의 가장 빠른 종료 시간
     => 강의실 추가 X
  - 새로 추가되는 강의의 시작 시간 < pq.remove() 에서 나온 강의의 가장 빠른 종료 시간
     => 강의실 + 1 추가

2. 자료구조
 - Lesson[]: 강의 배열, 강의 시작 시간 빠른 순으로 정렬
 - PriorityQueue<Lesson>: 진행 중인 강의 저장, 강의 종료 시간 빠른 순으로 정렬

3. 시간 복잡도
 - 강의 배열 입력 후, 정렬: O(n + n log n)		(n: 전체 강의 개수)
 - PriorityQueue 에 강의 추가 및 제거: 대충 2(log 1 + log 2 + ... log n)
   = 2 log(n!) = 2n log n	(n: 전체 강의 개수)
 => 총 시간 복잡도: O(n + 3n log n)
 => n 최대값 대입: 10^5 + 3 x 10^5 x 5 = 16 x 10 ^5 << 2억 (2초)
*/

class Lesson implements Comparable<Lesson> {
	private int start, end;

	public Lesson(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public int getStart() { return start; }
	public int getEnd() { return end; }

	// 강의 시작 빠른 순으로 정렬
	public int compareTo(Lesson lesson) {
		int startDiff = this.start - lesson.start;
		if (startDiff != 0)
			return startDiff;
		else
			return this.end - lesson.end;
	}
}

public class Main {
	static int n;					// 전체 강의 수
	static Lesson[] lessons;		// 입력 강의 배열, 시작 시간 빠른 순으로 정렬
	static PriorityQueue<Lesson> pq = new PriorityQueue<>((l1, l2) -> {
		int endDiff = l1.getEnd() - l2.getEnd();
		if (endDiff != 0)
			return endDiff;
		else
			return l1.getStart() - l2.getStart();
	});
	// 진행 중인 강의 저장, 종료 시간 빠른 순으로 정렬
	static int minNum = 0;			// 필요 최소 강의실 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		lessons = new Lesson[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lessons[i] = new Lesson(start, end);
		}

		Arrays.sort(lessons);

		pq.add(lessons[0]);		// 시작 시간이 가장 빠른 첫 강의 수업 시작
		minNum++;

		for (int i = 1; i < lessons.length; i++) {
			if (!pq.isEmpty()) {
				Lesson current = pq.peek();
				if (current.getEnd() <= lessons[i].getStart())
					pq.remove();		// 현재 강의 종료
				else
					minNum++;
				pq.add(lessons[i]);
			}
			else {		// 현재 진행중인 강의가 없는 경우 (현재 강의실 사용 중인 강의가 없는 경우)
				pq.add(lessons[i]);
			}
		}

		System.out.println(minNum);
	}
}

package Greedy.BOJ7983;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 1) 과제 배열 정렬
   - 과제 마감일 늦은 순으로 정렬
 2) 마감일 늦은 과제부터 각 과제의 (종료 일자, 시작 일자) 정하기
   - 종료 일자 정하기
     ① 기본적으로, 종료 일자 = 마감일 t
     ② 현재 과제의 종료 일자 >= 배열에서 이전에 확인한 과제(이후 마감일 과제)의 시작 일자인 경우
       => 종료 일자 = 배열에서 이전에 확인한 과제(이후 마감일 과제)의 시작 일자 - 1일 (하루 전)
   - 시작 일자 = 종료 일자 - 소요일 + 1일
     => 소요일이 정해져있으므로, 시작 일자는 종료 일자를 정하면 자동으로 고정됨

2. 자료구조
 - Homework[]: 각 과제의 (소요일 d, 마감일 t), (시작 일자, 종료 일자)

3. 시간 복잡도
 - 배열 정렬: O(n log_2 n)
   => n 최대값 대입: 10^6 x log_2 10^6 = (6 x 10^6) log_2 10 ~= 18 x 10^6
 - 과제 시작 일자, 종료 일자 지정: O(n)
   => n 최대값 대입: 10^6
 => 전체 시간 복잡도: (18 x 10^6) + 10^6 = 19 x 10^6 << 2억
*/

class Homework implements Comparable<Homework> {
	public int d, t;			// 과제 (소요일 d, 마감일 t)
	public int start, end;		// 과제 (시작 일자, 종료 일자)

	public Homework(int d, int t) {
		this.d = d;
		this.t = t;
	}

	public int compareTo(Homework hw) {
		// 과제 마감일 늦은 순(큰 순)으로 정렬
		return hw.t - this.t;
	}
}

public class Main {
	static int n;					// 과제 개수
	static Homework[] homeworks;	// 각 과제의 (소요일 d, 마감일 t), (시작 일자, 종료 일자)
	static int maxDay;				// 출력, 내일(1일)부터 연속으로 최대 놀 수 있는 일 수

	static void solution() {
		homeworks[0].end = homeworks[0].t;
		homeworks[0].start = homeworks[0].end - homeworks[0].d + 1;

		for (int i = 1; i < n; i++) {
			// ① 기본적으로, 종료 일자 = 마감일 t
			homeworks[i].end = homeworks[i].t;
			homeworks[i].start = homeworks[i].end - homeworks[i].d + 1;

			// ② 현재 과제의 종료 일자 >= 배열에서 이전에 확인한 과제(이후 마감일 과제)의 시작 일자인 경우
			// => 종료 일자 = 배열에서 이전에 확인한 과제(이후 마감일 과제)의 시작 일자 - 1일 (하루 전)
			if (homeworks[i].end >= homeworks[i-1].start) {
				homeworks[i].end = homeworks[i-1].start - 1;
				homeworks[i].start = homeworks[i].end - homeworks[i].d + 1;
			}
		}

		maxDay = homeworks[n-1].start - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		homeworks = new Homework[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			homeworks[i] = new Homework(d, t);
		}

		Arrays.sort(homeworks);

		solution();
		System.out.println(maxDay);
	}
}

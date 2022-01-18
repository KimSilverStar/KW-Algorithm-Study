package BruteForce.BOJ15686;
import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

/*
- 0: 빈 칸, 1: 집, 2: 치킨 집

1. 아이디어
 - 입력 행렬에서 집 좌표 리스트, 치킨 집 좌표 리스트 저장
 - 치킨 집 좌표 리스트에서 중복 없이, 순서 상관없이 m개 선택 (조합)
   => 백트래킹으로 선택
 - 가능한 모든 치킨 집 조합에 대해, 각 집들의 치킨거리 합 (도시의 치킨거리) 계산
   => 최소값 갱신해나감
 => 브루트 포스 + 백트래킹으로 모든 조합에 대해 거리 값 계산하여 최소값 갱신

2. 자료구조
 - ArrayList<Coord> 3개: 집 / 치킨 집 / 선택한 치킨 집 좌표 리스트
 - boolean[]: 치킨 집 방문(선택) 확인

3. 시간 복잡도
 - 최대 치킨 집 개수에서 중복없이, 순서 상관없이 m개 선택하는 조합
   => C(13, m)
   => m = 6 일때 최대 => C(13, 6) = 최대 1,716개 조합		[ C(n, k) = n! / k! x (n-k)! ]
 - 1개 치킨 집 조합(m개)에 대해, 2n개 집들을 확인
   => 2 x n x m
   => n, m 최대값 대입: 2 x 50 x 13 = 1,300
 => 총 시간 복잡도: 1,716 x 1,300 = 2,230,800 << 1억 (1초)
*/

class Coord {
	private int row;
	private int col;

	public Coord(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public int getRow() { return row; }
	public int getCol() { return col; }
}

public class Main {
	static int n;			// n x n 행렬
	static int m;			// 선택할 치킨 집 개수
	static int minCityDistance = Integer.MAX_VALUE;				// 출력 값

	static List<Coord> homeList = new ArrayList<>();			// 집 좌표 리스트
	static List<Coord> chickenList = new ArrayList<>();			// 치킨 집 좌표 리스트
	static List<Coord> selectedChickenList = new ArrayList<>();			// 선택한 m개 치킨 집 좌표 리스트
	static boolean[] checkChickenList;		// 치킨 집 선택 확인

	/* idx: 치킨 집 선택 시작 index */
	static void solution(int idx) {
		// m개 선택한 경우 => 거리 값 계산
		if (selectedChickenList.size() == m) {
			int cityDistance = 0;		// 도시의 치킨 거리 (각 집의 치킨 거리 합)

			for (int i = 0; i < homeList.size(); i++) {
				int minDistance = Integer.MAX_VALUE;		// 각 집의 최소 치킨 거리

				for (int j = 0; j < selectedChickenList.size(); j++) {
					minDistance = Math.min(
							minDistance,
							calcDistance(homeList.get(i), selectedChickenList.get(j))
					);
				}
				cityDistance += minDistance;
			}

			minCityDistance = Math.min(minCityDistance, cityDistance);
			return;
		}

		// m개 선택
		for (int i = idx; i < chickenList.size(); i++) {
			if (!checkChickenList[i]) {		// 아직 선택 안한 경우
				checkChickenList[i] = true;
				selectedChickenList.add(chickenList.get(i));
				solution(i + 1);

				// 재귀 호출 복귀 시점: 선택 확인 배열, 선택 리스트 복구
				checkChickenList[i] = false;
				selectedChickenList.remove(chickenList.get(i));
			}
		}
	}

	static int calcDistance(Coord c1, Coord c2) {
		return Math.abs(c1.getRow() - c2.getRow())
				+ Math.abs(c1.getCol() - c2.getCol());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1)				// 집
					homeList.add(new Coord(i, j));
				else if (input == 2)		// 치킨 집
					chickenList.add(new Coord(i, j));
			}
		}

		checkChickenList = new boolean[chickenList.size()];

		solution(0);		// Init Call

		System.out.println(minCityDistance);
	}
}

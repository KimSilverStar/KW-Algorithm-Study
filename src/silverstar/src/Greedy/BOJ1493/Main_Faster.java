package Greedy.BOJ1493;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 1) 그리디 알고리즘
   - 채울 수 있는 가장 큰 큐브부터 먼저 채움
     => length, width, height 중에서 가장 최소 값 minSize 를 찾음
     => log2 (minSize)
 2) 분할 정복
   - 큐브로 채우고 남은 영역을 분할하여 채움

2. 자료구조
 - int[] cubes: 큐브 종류 별 보유 개수
   ex) cubes[1]: 한변의 길이가 2^1 인 큐브의 개수

3. 시간 복잡도
 1) 그리디로 채울 수 있는 가장 큰 큐브 찾음
   - 매 반복에서 O(log n)
 2) 분할 정복
   - 매 재귀호출 3번
*/

public class Main_Faster {
	static int length, width, height;		// 박스 크기
	static int n;							// 큐브 종류 개수
	static int[] cubes;						// 큐브 종류 별 보유 개수
	static int minCount;					// 필요한 큐브 최소 개수
	static boolean flag;					// 박스를 채우지 못하는 경우

	/* 크기 l x w x h 영역을 채움 */
	static void solution(int l, int w, int h) {
		if (l == 0 || w == 0 || h == 0)
			return;

		int minSize = l;		// l, w, h 중에 최소 값
		if (minSize > w)
			minSize = w;
		if (minSize > h)
			minSize = h;

		flag = false;
		int idx = baseLog(2, minSize);
		int cubeLen = 0;
		for (int i = idx; i >= 0; i--) {
			if (cubes[i] == 0)
				continue;

			cubeLen = 1 << i;			// 2^i
			cubes[i]--;
			minCount++;

			flag = true;
			break;
		}

		if (!flag)
			return;

		// 분할 정복으로 남은 영역을 채움
		solution(cubeLen, w - cubeLen, cubeLen);
		solution(l - cubeLen, w, cubeLen);
		solution(l, w, h - cubeLen);
	}

	static int baseLog(int base, int x) {
		return (int)(Math.log(x) / Math.log(base));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		length = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		n = Integer.parseInt(br.readLine());
		cubes = new int[20];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			cubes[idx] = count;
		}

		solution(length, width, height);

		if (flag)
			System.out.println(minCount);
		else
			System.out.println(-1);
	}
}

package Greedy.BOJ1052;
import java.io.*;
import java.util.StringTokenizer;

/*
1. 풀이
 - 작은 물 양의 물병을 합쳐나감
 - 같은 물 양의 물병이 없어서 물병을 합칠 수 없는 경우, 새로운 1L 짜리 물병을 구입
   => 기존에 보유한 1L 짜리 물병 보다 큰 가장 작은 물양의 물병을 찾음
      ex) 1L 1병, 4L 1병, 8L 1병 보유한 경우,
          4 - 1 = 3병 구입

2. 자료구조
 - int[] bottles: 해당 물 양에 따른 물통 개수
   ex) waters[1]: 1L 짜리 물병 개수
   => n 최대값 10^7
   => 넉넉하게 [2 * n] 할당
   => 4 x (2 x 10^7) byte = 8 x 10 MB

3. 시간 복잡도
*/

public class Main {
	static int n;				// 처음 갖고 있는 물병 개수
	static int k;				// 한 번에 이동 가능한 물병 개수
	static int minCount;		// 새로 구입한 물병 개수

	static int[] bottles;
	static int bottleCount;		// 물이 들어있는 물병 개수
	static int maxWater;		// 물이 들어있는 물병 중에서 가장 많은 물양

	static void solution() {
		while (bottleCount > k) {
			boolean isSameExist = false;		// 같은 물 양의 물병이 존재하는지

			for (int i = 1; i <= maxWater; i++) {
				if (bottles[i] >= 2) {			// 같은 물 양의 물병이 2개 이상인 경우, 합침
					int num = bottles[i];		// 같은 물 양의 물병 개수
					bottles[i] = num % 2;
					bottles[i * 2] += (num / 2);

					bottleCount = (bottleCount - num) + (num % 2) + (num / 2);
					maxWater = Math.max(maxWater, i * 2);

					isSameExist = true;
					break;
				}
			}

			if (!isSameExist) {		// 같은 물 양의 물병이 없는 경우, 새로 구입
				// 1L 짜리 물병보다 큰 최소 물병을 찾음
				int water;
				for (water = 2; water <= maxWater; water++) {
					if (bottles[water] > 0)
						break;
				}

				bottles[1] += (water - 1);		// 1L 짜리 물병 새로 구입
				bottleCount += (water - 1);
				minCount += (water - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		if (n <= k) {
			System.out.println(0);
			return;
		}

		bottles = new int[n * 2];
		bottles[1] = n;			// 처음: 1L 짜리 물병 n개
		bottleCount = n;
		maxWater = 1;

		solution();
		System.out.println(minCount);
	}
}

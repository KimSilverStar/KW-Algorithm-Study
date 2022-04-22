package DP.BOJ9252;
import java.io.*;

/*
1. 아이디어
 1) DP 배열 정의: int[][] dp
   - dp[i][j]: str1[i]까지의 문자열, str2[j]까지의 문자열에서 LCS 길이
   - 출력, 최종 LCS 길이: dp[len1][len2]

 2) 규칙 및 점화식
   - 입력 문자열 str1, str2의 각 문자의 동일 여부 비교
   ① str1[i] 문자 != str2[j] 문자인 경우
     - 이전 윗 칸 or 왼쪽 칸 중, LCS 길이 최대값
     => dp[i][j] = max(dp[i-1][j], dp[i][j-1])
   ② str1[i] 문자 == str2[j] 문자인 경우
     - 추가된 동일 문자로 인해, LCS 길이 + 1 늘어남
     - 추가된 동일 문자를 제외한 두 문자열의 LCS 길이 + 1
     => dp[i][j] = dp[i-1][j-1] + 1

 ※ 최종 LCS 문자열 구하기
  - DP 배열(각 상태에서의 LCS 길이)을 채운 순서의 역순으로 추적
    => LCS 문자열이 역순으로 저장됨 (결과 값을 역순으로 출력)
  - 마지막 dp[len1][len2] 에서부터 시작
  ① 이전 윗 칸 or 왼쪽 칸의 DP 배열 값 중,
     현재 칸의 DP 배열 값과 같은 칸이 존재하는 경우
    - 해당 윗 칸 or 왼쪽 칸으로 이동
    => DP 배열을 채우는 과정에서, "추가된 동일 문자가 없는 경우"에 해당
  ② 이전 윗 칸 or 왼쪽 칸의 DP 배열 값 중,
     현재 칸의 DP 배열 값과 같은 칸이 존재하지 않는 경우
    - 현재 칸에 해당하는 문자를 추가하고, 왼쪽 대각선 윗 칸으로 이동
    => DP 배열을 채우는 과정에서, "동일 문자가 추가되어 LCS 길이가 + 1 늘어난 경우"에 해당


2. 자료구조
 - int[][] dp: DP 배열
   ① 자료형: DP 배열 원소 최대값 10^3 << 21억 이므로, int 가능
   ② 메모리: 4 x 10^3 x 10^3 byte = 4 x 10^6 byte = 4 MB


3. 시간 복잡도
 - DP 배열 채우기: O(len1 x len2)
   => 입력 문자열 길이 최대값 대입: 10^3 x 10^3 = 10^6 << 1억
*/

public class Main {
	static String str1, str2;
	static int maxLength;			// 출력, LCS 최대 길이
	static StringBuilder LCS = new StringBuilder();		// 출력, LCS 문자열
	static int[][] dp;

	static void solution() {
		// 1. DP 배열 채우기
		for (int i = 1; i <= str1.length(); i++) {
			for (int j = 1; j <= str2.length(); j++) {
				// str1[i] 문자 == str2[j] 문자인 경우
				if (str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				// str1[i] 문자 != str2[j] 문자인 경우
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}

		maxLength = dp[str1.length()][str2.length()];

		if (maxLength == 0)
			return;

		// 2. LCS 문자열 구하기
		int i = str1.length();
		int j = str2.length();

		while (i != 0 && j != 0) {
			// 이전 윗 칸 or 왼쪽 칸과 LCS 길이 같은 경우
			if (dp[i][j] == dp[i-1][j])
				i--;
			else if (dp[i][j] == dp[i][j-1])
				j--;
			// 이전 윗 칸, 왼쪽 칸과 LCS 길이 다른 경우 (LCS 길이 감소한 경우)
			else {
				LCS.append(str1.charAt(i-1));
				i--;
				j--;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		str1 = br.readLine();
		str2 = br.readLine();

		// [0]행, [0]열은 패딩, [1][1] ~ [len1][len2] 사용
		dp = new int[str1.length() + 1][str2.length() + 1];
		solution();

		System.out.println(maxLength);
		if (maxLength > 0)
			System.out.println(LCS.reverse());
	}
}

package String.BOJ9935;
import java.io.*;

/*
1. 풀이
 1) 입력 문자열을 한 문자씩 읽어가면서, StringBuilder 에 저장
 2) StringBuilder 의 길이 >= 폭발 문자열 길이인 경우
   - StringBuilder 에 폭발 문자열과 동일한 문자열이 존재하는지 확인

2. 자료구조
 - StringBuilder: 입력 문자열을 한 문자씩 읽으면서, StringBuilder 에 추가 및 삭제

3. 시간 복잡도
 - 입력 문자열 최대 길이: 10^6 (100만)
   => O(n^2) 미만으로 풀어야 함 (n, n long n 까지 가능)
*/

public class Main_StringBuilder {
	static String input, bomb;			// 입력 문자열, 폭발 문자열
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);

		input = br.readLine();
		bomb = br.readLine();

		for (int i = 0; i < input.length(); i++) {
			sb.append(input.charAt(i));

			if (sb.length() >= bomb.length()) {
				boolean isSame = true;
				int idx = sb.length() - bomb.length();

				for (int j = idx; j < sb.length(); j++) {
					if (sb.charAt(j) != bomb.charAt(j - idx)) {
						isSame = false;
						break;
					}
				}

				if (isSame)
					sb.delete(idx, idx + bomb.length());
			}
		}

		if (sb.length() == 0)
			System.out.println("FRULA");
		else
			System.out.println(sb.toString());
	}
}

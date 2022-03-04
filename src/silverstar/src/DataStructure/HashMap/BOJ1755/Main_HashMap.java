package DataStructure.HashMap.BOJ1755;
import java.io.*;
import java.util.*;

/*
1. 아이디어
 1) 0 "zero" ~ 9 "nine" 을 String[] 에 저장
 2) for 문으로 m ~ n 사이의 정수들을 한 숫자씩 읽어서,
    영어로 읽은 값을 ArrayList, HashMap 에 저장
   - HashMap: 정수를 영어로 읽은 문자열이 Key, 정수 값이 Value
 3) ArrayList 를 정렬
 4) 정렬된 ArrayList 의 요소를 HashMap 의 Key 로 하여,
    HashMap 에서 정수 값들을 꺼내 차례로 출력

2. 자료구조
 - List<String>, ArrayList<String>: 정수를 한 숫자씩 영어로 읽은 문자열을 저장
 - Map<String, Integer>, HashMap<String, Integer>
   : 정수를 영어로 읽은 문자열이 Key, 정수 값이 Value

3. 시간 복잡도
 1) 각 정수를 영어로 읽은 문자열을 저장: O(n)
 2) 정렬: O(n log n)
 3) 정렬된 문자열들을 정수에 맵핑하여 출력: O(n)
 - 총 시간 복잡도: O(2n + n log n)		(n: 정수 개수)
   => n 최대값 대략 100 대입: 200 + (100 x 2) = 400 << 2억
*/

public class Main_HashMap {
	static int m, n;				// m 이상 n 이하의 정수
	static String[] numberStrArr;
	static List<String> list = new ArrayList<>();
	static Map<String, Integer> map = new HashMap<>();
	static StringBuilder sb = new StringBuilder();

	static void solution() {
		// 1) 정수 값을 영어 문자열로 입력하여 List, Map 에 저장
		for (int i = m; i <= n; i++) {
			String numStr = String.valueOf(i);					// "80" 형태
			StringBuilder strByDigit = new StringBuilder();		// "eight zero" 형태

			for (int j = 0; j < numStr.length(); j++) {
				int digit = Character.getNumericValue(numStr.charAt(j));
				strByDigit.append(numberStrArr[digit])
						.append(" ");
			}

			list.add(strByDigit.toString());
			map.put(strByDigit.toString(), i);
		}

		// 2) List 를 사전 순으로 정렬
		Collections.sort(list);

		// 3) Map 의 각 정수 값 출력
		int count = 1;
		for (String s : list) {
			int number = map.get(s);
			sb.append(number).append(" ");

			if (count % 10 == 0)
				sb.append("\n");

			count++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		numberStrArr = new String[] {
				"zero", "one", "two", "three", "four",
				"five", "six", "seven", "eight", "nine"
		};

		solution();
		System.out.println(sb);
	}
}

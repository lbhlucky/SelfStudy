package TwoPointer;

/*
* <알고리즘의 핵심>
* 꼭 봐야하는 영역만 보기
*
* 투 포인터(Two Pointer)
* 화살표 두개에 의미를 부여해서 탐색 범위를 압축하는 방법
* 1. 1차원 배열 위에 2개의 포인터를 만드는 경우
*   1) "2개의 포인터"가 "모두 왼쪽"에서 "시작"해서 "같은 방향"으로 이동
*   2) "2개의 포인터"가 "양 끝"에서 "서로를 향해" 이동
* 2. 관찰을 통해서 문제에 등장하는 "변수 2개의 값"을 두 포인터로 표현하는 경우
*
* 투포인터 꿀팀
* <키워드>
* - 1차원 배열에서의 "연속 부분 수열" or "순서를 지키며 차례대로"
* - "곱의 최소" ex) A*B : a가 커지면 b는 더 작아져야한다.
*
* ==> 이런 단어가 등장하면 twoPointer 접근을 시도해 볼 가치가 있다.
*/

import java.io.*;
import java.util.*;

public class PartialSum {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st == null || st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // 부분 합
    // 난이도 3
    // 10 <= N <= 100,000
    // 0 < S <= 10^8
    // 1 <= 각 원소 <= 10,000


    // Q. 이 수열에서 연속된 수들의 부분합 중에 그합이 S 이상이 되는 것 중,
    //    가장 짧은 것의 길이를 구하는 프로그램

    // 문제 파악하기 - 정답의 최대치
    // N = 100,000 S = 10^8
    // 정답이 N 이하 이므로 Integer 범위
    // 모든 원소의 총합도 10^9 이므로 Integer 범위   10^5 * 10^4 : N의 최대범위 * 각 원소의 최대값

    // 접근 - 가장 쉬운 방법 O(N^2)
    // 1. 왼쪽 시작 L 결정 -> O(N)
    // 2. 오른쪽 끝 R을 L부터 시작해서 이동 -> O(N)
    // ==> O(N^2)

    // 접근 - 투포인터 방법 O(N)
    // 1. 왼쪽 시작 L의 이동 횟수 N번
    // 2. 오른쪽 끝 R을 이전의 R부터 시작해서 이동
    // 3. L, R이 각자 최대 N 번 이동하니까 => O(N)

    // 원소의 개수 n, 기준 합 S
    static int n, S;
    // 입력 받은  정수를 담아 배열로 만들 변수
    static int[] a;

    static void input() {
        n = scan.nextInt();
        S = scan.nextInt();
        a = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            a[i] = scan.nextInt();
        }
    }

    static void pro(){
        // ans : sum값 중 가장 짧은 값
        int R = 0, sum = 0, ans = n + 1;
        for(int L = 1 ; L <= n; L++){
            // L - 1 을 구간에서 제외하기
            sum -= a[L-1];

            // R 을 옮길 수 있을 때 까지 옮기기
            while(R + 1 <= n && sum < S){
                R++;
                sum += a[R];
            }

            // [L ... R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기
            if(sum >= S){
                ans = Math.min(ans, R-L+1);
            }

        }
        // ans 값을 보고 불가능 판단하기
        if(ans == n+1){
            ans = 0;
        }
        System.out.println(ans);
    }


    public static void main(String[] args) {
        input();
        pro();
    }
}

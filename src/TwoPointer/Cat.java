package TwoPointer;

import java.io.*;
import java.util.StringTokenizer;

public class Cat {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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

    // 고냥이
    // 난이도 3
    // 1 <= 알파벳 종류, N <= 26
    // 1 <= 문자열 길이 <= 100,000
    // 각 문자는 알파벳 소문자로 이루어짐


    // Q. 최대 N개의 종류의 알파벳을 가진 연속된 문자열 밖에 인식하지 못한다.
    //    인식할 수 있는 최대 문자열의 길이는 얼마인지가 궁금해졌다.

    // 문제 파악하기 - 정답의 최대치
    // N = 26이라면 문자열 전체를 인식하므로,
    // 최대 길이인 10만이 정답 => Integer 범위

    // 접근 - 바로 투 포인터 접근으로!
    // R : 인식하고 싶은 구간의 오른쪽 끝
    // L : 인식 가능한 가장 왼쪽 위치
    // cnt 배열 : 각 문자마다 몇번 등장하는 지 기록
    // kind : [L, R] 사이의 알파벳 종류
    // -> cnt 배열에서 0이 아닌 것의 개수

    // 시간, 공간 복잡도 계산하기
    // 1. R을 하나씩 이동시키면서 L을 조절하기 => O(N)
    // 2. kind를 O(26)에 계산하거나 O(1)에 계산 가능
    // 3. 총 시간 복잡도 : O(N)

    static int N, kind;
    static String A;
    static int[] cnt;

    static void input() {
        N = scan.nextInt();
        A = scan.nextLine();
        cnt = new int[26];
    }

// Sol01) -------------------------------------------------------------------------------------------------------------
//    static void add(char x) {  // x 라는 알파벳 추가
//        cnt[x -'a']++;
//    }
//
//    static void erase(char x) {  // x 라는 알파벳 제거
//        cnt[x -'a']--;
//    }
//
//    static void pro() {
//        int len = A.length(), ans = 0;
//        for (int R = 0, L = 0; R < len; R++) {
//            // R 번째 문자를 오른쪽에 추가
//            add(A.charAt(R));
//
//            // 불가능하면, 가능할 때까지 L을 이동
//            while(true){
//                kind = 0;
//                for(int i = 0 ; i < 26 ; i++){
//                    if(cnt[i] != 0 ){
//                        kind++;
//                    }
//                }
//                if(kind <= N){
//                    break;
//                }
//                erase(A.charAt(L));
//                L++;
//            }
//            // 정답 갱신
//            ans = Math.max(ans, R-L+1);
//        }
//        System.out.println(ans);
//    }

// Sol02) -------------------------------------------------------------------------------------------------------------
    static void add(char x) {  // x 라는 알파벳 추가
        cnt[x -'a']++;
        if(cnt[x-'a'] == 1){
            kind++;
        }
    }

    static void erase(char x) {  // x 라는 알파벳 제거
        cnt[x -'a']--;
        if(cnt[x-'a'] == 0){
            kind--;
        }
    }

    static void pro() {
        int len = A.length(), ans = 0;
        for (int R = 0, L = 0; R < len; R++) {
            // R 번째 문자를 오른쪽에 추가
            add(A.charAt(R));

            // 불가능하면, 가능할 때까지 L을 이동
            while(kind > N){
                erase(A.charAt(L++));
            }

            // 정답 갱신
            ans = Math.max(ans, R-L+1);
        }
        System.out.println(ans);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
}

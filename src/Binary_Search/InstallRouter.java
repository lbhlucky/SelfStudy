package Binary_Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InstallRouter {
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

    // 공유기 설치
    // 난이도 3
    // 2 <= N <= 200,000
    // 2 <= C <= N
    // 1 <= 좌표 x_i <= 10억
    // 집의 개수 N
    // 공유기 개수 C
    // Q. C개의 공유기를 몇몇 집에 설치 
    //    인접한 공유기 사이의 거리 최대화하기

    // 문제 파악하기 - 정답의 최대치
    // 집의 좌표 거리 1이상 10억이하
    // 제일 멀리 설치해보면 정답은 10억 이하 => Integer 가능!

    // 문제 파악하기 - 키워드 체크 하기
    // C개의 공유기를 N개의 집에 적당히 설치해서,
    // 가장 인접한 "두 공유기 사이의 거리를 최대"로 하는 프로그램을 작성하시오.

    // 접근 - 매개 변수 만들어 보기
    // 원래 문제 : C개의 공유기를 설치했을 때, 인접거리(D)는 얼마인가?
    // 뒤집은 문제 : 어떤 거리(D)만큼은 거리를 둘 때, 공유기 C개를 설치할 수 있는가? Yes/No

    // 어떤 거리(𝐷) 만큼은 거리를 둘 때, 왼쪽 집부터 되는 대로 전부 설치해보기!

    // 시간, 공간 복잡도 계산하기
    // 1. 주어진 집들을 정렬하기 => O(N log N)
    // 2. D를 정해서 결정 문제 한 번 풀기 => O(N)
    // 3. 정답의 범위를 이분 탐색하면서 풀기 => O(log X)번 반복
    // 4. 총 시간 복잡도 : O(N log N + N log X)

    static int N, C;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        C = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static boolean determination(int D) {
        // D 만큼의 거리 차이를 둔다면 C 개 만큼의 공유기를 설치할 수 있는가?

        // 제일 왼쪽 집부터 가능한 많이 설치해보자!
        // D 만큼의 거리를 두면서 최대로 설치한 개수와 C 를 비교하자.
        int cnt = 1, last = A[1];
        for(int i = 2 ; i <= N ; i++){
            // 이번에 A[i]에 설치가 가능한가?        
            if(A[i] - last >= D){
                cnt++;
                last = A[i];
            }
        }
        return cnt >= C;
    }

    static void pro() {
        // determination을 빠르게 하기 위해서 정렬해주자.
        Arrays.sort(A, 1 , N+1);

        int L = 1, R = 1000000000, ans = 0;
        // [L ... R] 범위 안에 정답이 존재한다!
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자!
        while(L <= R){
            int mid = (L+R)/2;
            if(determination(mid)){
                ans = mid;
                L = mid+1;
            }else{
                R = mid-1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }  
}

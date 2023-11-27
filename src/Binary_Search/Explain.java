package Binary_Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Explain {
/*
 (수열에서)탐색이란?
 - 수열과 탐색 대상 x가 주어졌을 때,
 - x 가존재하는 지 
 - x[이하, 미만, 이상, 초과]의 원소가 몇개인지
 - x 랑 가장 가까운 원소는 무엇인지
 ==> O(N)

 - 정렬된 수열과 탐색대상 x가 주어졌을 때,
 - x 가존재하는 지 
 - x[이하, 미만, 이상, 초과]의 원소가 몇개인지
 - x 랑 가장 가까운 원소는 무엇인지
 
 * 이분 탐색(Binary Search)
 무엇 : 정렬이 보장되는 배열에서 기준 x를 가지고 범위를 "이분"하면서 탐색하는 방법
 - x 가존재하는 지 
 - x[이하, 미만, 이상, 초과]의 원소가 몇개인지
 - x 랑 가장 가까운 원소는 무엇인지
 ==> O(log N)

 이분 탐색
 - 오름차순 정렬이 된 배열의 특성
    1. 임의의 M번 인덱스에 있는 A[M]이 x보다 크다면, A[M...N]은 전부 x보다 크다.
    2. 임의의 M번 인덱스에 있는 A[M]이 x보다 작다면, A[1...M]은 전부 x보다 작다.
    ※주의※ 오름차순 정렬이기 떄문에 생기는 성질! 정렬이 아니라면 불가능하다!!

 L : 탐색할 가치가 있는 범위의 왼쪽 끝 인덱스
 R : 탐색할 가치가 있는 범위의 오른쪽 끝 인덱스
 Result : 탐색한 x의 위치
 탐색 목표 : x이하의 원소 중에 가장 오른쪽에 있는 원소

 자주하는 실수
 1위 : 입력된 배열에 바로 이분 탐색을 하는데, 정렬을 하지 않는 경우!
 2위 : L, R, M, Result 변수의 정의를 헷갈려서 부등호 등을 잘못 쓰는 경우!
 3위 : L, R 범위를 잘못 설정하거나 Result의 초기값을 잘못 설정하는 경우!
 */    
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

    // 먹을 것인가 먹힐 것인가
    // 난이도 2
    // N <= 20,000
    // M <= 20,000

    // 문제 파악하기 - 정답의 최대치
    // 모든 쌍이 정답인 경우 ! => N * M = 4 억 ==> Integer!

    // 접근 - 가장 쉬운 방법 O(NM)
    // => B에서 A[i] 미만 원소들 찾기

    // 접근 탐색을 빨리 하기! O((N+M) log M)
    // <생각의 흐름>
    // B에서 A[i] 이하 원소들을 빨리 찾기 -> 이걸 빨리 해주는 게 뭐가 있을 까? -> "이분 탐색"을 쓰자
    // -> 이를 위해 전제 조건(정렬된 상태) 만족 -> B 가 정렬되어있으니까 원하는 원소를 빠르게(log M) 탐색이 가능하네
    // -> A의 원소마다 이분 탐색 => O(N log M)

    // 시간, 공간 복잡도 계산하기
    // 1. B 배열 정렬 한 번 = > O(M log M)
    // 2. 모든 A의 원소마다, B 배열에 대해 이분 탐색 필요
    //   => O(N log M)
    // 3. 총 시간 복잡도 : O((N+M) log M)

    static int N, M;
    static int[] A, B;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1];
        B = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        for (int i = 1; i <= M; i++) {
            B[i] = scan.nextInt();
        }
    }

    static int lower_bound(int[] A, int L, int R, int X) {
        // A[L...R] 에서 X 미만의 수(X 보다 작은 수) 중 제일 오른쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 L - 1 을 return 한다
        int result = L-1;
        while (L <= R) {
            int mid = (L+R)/2;
            if(A[mid] < X){
               result = mid;
               L = mid+1;  // X보다 작은수중 제일 오른쪽 인덱스
            }else if(A[mid] >= X){
               R = mid-1;
            }
        }
        return result;
    }

    static void pro() {
        // B 배열에 대해 이분탐색을 할 예정이니까, 정렬을 해주자!
        // TODO
        Arrays.sort(B, 1, M+1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            // A[i] 를 선택했을 때, B 에서는 A[i]보다 작은 게 몇 개나 있는 지 count하기
            // ans += /* TODO */;
            ans += lower_bound(B, 1, M, A[i]);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int TT;
        TT = scan.nextInt();
        for (int tt = 1; tt <= TT; tt++) {
            input();
            pro();
        }
    }    
}

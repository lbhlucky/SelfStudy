package TwoPointer;

import java.io.*;
import java.util.StringTokenizer;

public class ListOfUniqueNumbers {
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

    // List Of Unique Numbers
    // 난이도 4
    // 1 <= N <= 100,000
    // 1 <= 각 원소 <= 100,000

    // 원소의 개수 N

     // Q. 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수

     // keyword : 연속한 1개 이상의 수를 뽑았을 때

    // 문제 파악하기 - 정답의 최대치
    // N = 100,000
    // 모든 연속 구간이 모두 정답에 카운트 된다!
    // 그러한 개수 = N + (N-1) + ... + 2 + 1
    // 약 50억 => long타입

    // 접근 - 가장 쉬운 방법 O(N^3)
    // 1. 왼쪽 시작 L 결정 => O(N)
    // 2. 오른쪽 끝을 R 을 L부터 시작해서 이동 => O(N)
    // 3. R을 이동해서 추가된 원소가 [L, R-1] 안에 있는지 확인 => O(N)
    // 4. 총 시간 복잡도 O(N^3)

    // 접근 - 개선된 방법 O(N^2)
    // 1. 왼쪽 시작 L 결정 => O(N)
    // 2. 오른쪽 끝을 R 을 L부터 시작해서 이동 => O(N)
    // 3. R을 이동해서 추가된 원소가 [L, R-1] 안에 있는지 확인
    //    숫자마다 [L, R]안에 몇 개나 있는 지를 직접 세자 => O(1)
    // 4. 총 시간 복잡도 O(N^2)

    // 접근 - 투 포인터 방법 O(N)
    // 1. 왼쪽 시작 L 결정 => O(N)
    // 2. 오른쪽 끝을 R을 이전의 R부터 시작해서 이동
    // 3. R을 이동해서 추가된 원소가 [L, R-1]안에 있는지 확인 => O(1)
    // 4. 총 시간 복잡도 O(N)

    static int N;
    static int[] A, cnt;
    static void input(){
        N = scan.nextInt();
        A = new int[N+1];
        for(int i = 1 ; i <= N ; i++){
            A[i] = scan.nextInt();
        }
        // cnt 배열에 i번지에 써저있는 숫자는 i라는 숫자가 몇번 등장햇는지 기록
        // i = 1부터 10만까지 등장하니까
        cnt = new int[100000+1];
    }
    static void pro() {
        long ans = 0;

        for (int L=1, R=0; L<=N; L++){  // L 마다 R 을 최대한 옮겨 줄 계획이다.
            // R 을 옮길 수 있는 만큼 옮긴다.
            while(R + 1 <= N && cnt[A[R+1]] == 0){
                R++;
                cnt[A[R]]++;
            }

            // 정답을 갱신한다
            ans += R - L + 1;

            // L 을 옮겨주면서 A[L] 의 개수를 감소시킨다.
            cnt[A[L]]--;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}

package Binary_Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSolution {
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

    // 두 용액
    // 난이도 3
    // 2 <= N <= 100,000
    // -10억 <= 원소 <= 10억
    
    // Q. 서로 다른 두 용액을 더해서 합이 최대한 0에 가깝게 만들기
    
    // 문제 파악하기 - 정답의 최대치
    // 두 수의 합으로 가능한 범위 : -20억 ~ 20억
    // Integer 범위 : -21억 ~ 21억
    // => Integer 가능

    // 접근 - 가장 쉬운 방법(N^2)^2
    // -> 두 용액을 선택해보기!, 전부 다!

    // 접근 - 빠른 방법 O(N log N)
    // 해야하는 일:
    // 1. 왼쪽 용액(A[left])을 골랐을 때, 오른쪽 용액은?
    // 2. A[left] 와 더해서 0에 가장 가까우려면?
    // 3. -A[left]와 가까울수록 좋습니다!
    // 즉, A[left]를 정했을 떄, -A[left]랑 가장 가까운 걸 빨리 찾자!
    // 어디서? A[(A[left]+1)...N]에서!
    // 정렬의 특성 - 각 원소마다, 가장 가까운 원소는 자신의 양 옆 중에 있다.

    // 정렬해보기! O(N log N)
    // 두가지 이득을 취할 수 있다.
    // 1. 이분 탐색 사용 가능!
    // 2. 가장 가까운 원소를 빠르게 찾기 가능!

    // Result : A[left+1 ... N]에서 X = -A[left]이상의 원소 중 가장 왼쪽 위치
    // 만약 그런게 없다면 N+1

    // A[Result-1]와 A[Result] 중에 X랑 가장 가까운 원소가 있다.
    // 대신 Result-1 과 Result 중에 left+1 이상 N 이하 인것만 가능한 원소이다.

    // 시간, 공간 복잡도 계산하기
    // 1. 배열 정렬 한 번 => O(N log N)
    // 2. 모든 원소마다 left로 정하고, -A[left]를 이분 탐색하기
    //    => O(N log N)
    // 3. 총 시간 복잡도 : O(N log N)

    static int N;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static int lower_bound(int[] A, int L, int R, int X) {
        // A[L...R] 에서 X 이상의 수 중 제일 왼쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 R + 1 을 return 한다
        int result = R + 1;
        while(L <= R){
            int mid = (L+R)/2;
            if(A[mid] >= X){
                result = mid;
                R = mid -1;
            } else {
                L = mid +1;
            }
        }
        return result;
    }

    static void pro() {
        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        Arrays.sort(A, 1, N + 1);

        // 0에 가장 가까운 합이 몇인지
        int best_sum = Integer.MAX_VALUE;
        // 그때 왼쪽 용액의 값 v1
        //      오른쪽 용액의 값 v2
        int v1 = 0, v2 = 0;
        for (int left = 1; left <= N - 1; left++) {
            // A[left] 용액을 쓸 것이다. 고로 -A[left] 와 가장 가까운 용액을 자신의 오른쪽 구간에서 찾자.
            int result = lower_bound(A, left+1, N, -A[left]);

            // A[result - 1] 와 A[result] 중에 A[left] 와 섞었을 때의 정보를 정답에 갱신시킨다.
            if (left + 1 <= result-1 && result -1 <= N && Math.abs(A[result-1]+A[left]) < best_sum){
                best_sum = Math.abs(A[left]+A[result - 1]);
                v1 = A[left];
                v2 = A[result-1];
            }

            if (left + 1 <= result && result <= N && Math.abs(A[result]+A[left]) < best_sum){
                best_sum = Math.abs(A[left]+A[result]);
                v1 = A[left];
                v2 = A[result];
            }            
        
        }
        sb.append(v1).append(' ').append(v2);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();    // 입력 받기
        pro();      // 계산 후 출력
    }   
}

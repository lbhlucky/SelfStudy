/*
 * 매개 변수 탐색(Parametric Search)
 배열이 0과 1만 존재하며, 오름차순인건 보장되만, 전체 배열은 모른다.
 특정 인덱스의 값을 O(T)에 계산 가능할 때, 여기서 0과 1의 경계를 찾아야한다면?
 
 예) Up-Down 게임!
 -    A 가 1 ~ 1000 사이의 어떤 자연수를 선택
 -    B 는 A 한테 “생각한 숫자가 X 이상이야?” 라는 질문 가능 
 -    A는 맞으면 1, 아니면 0 이라고 대답
 -    최소 횟수로 질문하려면? 

 <핵심>
 1.   정답을 “매개 변수(Parameter)”로 만들고 Yes/No 문제(결정 문제)로 바꿔 보기
 2.   모든 값에 대해서 Yes/No 를 채웠다고 생각했을 때, 정렬된 상태인가?
 3.   Yes/No 결정하는 문제를 풀기!
 문제를 거꾸로 푸는 것이기 때문에 통찰력을 요구합니다.
 최근 코테에 굉장히 빈도 높게 나오기 때문에 중요하며, 훈련이 많이 필요한 알고리즘입니다.

 <자주 하는 실수>
 1위 : 매개 변수에 대한 결정이 Nooooooooo Yesssssssss 꼴이 아닌데 이분 탐색 하는 경우! 
 2위 : L, R, M, Result 변수의 정의를 헷갈려서 부등호 등을 잘못 쓰는 경우!
 3위 : L, R 범위를 잘못 설정하거나 Result의 초기값을 잘못 설정하는 경우!

 <꿀팁 - 키워드>
 ~~의 최댓값을 구하시오 
 ~~의 최솟값을 구하시오
 => Parametric Search 접근을 시도해볼 가치가 있다!
 */

package Binary_Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutTree {
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

    // 나무 자르기
    // 난이도 2
    // 1 <= N <= 100만
    // 1 <= M <= 20억
    // 0 <= 각 나무 높이 <= 10억
    // 나무 개수 N,  필요한 나무의 길이 M, 나무를 자를 특정 높이 H

    // 문제 파악하기 - 정답의 최대치
    // 나무 개수 N = 100만
    // 필요한 나무의 길이 M = 20억
    // 1. 정답의 범위 : 0 ~ 10억
    // 2. 잘린 나무의 길이의 합 <= 나무 높이의 총합 = 100만 * 10억 = 10^15
    // 즉, 계산 과정 중의 변수 타입은 long

    // 문제 파악하기 - 키워트 체크하기
    // 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에
    // "설정할 수 있는 높이의 최댓값"을 구하는 프로그램을 작성하시오.

    // 접근 - 매개 변수 만들어 보기
    // 원래 문제 : 원하는 길이 M 만큼을 얻을 수 있는 최대 높이(H_ans)는 얼마인가?
    // 뒤집은 문제 : 어떤 높이(H)로 잘랐을 때, 원하는 길이 M 만큼 얻을 수 있는가? Yes/No
    // 시간 복잡도 : O(N)

    // <핵심>
    // 1. 정답을 “매개 변수(Parameter)”로 만들고 Yes/No 문제(결정 문제)로 바꿔 보기
    // 2.   모든 값에 대해서 Yes/No 를 채웠다고 생각했을 때, 정렬된 상태인가?
    // 3.   Yes/No 결정하는 문제를 풀기!

    // 시간, 공간 복잡도 계산하기
    // 1. H를 정해서 결정 문제 한 번 풀기 => O(N)
    // 2. 정답의 범위를 이분 탐색하면서 풀기 => O(log X)번 반복
    // 3. 총 시간 복잡도 : O(N log X)

    static int N, M;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static boolean determination(int H) {
        // H 높이로 나무들을 잘랐을 때, M 만큼을 얻을 수 있으면 true, 없으면 false를 return하는 함수
        long sum = 0;
        for(int i = 1 ; i <= N ; i++){
            if(A[i] > H){
                sum += A[i] - H;
            }
        }
        return sum >= M;
    }

    static void pro() {
        long L = 0, R = 2000000000, ans = 0;
        // [L ... R] 범위 안에 정답이 존재한다!
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자! 
        while(L <= R){
            long mid = (L+R)/2;
            if(determination((int)mid)){
                ans = mid;
                L = mid+1;
            }else{
                R = mid -1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
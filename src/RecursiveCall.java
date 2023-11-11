import java.util.ArrayList;

public class RecursiveCall {
    public int Factorial(int n){
        if(n > 1){
            return n*this.Factorial(n-1);
        }else{
            return 1;
        }
    }

    public int factorialFunc(int n){
        if(n <= 1 ){
            return 1;
        }else{
            return n * this.factorialFunc(n-1);
        }
    }

    // 숫자가 들어있는 배열이 주어졌을 때, 배열의 합을 리턴하는 코드를 재귀용법을 사용해 작성
    public int arrSum(ArrayList<Integer> data){
        if(data.size() <= 0){
            return 0;
        }else{
            return data.get(0) + this.arrSum(new ArrayList<>(data.subList(1, data.size())));
        }

    }

    // 정수 4를 1, 2, 3의 조합으로 나타내는 방법은 다음과 같이 7가지가 있음
    // 1. 1 + 1 + 1 + 1
    // 2. 1 + 1 + 2
    // 3. 1 + 2 + 1
    // 4. 2 + 1 + 1
    // 5. 2 + 2
    // 6. 1 + 3
    // 7. 3 + 1
    // Q. 정수 n 이 입력으로 주어졌을 때, n을 1,2,3의 합으로 나타낼 수 있는 방법의 수를 구하시오
    // 힌트)
    // 정수 n을 만들 수 있는 경우의 수를 리턴하는 함수를 f(n) 이라고 하면,
    // f(n)은 f(n-1) + f(n-2) + f(n-3) 과 동일 하다는 패턴 찾기
    public int func(int data){
        if(data == 1){
            return 1;
        }else if(data == 2){
            return 2;
        }else if(data == 3){
            return 4;
        }
        return this.func(data - 1)+this.func(data-2)+func(data-3);
    }

    public static void main(String[] args) {
        RecursiveCall recall = new RecursiveCall();
        System.out.println("Factorial() : "+recall.Factorial(5 ));

        System.out.println("factorialFunc() : "+recall.factorialFunc(5));
        System.out.println("------------------------------------------------------");
        /*
        * ArrayList의 subList 메서드
        *    public List<E> subList(int fromIndex, int toIndex)
        * - fromindex : 서브셋이 시작할 인덱스
        * - toindex : 서브셋의 마지막 인덱스
        * */
        ArrayList<Integer> testData = new ArrayList<>();
        for(int data = 0 ; data < 10 ; data++){
            testData.add(data);
        }
        System.out.println(testData);
        System.out.println(testData.subList(1, testData.size()-1));
        System.out.println("------------------------------------------------------");

        System.out.println(recall.arrSum(testData));
        System.out.println("------------------------------------------------------");
        System.out.println(recall.func(6));
    }
}

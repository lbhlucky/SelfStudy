import java.util.ArrayList;
import java.util.Arrays;

/*
* 탐욕 알고리즘(Greedy Algorithm)
* - 최적의 해에 가까운 값을 구하기 위해 사용됨
* - 여러 경우 중 하나를 결정해야할 때마다
*   매순간 최적이라고 생각되는 경우를 선택하는 방식으로 진행해서, 최종적인 값을 구하는 방식
* */
public class Greedy {
    // 탐욕알고리즘
    // 문제1 : 동전 문제
    // Q. 지불해야 하는 값이 4720원 일 떄, 1원, 50원, 100원, 500원 동전으로 동전의 수가 가장 적게 지불하시오.
    //    - 가장 큰 동전부터 최대한 지불해야 하는 값을 채우는 방식으로 구현 가능
    //    - 탐욕 알고리즘으로 매순간 최적이라고 생각되는 경우를 선택하면 됨
    public void coinFunc(Integer price, ArrayList<Integer> coinList){
        Integer totalCoinCount = 0;
        Integer coinNum = 0;
        ArrayList<Integer> details = new ArrayList<Integer>();

        for(int i = 0 ; i < coinList.size(); i++){
            coinNum = price / coinList.get(i);
            totalCoinCount += coinNum;
            price -= coinNum * coinList.get(i);
            details.add(coinNum);
            System.out.println(coinList.get(i)+"원 : " + coinNum + "개");
        }
        System.out.println("총 동전 갯수 : " +totalCoinCount);
    }

    // 문제2 부분 배낭 문제(Fractional Knapsack Problem)
    // Q. 무게 제한이 k인 배낭에 최대 가치를 가지도록 물건을 넣는 문제
    //    - 각 물건은 무게(w) 와 가치(v)로 표현될 수 있음


    public static void main(String[] args) {
        Greedy greedy = new Greedy();
        // 문제 1의 동전 리스트 만들기
        ArrayList<Integer> coninList = new ArrayList<Integer>(Arrays.asList(500,100,50,1));
        greedy.coinFunc(4720, coninList);

        System.out.println("---------------------------------------------------------------------");


        // 정렬 기준 정의하기
        // 1. 배열에 대한 정렬 : Arrays.sort(배열)
        Integer[] iArray = new Integer[]{1, 10, 4, 3, 2};
        Arrays.sort(iArray);
        Arrays.toString(iArray);


        System.out.println("---------------------------------------------------------------------");

        // 2차원 배열로 문제 2의 물건 리스트 만들기
        Integer[][] itemList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5}};
        System.out.println("item 갯수 : "+itemList.length);


    }
}

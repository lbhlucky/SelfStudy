package Sort_Basic;
/*
* 정렬
* - 어떤 데이터들이 주어졌을 떄 이를 정해준 순서대로 나열하는 것
* - 정렬은 프로그램 작성시 빈번하게 필요로함
* - 다양한 알고리즘이 고안되었으며, 알고리즘 학습의 필수!
*   다양한 정렬 알고리즘 이해를 통해, 동일한 문제에 대해 다양한 알고리즘이 고안될 수 있음을 이해하고,
*   각 알고리즘 간 성능 비교를 통해, 알고리즘 성능 분석에 대해서도 이해할 수 있음
* */

/*
* 버블정렬
* - 두 인접한 데이터를 비교해서, 앞에 있는 데이터가 뒤에 있는 데이터보다 크면, 자리를 바꾸는 알고리즘
* */
import java.util.ArrayList;
import java.util.Collections;
public class BubbleSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList){
        for(int index = 0 ; index < dataList.size() -  1 ; index++){
            // 자리교환 여부를 확인하기 위한 변수
            boolean swap = false;
            //
            for(int index2 = 0; index2 < dataList.size()-1 -index ; index2++){
                if(dataList.get(index2) > dataList.get(index2+1)){
                    Collections.swap(dataList, index2, index2+1);
                    swap = true;
                }
            }
            if(!swap){
                break;
            }
        }
        return dataList;
    }
    public static void main(String[] args) {
        ArrayList<Integer> dataList = new ArrayList<>();
        dataList.add(4);
        dataList.add(2);

        if(dataList.get(0) > dataList.get(1)){
            Collections.swap(dataList, 0 ,1);
        }
        System.out.println(dataList);
        System.out.println("----------------------------------");

        ArrayList<Integer> dataList1 = new ArrayList<>();
        dataList1.add(9);
        dataList1.add(2);
        dataList1.add(4);

        for(int i = 0; i < dataList1.size()-1 ; i++){
            if(dataList1.get(i) > dataList1.get(i+1)){
                Collections.swap(dataList1, i, i+1);
            }
        }
        System.out.println(dataList1);
        System.out.println("----------------------------------");

        ArrayList<Integer> testData = new ArrayList<Integer>();
        for(int i = 0 ; i < 10 ; i++){
            testData.add((int)(Math.random()*100));
        }
        BubbleSort bubbleSort = new BubbleSort();
        System.out.println(bubbleSort.sort(testData));
    }

}

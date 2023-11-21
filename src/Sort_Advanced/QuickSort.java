package Sort_Advanced;
import java.util.ArrayList;
import java.util.Arrays;

/*
* 퀵정렬
* - 정렬 알고리즘의 꽃
* - 기준점(pivot)을 정해서, 기준점보다 작은 데이터는 왼쪽, 큰 데이터는 오른쪽에 모으는 함수를 작성
* - 각 왼쪽/오른쪽은 재귀 용법을 사용해서 다시 동일 함수를 호출하여 위 작업을 반복함
* - 함수는 왼쪽 + 기준점 + 오른쪽을 리턴함
* */
public class QuickSort {
    public void splitFunc(ArrayList<Integer> dataList){
        if(dataList.size() <= 1){
            return;
        }
        int pivot = dataList.get(0);

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        for(int i = 1 ; i < dataList.size() ; i++){
            if(dataList.get(i) > pivot){
                rightArr.add(dataList.get(i));
            }else{
                leftArr.add(dataList.get(i));
            }
        }
        System.out.println("leftArr : "+leftArr);
        System.out.println("pivot : "+pivot);
        System.out.println("rightArr : "+rightArr);
    }

    public void splitFunction(ArrayList<Integer> dataList){
        if(dataList.size() <= 1){
            return;
        }
        int pivot = dataList.get(0);

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        for(int i = 1 ; i < dataList.size() ; i++){
            if(dataList.get(i) > pivot){
                rightArr.add(dataList.get(i));
            }else{
                leftArr.add(dataList.get(i));
            }
        }
        ArrayList<Integer> mergedArr = new ArrayList<Integer>();
        mergedArr.addAll(leftArr);
        mergedArr.addAll(Arrays.asList(pivot));
        mergedArr.addAll(rightArr);

        System.out.println("mergedArr : " + mergedArr);
    }

    // QuickSort 메서드 만들기
    // 1. 만약 배열 갯수가 1개면 해당 배열 리턴
    // 2. 그렇지 않으면, 배열의 맨앞 데이터를 기준점(pivot)으로 지정
    // 3. left / right 배열 변수 선언
    // 4. 맨 앞의 데이터를 뺸 나머지 데이터를 기준점과 비교(pivot)
    //    - 기준점보다 작으면 left.add(해당 데이터)
    //    - 기준점보다 크면 right.adD(해당 데이터)
    // 5. QuickSort.sort(left) + pivot + QuickSort.sort(right) 을 리턴하는 방식으로 재귀 호출
    public ArrayList<Integer> sort(ArrayList<Integer> data){
        // 만약 배열 갯수가 1개면 해당 배열 리턴
        if(data.size() <= 1){
            return data;
        }
        // 배열의 맨앞 데이터를 기준점(pivot)으로 지정
        int pivot = data.get(0);

        // left / right 배열 변수 선언
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        ArrayList<Integer> rightList = new ArrayList<Integer>();

        // 맨 앞의 데이터를 뺸 나머지 데이터를 기준점과 비교(pivot)
        for(int i = 1 ; i < data.size() ; i++){
            //    - 기준점보다 작으면 left.add(해당 데이터)
            if(data.get(i) < pivot){
                leftList.add(data.get(i));
            //    - 기준점보다 크면 right.adD(해당 데이터)
            }else{
                rightList.add(data.get(i));
            }
        }
        // QuickSort.sort(left) + pivot + QuickSort.sort(right) 을 리턴하는 방식으로 재귀 호출
        ArrayList<Integer> mergedList = new ArrayList<Integer>();
        // 배열.addAll(추가할 배열) : 추가할 배열을 순회하며 하나씩 추가한다.
        mergedList.addAll(this.sort(leftList));
        // pivot은 배열이 아닌 데이터 하나 이므로, Arrays.asList()를 활용해 배열로 만들어준다.
        mergedList.addAll(Arrays.asList(pivot));
        mergedList.addAll(this.sort(rightList));

        return mergedList;
    }
    public static void main(String[] args) {
        Integer[] list = {4, 1, 2, 5, 7};
        QuickSort quickSort = new QuickSort();
//        ArrayList<Integer> dataList = new ArrayList<Integer>(Arrays.asList(list));
//        quickSort.splitFunc(dataList);
        quickSort.splitFunc(new ArrayList<Integer>(Arrays.asList(list)));
        System.out.println("==========================================================");
        quickSort.splitFunction(new ArrayList<>(Arrays.asList(list)));
        System.out.println("==========================================================");
        ArrayList<Integer> dataSet = new ArrayList<Integer>();
        for(int i = 0 ; i < 25 ; i++){
            dataSet.add((int)(Math.random()*100));
        }
        System.out.println("Quick_Sort : "+quickSort.sort(dataSet));
    }
}

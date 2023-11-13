import java.util.ArrayList;
import java.util.Arrays;

/*
* 병합정렬(Merge Sort)
* - 재귀 용법을 활용한 알고리즘
*   1. 리스트를 절반으로 잘라 비슷한 크기의 두 부분 리스트로 나눈다.
*   2. 각 부분 리스트를 재귀적으로 합병 정렬을 이용해 정렬한다.
*   3. 두 부분 리스트를 다시 하나의 정렬된 리스트로 합병한다.
*
* 1단계 : 정렬되지 않은 배열을 끝까지 분리하는 단계
* 2단계 : 분리한 데이터를 단계별로 합치는 단계
* */
public class MergeSort {
    // 배열을 앞뒤 두 배열로 자르는 코드 작성(일반화)
    public void splitFunc(ArrayList<Integer> dataList){
        if(dataList.size() <= 1){
            return ;
        }

        int medium = dataList.size() / 2;

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        // 0부터 medium -1 인덱스 번호까지 해당 배열 아이템을 서브 배열로 추출
        leftArr = new ArrayList<Integer>(dataList.subList(0,medium));

        // medium 부터 마지막 인덱스번호까지 해당 배열 아이템은 서브 배열로 추출
        rightArr = new ArrayList<Integer>(dataList.subList(medium, dataList.size()));

        System.out.println("leftArr : "+leftArr);
        System.out.println("rightArr : "+rightArr);
    }

    // mergeSplitFunc 메서드 틀 만들기 (재귀용법 틀 만들기)
    public ArrayList<Integer> mergeSplitFunc(ArrayList<Integer> dataList){
        if(dataList.size() <= 1){
            return dataList;
        }
        int medium = dataList.size() / 2;

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        leftArr =  mergeSplitFunc(new ArrayList<Integer>(dataList.subList(0,medium)));
        rightArr = mergeSplitFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size())));

        // 객체 메서드 호출
        return this.mergeFunc(leftArr, rightArr);

    }

    // mergeFunc 메서드 만들기
    public ArrayList<Integer> mergeFunc(ArrayList<Integer> leftList, ArrayList<Integer> rightList){
        System.out.println("mergeFunc 의 leftList : "+leftList);
        System.out.println("mergeFunc 의 rightList : "+rightList);
        ArrayList<Integer> mergedList = new ArrayList<Integer>();


        // 각 배열의 0번 인덱스를 지정하는 변수 선언
        int leftPoint = 0;
        int rightPoint = 0;

        // Case1 : leftList, rightList 둘 다 있을 때
        while(leftList.size() > leftPoint && rightList.size() > rightPoint){
            if(leftList.get(leftPoint) > rightList.get(rightPoint)){
                mergedList.add(rightList.get(rightPoint));
                rightPoint += 1;
            }else{
                mergedList.add(leftList.get(leftPoint));
                leftPoint += 1;
            }
        }
        // Case2 : rightList 만 없을 때, 나머지 leftList 있는 데이터를 그대로 mergedList 뒤에 넣음
        while(leftList.size() > leftPoint){
            mergedList.add((leftList.get(leftPoint)));
            leftPoint += 1;
        }
        // Case3 : leftArr 만 없을 때, 나머지 rightList 있는 데이터를 그대로 mergedList 뒤에 넣음
        while(rightList.size() > rightPoint){
            mergedList.add(rightList.get(rightPoint));
            rightPoint += 1;
        }

        return mergedList;

    }

    public ArrayList<Integer> firstStep(ArrayList<Integer> dataList){
        if(dataList.size() <= 1){
            return dataList;
        }
        int center = dataList.size() / 2;

        ArrayList<Integer> forwardList = new ArrayList<Integer>(firstStep(new ArrayList<Integer>(dataList.subList(0, center))));
        ArrayList<Integer> backList = new ArrayList<Integer>(firstStep(new ArrayList<Integer>(dataList.subList(center, dataList.size()))));

       return secondStep(forwardList, backList);
    }

    public ArrayList<Integer> secondStep(ArrayList<Integer> forwardList, ArrayList<Integer> backList){
        ArrayList<Integer> mergedList = new ArrayList<Integer>();

        int forwardPoint = 0;
        int backPoint = 0;

        while(forwardList.size()>forwardPoint && backList.size() > backPoint){
            if(forwardList.get(forwardPoint) > backList.get(backPoint)){
                mergedList.add(backList.get(backPoint));
                backPoint += 1;
            }else{
                mergedList.add(forwardList.get(forwardPoint));
                forwardPoint += 1;
            }
        }

        while(forwardList.size()>forwardPoint ){
            mergedList.add(forwardList.get(forwardPoint));
            forwardPoint += 1;
        }

        while(backList.size() > backPoint){
            mergedList.add(backList.get(backPoint));
            backPoint += 1;
        }

        return mergedList;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        // Arrays.asList : 나열된 데이터를 배열로 변환 해주는 메서드
        mergeSort.splitFunc(new ArrayList<Integer>(Arrays.asList(4, 1, 2, 3, 5, 7)));

        ArrayList<Integer> testData = new ArrayList<Integer>();
        for(int i = 0 ; i < 20; i++){
            testData.add((int)(Math.random()*100));
        }
        System.out.println("병합정렬 전 : "+testData);
        System.out.println("병합정렬 후 : "+mergeSort.mergeSplitFunc(testData));

        System.out.println("-----------------------------------------------------------------");

        ArrayList<Integer> testList = new ArrayList<Integer>();
        for(int a = 0 ; a < 25 ; a++){
            testList.add((int)(Math.random()*100));
        }
        System.out.println("병합정렬 전 : "+testList);
        System.out.println("병합정렬 후 : "+mergeSort.firstStep(testList));
    }

}

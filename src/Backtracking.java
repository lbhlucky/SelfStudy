import java.util.ArrayList;
public class Backtracking {

    // Promising 단계를 수행하는 메서드
    // candidate : 현재까지의 퀸의 위치 좌표를 담은 배열
    // currentCol : 해당 인덱스 번호
    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol){
        Integer currentRow = candidate.size();
        for(int index = 0; index < currentRow ; index++){
            // 수직 체크 || 대각선 체크
            if((candidate.get(index) == currentCol) || (Math.abs(candidate.get(index)-currentCol) == currentRow-index)){
                return false;
            }
        }
        return true;
    }

    // Pruning 단계를 수행하는 메서드
    // N : 행의 갯수
    // currentRow : 현재 검토해야할 Row
    // currentCandidate : 현재 퀸이 위치한 좌표 정보 (Row, Col)
    public void dfsFunc(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate){
        if(currentRow == N){
            System.out.println(currentCandidate);
            return;
        }

        for(int index = 0; index < N ; index++){
            if(this.isAvailable(currentCandidate, index)){
                currentCandidate.add(index);
                this.dfsFunc(N, currentRow+1, currentCandidate);
                // 가지치기
                currentCandidate.remove(currentCandidate.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        backtracking.dfsFunc(4,0, new ArrayList<Integer>());
    }
}

/*
 * 해쉬 테이블 (Hash Table)
 * - 키(key) 에 데이터(value)를 매핑할 수 있는 데이터 구조
 * - 해쉬 함수를 통해, 배열에 키에 대한 데이터를 저장할 수 있는 주소(인덱스번호)를 계산
 * - Key를 통해 바로 데이터가 저장되어 있는 주소를 알 수 있으므로, 저장 및 탐색 속도가 획기적으로 빨라짐
 * - 미리 해쉬 함수가 생성할 수 있는 주소(인덱스번호)에 대한 공간을 배열로 할당한 후, 키에 따른 데이터 저장 및 탐색 지원
 *
 * 알아둘 용어
 * - 해쉬 함수(Hash Function)
 *   : 해쉬(Hash), 해쉬 값(Hash Value), 또는 해쉬 주소(Hash Address)
 *     => 해싱 함수를 통해 리턴된 고정된 길이의 값
 * - 해쉬 테이블(Hash Table)
 *   : 키 값의 연산에 의해 직접 접근이 가능한 데이터 구조
 *     => 슬롯(slot) : 해쉬 테이블에서 한개의 데이터를 저장할 수 있는 공간
 *
 * 해쉬 테이블의 장점
 * - 데이터 저장/읽기 속도가 빠르다(= 검색 속도가 빠르다)
 * - 해쉬는 키에 대한 데이터가 있느지(중복) 확인이 쉬움
 * 해쉬 테이블의 단점
 * - 일반적으로 저장공간이 좀 더 많이 필요하다.
 * - 여러 키에 해당하는 주소가 동일할 경우 충돌을 해결하기 위한 별도 자료구조가 필요함.
 * 해쉬 테이블의 주요 용도
 * - 검색이 많이 필요한 경우
 * - 저장, 삭제, 읽기가 빈번한 경우
 * - 캐쉬 구현시(중복 확인이 쉽기 때문)
 * 
 * 충돌(Collision) 해결 알고리즘 (좋은 해쉬 함수 사용하기)
 * - 해쉬테이블의 가장 큰 문제는 충돌(Collision)의 경우이다.
 * 3. 빈번한 충돌을 개선하는 기법
 * - 해쉬테이블 저장공간을 확대 및 해쉬함수 재정의
 *
 * 해쉬테이블의 시간복잡도
 * - 일반적인 경우(충돌이 없는 경우) : O(1)
 * - 최악의 경우(충돌이 모두 발생하는경우) : O(n)
 *
 * 검색 시 해쉬테이블 사용 예
 * 1. 배열에 데이터를 저장하고 검색할 때 : O(n)
 * 2. 이상적인 해쉬테이블 케이스에서 데이터를 검색할 때 : O(1)
 * */

// hash table 클래스 만들기
public class Hash {
    // 슬롯이라는 클래스 기반으로 객체 배열을 만들 수 있는 hashTable 생성
    public Slot[] hashTable;

    // 미리 배열의 사이즈를 할당할 수 있는 어트리뷰트 생성
    public Hash(Integer size){
        this.hashTable = new Slot[size];
    }

    // 내부 클래스로 Slot 정의
    public class Slot {
        String value;
        
        // 생성자 생성
        Slot(String value){
            this.value = value;
        }
    }

    // 해쉬 함수 생성
    public int hashFunc(String key){
        return (int)(key.charAt(0)) % this.hashTable.length;
    }
    
    // 데이터 저장 메서드 추가(저장 성공 true/ 실패 false)
    public boolean saveData(String key, String value){
        // 해당 데이터를 저장할 수 있는 주소 선언
        Integer address = this.hashFunc(key);
        
        // 현재 해쉬테이블 배열에 해당 주소가 null 아니면
        if(this.hashTable[address] != null){
            // 해당 주소에 저장된 value를 변경해준다
            this.hashTable[address].value = value;
        }else { // 아니라면
            // 해당 주소에 새로운 슬롯 객체를 생성
            this.hashTable[address] = new Slot(value);
        }
        // 저장 성공
        return true;
    }

    // key에 대한 데아터를 가져오는 메서드 추가
    public String getData(String key){
        // 해시함수를 이용해 주소값 가져오기
        Integer address = this.hashFunc(key);
        // 해당 주소에 값이 있으면
        if(this.hashTable[address] != null){
            // 해당 주소의 value 리턴
            return this.hashTable[address].value;
        }else{  // 아니라면
            return null;
        }
    }

    // Key 가 문자열일 때, 문자열의 앞 글자를 숫자로 변환해서, Divistion 기법을 사용하여,
    // Key에 대한 주소(인덱스번호) 계산
    // - Divistion 기법 : 가장 간단한 해쉬 함수 중 하나로, 나누기를 통해, 나머지 값을 사용하는 기법
    public static void main(String[] args) {
        String name = "DaveLee";
        System.out.println((int)(name.charAt(0)));
        int intName = (int)(name.charAt(0)) % 20;
        System.out.println(intName);

        Hash mainObj = new Hash(20);
        mainObj.saveData("DaveLee","01000002345");
        mainObj.saveData("Fun-Coding","01012346345");
        mainObj.saveData("David","010144443333");
        mainObj.saveData("Dave","01055556666");
        System.out.println(mainObj.getData("DaveLee"));
        System.out.println(mainObj.getData("Dave"));
    }
}

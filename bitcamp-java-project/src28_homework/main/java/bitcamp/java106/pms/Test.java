package bitcamp.java106.pms;

public class Test {
    public static void main(String[] args) {
        // 프로그램을 사ㅏ용할 때가 있는데 그 때, 그 API를 적용하기 전에 
        // 간단한 예제를 만들어 동작을 확인해보기.
        
        String str = "/board/add?title=aaa&content=bbb";
        String[] arr = str.split("\\?");
        
        for(String item : arr) 
            System.out.println(item);
        
    }
}

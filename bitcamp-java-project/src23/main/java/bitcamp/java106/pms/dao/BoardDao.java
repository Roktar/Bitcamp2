package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;

@Component
public class BoardDao extends AbstractDAO<Board>{

    public int getIndex(Object key) { // 부모클래스에 선언된 리스트를 돌아보며 값을 찾는다.
        int no = (int) key; // board는 글번호(int)로 검색한다.
        
        for(int i=0; i<data.size(); i++) {
            if( data.get(i).getNo() == no )
                return i;
        }
        return -1;
    }
}

// ver 23 - @Component Annotation 추가
// ver 22 - 추상클래스 상속
// ver 19 - 컬렉션 클래스 및 제네릭 적용
// ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성.






package bitcamp.java106.pms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.domain.Member;

@Component
public class MemberDao extends AbstractDAO<Member>{
    public MemberDao() throws Exception {
        this.load();
    }
    
    public void load() throws Exception {
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/member.data")));) {
            while( true ) {
                try {
                    this.insert((Member) in.readObject());
                } catch (Exception e) { 
                    break;
                }
            }
        }
    }
    
    public void save() throws Exception {        
        Iterator<Member> members = this.list();
        
        try( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/member.data")));) {
            while (members.hasNext()) 
                out.writeObject(members.next());
        } 
    }
    
    public int getIndex(Object key) {
        String id = (String) key;
        for(int i=0; i<collection.size(); i++) {    
            if( collection.get(i).getId().equalsIgnoreCase(id) )
                return i;
        }
        return -1;
    }
}

//ver 16 - 인스턴스 변수를 직접 사용하는 대신 겟터, 셋터 사용.
//ver 14 - MemberController로부터 데이터 관리 기능을 분리하여 MemberDao 생성.
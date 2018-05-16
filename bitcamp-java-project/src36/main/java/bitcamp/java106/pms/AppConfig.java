package bitcamp.java106.pms;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="bitcamp.java106.pms")
// Component 어노테이션이 붙은 클래스들의 객체를 자동으로 생성한다.
public class AppConfig {
    
    // 스프링 IoC 컨테이너에서 자동 생성하지 않는 객체를 메소드를 통해 리턴해야한다.
    // 팩토리 메소드를 만들어 스프링 IoC 컨테이너에게 리턴해야함.
    // 단, Spring IoC 컨테이너에게 이 메소드를 호출하여 이 메소드가 리턴한 객체를 컨테이너에 보관하도록 "명령"을 내려야한다.
    // 메소드 선언 앞에 @Bean 어노테이션을 부착하라.
    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("bitcamp/java106/pms/sql/mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(inputStream);
    }
}

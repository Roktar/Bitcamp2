package bitcamp.java106.pms.jdbc;

import java.sql.Connection;

public interface DataSource {
    public Connection getConnection() throws Exception;
    public void returnConnection(Connection con); 
}

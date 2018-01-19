package cn.mldn.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
 private static final String DBDRVER ="oracle.jdbc.driver.OracleDriver";
 private static final String DBURL ="jdbc:oracle:thin:@localhost:1521:MLDN";
 private static final String USER ="scott";
 private static final String PASSWORD ="tiger";
 private static ThreadLocal<Connection> threadLocal =new ThreadLocal<Connection>();
    private static Connection connectiondatabase() {
    	Connection conn=null;
    try {
    	Class.forName(DBDRVER);
 conn= DriverManager.getConnection(DBURL,USER,PASSWORD);
   }catch(Exception e) {
	   e.printStackTrace();
   }
    return conn;
}
    public static Connection getConnection() {
    	Connection conn=threadLocal.get();
    	if(conn==null) {
    		conn=connectiondatabase()	;
    		threadLocal.set(conn);
    	}return conn;
    }
    public static void close() {
    	Connection conn=threadLocal.get();
    	try{
    		if(conn!=null) {
    	
    	conn.close();
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}threadLocal.remove();
    }
}

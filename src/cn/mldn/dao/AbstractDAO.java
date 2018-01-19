package cn.mldn.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.mldn.dbc.DatabaseConnection;

public abstract class AbstractDAO {
  @SuppressWarnings("unused")
public  static Long handleGetCount(String tableName)throws Exception{
	  String sql="SELECT COUNT(*) FROM member " + tableName;
	  PreparedStatement pdsmt =DatabaseConnection.getConnection().prepareStatement(sql);
	  ResultSet rs=pdsmt.executeQuery();
	  if(rs.next()) {
		  return rs.getLong(1);
		  
	  }return 0L;
  }
}

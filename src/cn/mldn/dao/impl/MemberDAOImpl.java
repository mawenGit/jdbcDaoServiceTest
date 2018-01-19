package cn.mldn.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.AbstractDAO;
import cn.mldn.dao.IMemberDAO;
import cn.mldn.dbc.DatabaseConnection;
import cn.mldn.vo.Member;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
   private PreparedStatement  pdsmt =null;
	@Override
	public boolean doCreate(Member vo) throws SQLException {
	String sql="INSERT INTO  member  (mid,name,note,birthday,salary,age)  VALUES (?,?,?,?,?,?)";
	this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
	this.pdsmt.setString(1, vo.getMid());
	this.pdsmt.setString(2, vo.getName());
	this.pdsmt.setString(3, vo.getNote());
	this.pdsmt.setDate(4, new Date(vo.getBirthday().getTime()));
	this.pdsmt.setDouble(5, vo.getSalary());
	this.pdsmt.setInt(6, vo.getAge());
	
		return this.pdsmt.executeUpdate()>0;
	}

	@Override
	public boolean doEdit(Member vo) throws SQLException {
		String sql=" UPDATE member SET name=?,note=?,birthday=?,salary=?,age=? WHERE mid=?";
		this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pdsmt.setString(6, vo.getMid());
		this.pdsmt.setString(1, vo.getName());
		this.pdsmt.setString(2, vo.getNote());
		this.pdsmt.setDate(3, new Date(vo.getBirthday().getTime()));
		this.pdsmt.setDouble(4, vo.getSalary());
		this.pdsmt.setInt(5, vo.getAge());
		
			return this.pdsmt.executeUpdate()>0;
		}

	@Override
	public boolean doRemove(String id) throws SQLException {
		String sql="DELETE FROM member WHERE mid=?";
		this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pdsmt.setString(1, id);
		
		return this.pdsmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemvoe(Set<String> ids) throws SQLException {
	StringBuffer  buf=new StringBuffer();
	buf.append("DELETE FROM member WHERE mid IN (");
	Iterator<String >   its =ids.iterator();
	while(its.hasNext()) {
		buf.append("'").append(its.next()).append("',");
		
	}
	buf.delete(buf.length()-1, buf.length()).append(")");
	this.pdsmt=DatabaseConnection.getConnection().prepareStatement(buf.toString());
	
		return this.pdsmt.executeUpdate()>0;
	}

	@Override
	public Member findById(String id) throws SQLException {
		String sql=" SELECT mid,name,note,birthday,salary,age FROM member WHERE mid=?";
		this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
		Member mem=null;
		this.pdsmt.setString(1,id);
		ResultSet rs=this.pdsmt.executeQuery();
		if(rs.next()) {
			mem=new Member();
			mem.setMid(rs.getString(1));
			mem.setName(rs.getString(2));
			mem.setNote(rs.getString(3));
			mem.setBirthday(rs.getDate(4));
			mem.setSalary(rs.getDouble(5));
			mem.setAge(rs.getInt(6));
		}
				
		return mem;
	}

	@Override
	public List<Member> findAll() throws SQLException {
	 String sql="SELECT COUNT(*) FROM member ";
	 this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
	 List<Member> list =new ArrayList<Member>();
	 ResultSet rs=this.pdsmt.executeQuery();
	 while(rs.next()) {
		 Member mem=new Member();
		 mem.setMid(rs.getString(1));
			mem.setName(rs.getString(2));
			mem.setNote(rs.getString(3));
			mem.setBirthday(rs.getDate(4));
			mem.setSalary(rs.getDouble(5));
			mem.setAge(rs.getInt(6));
			list.add(mem);
	 }
		return list;
	}

	@Override
	public List<Member> findAll(Long currentPage, Integer lineSize) throws SQLException {
		String sql=" SELECT mid,name,note,birthday,salary,age,ROWNUM rn FROM member WHERE ROWNUM<=? )temp WHERE temp.rn>?";
		this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pdsmt.setLong(1, currentPage*lineSize);
		this.pdsmt.setLong(2, (currentPage-1)*lineSize);
		List<Member> list =new ArrayList<Member>();
		ResultSet rs =this.pdsmt.executeQuery();
		while(rs.next()) {
			Member mem=new Member();
			 mem.setMid(rs.getString(1));
				mem.setName(rs.getString(2));
				mem.setNote(rs.getString(3));
				mem.setBirthday(rs.getDate(4));
				mem.setSalary(rs.getDouble(5));
				mem.setAge(rs.getInt(6));
				list.add(mem);
		 }
			return list;
		}


	@Override
	public List<Member> findSplit(String column, String keyWord, Long currentPage, Integer lineSize)
			throws SQLException {
		String sql=" SELECT mid,name,note,birthday,salary,age,ROWNUM rn FROM member WHERE "
				+ ""+ column + " Like ? AND  ROWNUM<=? )temp WHERE temp.rn>?";
		this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pdsmt.setString(1, "%"+keyWord+"%");
		this.pdsmt.setLong(2, currentPage*lineSize);
		this.pdsmt.setLong(3, (currentPage-1)*lineSize);
		List<Member> list =new ArrayList<Member>();
		ResultSet rs =this.pdsmt.executeQuery();
		while(rs.next()) {
			Member mem=new Member();
			 mem.setMid(rs.getString(1));
				mem.setName(rs.getString(2));
				mem.setNote(rs.getString(3));
				mem.setBirthday(rs.getDate(4));
				mem.setSalary(rs.getDouble(5));
				mem.setAge(rs.getInt(6));
				list.add(mem);
		 }
			return list;
		}
	@Override
	public Long getAllCount() throws SQLException {
	try {
		return super.handleGetCount("member");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
		
	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		
		String sql= "SELECT mid,name,note,birthday,salary,age FROM member WHERE " + column +"Like ?";
		this.pdsmt=DatabaseConnection.getConnection().prepareStatement(sql);
		this.pdsmt.setString(1, "%"+keyWord+"%");
		ResultSet rs=this.pdsmt.executeQuery();
		if(rs.next()) {
			return rs.getLong(1);
			
		}return 0L;
				
		
	}

}

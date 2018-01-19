package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IDAO<K,V> {
public boolean doCreate(V vo)throws SQLException;
public boolean doEdit(V vo)throws SQLException;
public boolean doRemove(K id)throws SQLException;
public boolean doRemvoe(Set<K> ids)throws SQLException;
public V findById(K id)throws SQLException;
public List<V> findAll()throws SQLException;
public List<V> findAll(Long currentPage,Integer lineSize)throws SQLException;
public List<V> findSplit(String column,String keyWord,Long currentPage,Integer lineSize)throws SQLException;
public Long getAllCount()throws SQLException;
public Long getSplitCount(String column,String keyWord)throws SQLException;
}

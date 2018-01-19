package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IMemberDAO;
import cn.mldn.factory.Factory;
import cn.mldn.service.IMemberService;
import cn.mldn.vo.Member;

public class MemberServiceImpl implements IMemberService {
  private   IMemberDAO memberDAO =Factory.getInstance("member.dao");
	@Override
	public boolean add(Member vo) throws Exception {
		if(memberDAO.findById(vo.getMid())==null) {
			return this.memberDAO.doCreate(vo);
		}
		return false;
	}

	@Override
	public boolean edit(Member vo) throws Exception {
		
		return this.memberDAO.doEdit(vo);
	}

	@Override
	public boolean delete(String id) throws Exception {
		return this.memberDAO.doRemove(id);
		
	}

	@Override
	public boolean delete(Set<String> ids) throws Exception {
		if(!(ids==null||ids.size()==0)){
			return this.memberDAO.doRemvoe(ids);
		}
		
		return true;
	}

	@Override
	public Member get(String id) throws Exception {
		return this.memberDAO.findById(id);
		
	}

	@Override
	public List<Member> list() throws Exception{
		
		return this.memberDAO.findAll();
	}

	@Override
	public Map<String, Object> list(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
Map<String, Object> map=new HashMap<String,Object>();
if(column==null||column.equals("")||keyWord.equals("")||keyWord==null) {
	map.put("allMembers",memberDAO.findAll(currentPage,lineSize));
	map.put("memberCoutn", memberDAO.getAllCount());
}else {
	map.put("allMembers",memberDAO.findSplit(column, keyWord, currentPage, lineSize));
	map.put("memberCoutn", memberDAO.getSplitCount(column, keyWord));
}return map;
		
	}	

}

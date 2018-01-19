package cn.mldn.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.mldn.factory.Factory;
import cn.mldn.service.IMemberService;
import cn.mldn.vo.Member;
import junit.framework.TestCase;

public class MemberServiceImplTest {
  IMemberService service =Factory.getInstance("member.service");
  Member mem=new Member();
	@Test
	public void testAdd() {
		mem.setMid("xx");
		mem.setName("李伟");
		mem.setAge(11);
		mem.setBirthday(new Date());
		mem.setNote("=====");
		mem.setSalary(22.22);
		try {
			TestCase.assertTrue(service.add(mem));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEdit() {
		mem.setMid("x");
		mem.setName("伟");
		mem.setAge(1);
		mem.setBirthday(new Date());
		mem.setNote("====");
		mem.setSalary(2.22);
		try {
			TestCase.assertTrue(service.edit(mem));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteString() {
		try {
			TestCase.assertTrue(service.delete("x"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testDeleteSetOfString() {
		Set<String> set=new HashSet<String> ();
		set.add("x");
		set.add("o");
		try {
			TestCase.assertTrue(service.delete(set));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGet() {
		try {
		TestCase.assertNotNull(service.get("allMembers"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	@Test
	public void testList() {
		try {
			TestCase.assertNotNull(service.list());
			}catch(Exception e) {
				e.printStackTrace();
			}
			}

	@Test
	public void testListStringStringLongInteger() {
		try{
			Map<String,Object> map=service.list(null, null, 2L, 2);
		
		List<String> list=(List<String>) map.get("allMembers");
		Long Count =(long) map.get("memberCount");
		TestCase.assertTrue(list.size()>0&&Count>0);
		
	}catch(Exception e) {
		e.printStackTrace();
	}

}
}

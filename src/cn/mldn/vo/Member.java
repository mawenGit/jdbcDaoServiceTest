package cn.mldn.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
   private String mid;
   private String name;
   private String note;
   private Date birthday;
   private double	salary;
   private int age;
public String getMid() {
	return mid;
}
public void setMid(String mid) {
	this.mid = mid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
}

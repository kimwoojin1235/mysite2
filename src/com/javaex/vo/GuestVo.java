package com.javaex.vo;

public class GuestVo {
	//필드
	private int no;
	private String name, password, content, regdate;

	//생성자
	public GuestVo() {}


	public GuestVo(int no, String name, String password, String content, String regdate) {
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regdate = regdate;
	}
	public GuestVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	}
	
	public GuestVo(int no, String password) {
		super();
		this.no = no;
		this.password = password;
	}
	//메소드 - g/s
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String reg_date) {
		this.regdate = reg_date;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "guestVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content + ", reg_date="
					+ regdate + "]";
	}
	
	
}

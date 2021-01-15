package com.javaex.vo;

public class BoardVo {
	private int no;
	private String title;
	private String content;
	private int hit;
	private String egdate;
	private int userno;
	private String name;
	public BoardVo() {
	}

	public BoardVo(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public BoardVo(String title, String content, int hit, String egdate, String name) {
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.egdate = egdate;
		this.name = name;
	}
	public BoardVo(int no, String title, String content, int hit, String egdate, int userno, String name) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.egdate = egdate;
		this.userno = userno;
		this.name = name;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getEgdate() {
		return egdate;
	}
	public void setEgdate(String egdate) {
		this.egdate = egdate;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

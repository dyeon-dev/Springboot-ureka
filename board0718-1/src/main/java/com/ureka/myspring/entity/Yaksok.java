package com.ureka.myspring.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Yaksok {
	@Id
	private int no;
	private String author, content, place;
	private Date ysDate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getYsDate() {
		return ysDate;
	}
	public void setYsDate(Date ysDate) {
		this.ysDate = ysDate;
	}
	
}

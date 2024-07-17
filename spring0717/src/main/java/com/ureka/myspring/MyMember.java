package com.ureka.myspring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// 기본생성자 자동생성
@NoArgsConstructor
// 모든 변수를 파라미터 받는 생성자
//@AllArgsConstructor
// 일부 변수만 받는 생성자(@NotNull or final)
@RequiredArgsConstructor

@Getter
@Setter
@Entity
public class MyMember {
	@Id
	private int memno;
	@Column(name="memname")
	private String memname;
	@Column(name="tel")
	private String tel;
}

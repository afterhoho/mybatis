package com.oracle.oBootMybatis01.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Emp {
	
	//테이블 데이터용
	private int empno;
	@NotEmpty(message = "이름은 필수 입니다.")
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	
	//조회용
	private String search;
	private String pageNum;
	private String keyword;
	private int start;
	private int end;
	
	//page 정보
	private String currentPage;
	
}

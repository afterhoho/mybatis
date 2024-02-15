package com.oracle.oBootMybatis01.model;

import lombok.Data;

@Data //@getter @setter있어서 이거 해주는거
public class Dept {
	
	private int deptno;
	private String dname;
	private String loc;

}

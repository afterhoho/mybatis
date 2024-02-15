package com.oracle.oBootMybatis01.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member1 {
	private String 	id;
	private String 	name;
	private String 	password;
	private Date	reg_date;

}

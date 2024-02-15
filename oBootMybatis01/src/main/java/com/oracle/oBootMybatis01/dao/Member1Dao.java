package com.oracle.oBootMybatis01.dao;

import java.util.List;

import com.oracle.oBootMybatis01.model.Member1;

public interface Member1Dao {

	int memCount(String id); // Member1의 Count

	List<Member1> listMem(Member1 member1);
}

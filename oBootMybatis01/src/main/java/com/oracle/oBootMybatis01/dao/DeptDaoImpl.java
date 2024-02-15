package com.oracle.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImpl implements DeptDao {
	
	private final SqlSession session;

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList =null;
		System.out.println("DeptDaoImpl deptSelect Start...");
		
		try {
			deptList = session.selectList("tkSelectDept");
		}catch(Exception e) {
			System.out.println("DeptDaoImpl deptSelect Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return deptList;
	}

	@Override
	public int insertEmp(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insert Start...");
		try {
			result = session.insert("insertEmp",emp);
		}catch(Exception e) {
			System.out.println("EmpDaoImpl insert Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImpl insertDept start...");
		session.selectOne("procDeptInsert",deptVO);
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("DeptDaoImpl selListDept start...." );
		session.selectOne("procDeptList",map);
	}

}

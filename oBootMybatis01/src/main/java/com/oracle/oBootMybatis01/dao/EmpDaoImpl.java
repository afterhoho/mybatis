package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository // DAO나 repository나 @repository로 걸어줌
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {
	// Mybatis DB 연동
	private final SqlSession session;

	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl start total...");

		try {

			totEmpCount = session.selectOne("com.oracle.oBootMybatis01.EmpMapper.empTotal");

			System.out.println("EmpDaoImpl totalEmp totEmpCount->" + totEmpCount);

		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp Exception ->" + e.getMessage());
		}

		return totEmpCount;
	}

//조회
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp start...");

		try {
			//
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList.size()->" + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp e.getMessage()->" + e.getMessage());
			e.printStackTrace();
		}

		return empList;
	}
	
	// 클릭시 내용 보이는거
	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpDaoImpl detail start...");
		Emp emp =new Emp();
		try {
			//						mapper ID   ,  Parameter
			emp = session.selectOne("tkEmpSelOne",empno);
			System.out.println("EmpDaoImpl detail getEname->"+emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpDaoImpl update Start...");
		int updateCount = 0;
		try {
			updateCount = session.update("tkEmpUpdate",emp);
		}catch(Exception e) {
			System.out.println("EmpDaoImpl updateEmp Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return updateCount;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listManager Start ...");
		try {
			// emp 관리자만 Select 			Naming Rule
			empList = session.selectList("tkSelectManager");
		}catch(Exception e) {
			System.out.println("EmpDaoImpl list Manager Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public int insertEmp(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insertEmp Start...");
		try {
			result = session.insert("insertEmp",emp);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteEmp(int empno) {
		int result = 0;
		System.out.println("EmpDaoImpl deleteEmp Start...");
		try {
			result =session.delete("deleteEmp",empno);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Emp> empSearchList3(Emp emp) {
		List<Emp>empSearchList3 = null;
		System.out.println("EmpDaoImpl empSearchList3  start...");
		System.out.println("EmpDaoImpl empSearchList3  emp->"+emp);
		try {
			// keyword 검색
			// Naming Rule						Map ID			parameter
			empSearchList3 =session.selectList("tkEmpSearchList3",emp);
		}catch(Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception"+e.getMessage());
			e.printStackTrace();
		}
		return empSearchList3;
	}
	
	@Override
	public int condTotalEmp(Emp emp) {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl Start condTotalEmp ");
		System.out.println("EmpDaoImpl Start emp-> "+emp);
		
		try {
			totEmpCount = session.selectOne("condTotalEmp",emp);
			System.out.println("EmpDaoImpl totalEmp totEmpCount->" + totEmpCount);
		}catch(Exception e) {
			System.out.println("condTotalEmp Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return totEmpCount;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept>empDept =null;
		System.out.println("EmpDaoImpl listEmpDept  start");
		
		try {
			empDept = session.selectList("tkListEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDept.size() start" + empDept.size());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return empDept;
	}


}
package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.Escape;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dao.Member1Dao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;

import jakarta.mail.Session;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	
	
	private final EmpDao ed;
	private final DeptDao dd;
	private final Member1Dao md;


	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl start total...");
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl totalEmp totEmpCnt->"+totEmpCnt);
		return totEmpCnt;
		
	}
	// 조회
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServicImpl start listEmp");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->"+empList.size());
		
		return empList;
	}
	
	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpServiceImpl detail..."); 
		Emp emp =null;
		emp = ed.detailEmp(empno);
		return emp;
	}
	
	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpServiceImpl update ");
		int updateCount =0;
		updateCount = ed.updateEmp(emp);
	
		
		
		return updateCount;
	}
	
	@Override
	public List<Emp> listManager() {
		List<Emp> empList =null;
		System.out.println("EmpServiceImpl listManager ");
		empList =ed.listManager();
		System.out.println("EmpServiceImpl listEmp empList.size()->"+empList.size());
		
		return empList;
	}
	
	@Override
	public List<Dept> deptSelect() {
		List<Dept>deptList = null;
		System.out.println("EmpServiceImpl deptSelect Start");
		deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()->"+deptList.size());
		return deptList;
	}
	
	   @Override
	   public int insertEmp(Emp emp) {

	      int result = 0;
	      System.out.println("EmpServiceImpl insertEmp() Start!");
	      
	      result = ed.insertEmp(emp);
	      System.out.println("EmpServiceImpl insertEmp() result : " + result);
	      
	      return result;
	   }
	@Override
	public int deleteEmp(int empno) {

		int result = 0;
		System.out.println("EmpServiceImpl deleteEmp Start!");
		result = ed.deleteEmp(empno);
		System.out.println("EmpService deleteEmp ");
		
		return result;
	}
	@Override
	public List<Emp> listSearchEmp(Emp emp) {
		List<Emp>empSearchList = null;
		System.out.println("EmpServiceImpl listEmp start");
		empSearchList = ed.empSearchList3(emp);
		System.out.println("EmpServiceImpl listSearchEmp empSearchList.size() ->" + empSearchList.size());
		
		return empSearchList;
	}
	
	@Override
	public int condTotalEmp(Emp emp) {
		int totEmpCnt = 0;
		System.out.println("EmpServiceImpl condTotalEmp start");
		totEmpCnt = ed.condTotalEmp(emp);
		System.out.println("EmpServiceImpl condtotalEmp totEmpCnt->"+totEmpCnt);
		return totEmpCnt;
	}
	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept>empdeptlist = null;
		System.out.println("EmpServiceImpl listEmpDept  start");
		empdeptlist = ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept empdeptlist.size() " + empdeptlist.size());
		return empdeptlist;
	}
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept  start");
		dd.insertDept(deptVO);
	}
	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept start...");
		dd.selListDept(map);
		
	}
	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id->"+id);
		return md.memCount(id);
	}
	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl listMem Start..");
		return md.listMem(member1);
	}
	
	



}

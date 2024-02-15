package com.oracle.oBootMybatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class LogAop {
//	// oBootMybatis01.dao.EmpDao 패키지 안에 있는 모든 메소드
//	@Pointcut("within(com.oracle.oBootMybatis01.dao.*)")
//	private void pointcutMethod() {
//		
//	}
//	@Around("pointcutMethod()")
//	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
//		// com.oBootMybatis01.dao.EmpDao 수행되는 핵심관심사 코어컨설
//		String signatureStr = joinPoint.getSignature().toString();
//		System.out.println(signatureStr + "is Strat...");
//		long st = System.currentTimeMillis();
//		
//		try {
//			// 핵심관심사
//			Object obj = joinPoint.proceed();
//			return obj;
//		} finally  {
//			long et = System.currentTimeMillis();
//			System.out.println(signatureStr + "is finished.");
//			System.out.println(signatureStr + "경과시간"+(et-st));
//		}
//	}
//	@Before("pointcutMethod()")
//	public void before() {
//		
//			System.out.println("before");
//		
//	}
	
}

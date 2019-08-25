package com.michael.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.michael.annotation.UserAnnotation;
import com.michael.dao.BaseDao;
import com.michael.service.BaseManager;

/**
 * 切面demo
 * 
 * @author
 * 
 */
@Aspect
@Component
public class UserAspect {

	@Autowired
	private BaseManager baseManager;
	@Autowired
	private HttpServletRequest request;
//	@Pointcut("execution(* com.xiao.cn.controller..*(..)) && @annotation(anno)")
//	public void myAspect(){
//		
//	}
	
	@Around("execution(* com.xiao.cn.controller..*(..)) && @annotation(anno)")
	public Object logArround(ProceedingJoinPoint pjp, UserAnnotation anno)
			throws Throwable {
		return pjp.proceed();
	}

}

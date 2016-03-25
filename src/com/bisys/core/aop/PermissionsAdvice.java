package com.bisys.core.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.bisys.core.exception.PermissionsException;

public class PermissionsAdvice {  
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired  
	protected HttpServletRequest request;
    /** 
     * 在核心业务执行前执行，不能阻止核心业务的调用。 
     * @param joinPoint 
     */  
    private void doBefore(JoinPoint joinPoint) {

    }  
      
    /** 
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理, 
     *  
     * 注意：当核心业务抛异常后，立即退出，转向After Advice 
     * 执行完毕After Advice，再转到Throwing Advice 
     * @param pjp 
     * @return 
     * @throws Throwable 
     */  
    private Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
    	Object retVal = null;
    	String url = request.getRequestURI() + (request.getParameter("platform")==null?"":":" + request.getParameter("platform"));
    	Subject currentUser = SecurityUtils.getSubject();
    	if(currentUser.isPermitted(url)){
    		logger.info("[合法访问]"+url);
    		//调用核心逻辑  
            retVal = pjp.proceed(); 
    	}else{
    		logger.info("[非法访问]"+url);
    		throw new PermissionsException("非法访问"+url);
    	}
        return retVal;  
    }  
  
    /** 
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice 
     * @param joinPoint 
     */  
    private void doAfter(JoinPoint joinPoint) {  

    }    
}  

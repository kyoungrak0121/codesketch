package org.spring.code.handlers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.code.helper.JsonHelper;
import org.spring.code.helper.LoggerHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* Interceptor : servlet-context.xml 에 설정  */
public class GlobalInterceptorHandler extends HandlerInterceptorAdapter {
	

	// 컨트롤러 실행 (직)전에 수행됩니다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {	
    	
    	// HandlerMethod 는 후에 실행할 컨트롤러의 메소드 입니다.
        if(handler == null || !(handler instanceof HandlerMethod)){ return super.preHandle(request, response, handler); }
        
        // 메소드명, 인스턴스, 타입, 사용된 Annotation 들을 확인할 수 있습니다.
        HandlerMethod method = (HandlerMethod) handler ; 
        
        LoggerHelper.debug(this,  "\n[Request URI]:"	+ request.getRequestURI()
        						+ "\n[Handler Class]:"	+ method.getBean().getClass()
        						+ "\n[Handler Method]:"	+ method.getMethod().getName()
        						+ "\n[Requset Prameta]:"+ JsonHelper.toJson(request.getParameterMap()).toString()
        				 );
        
        return super.preHandle(request, response, handler);
    }
 

    // 컨트롤러 실행 직)후에 수행됩니다.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	//LoggerHelper.debug(this,"postHandle call......");
    	
    	if(modelAndView == null){ return; }
    	
    	LoggerHelper.debug(this,modelAndView);
    	
    	String uri = request.getRequestURI().replaceAll("/admin/","");
    	
    	modelAndView.addObject("path", Stream.of(uri.split("/"))
											.filter(item -> item != null && ! "".equals(item))
											.collect(Collectors.toList()));
    	
    	super.postHandle(request, response, handler, modelAndView);
    }
    
    /*
    // View 렌더링이 끝난 직후에 수행됩니다.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	//LoggerHelper.debug(this,"afterCompletion call......");
    	if(handler == null || !(handler instanceof HandlerMethod)){ return; }
    }

    // 비동기 호출시 수행됩니다.
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//LoggerHelper.debug(this,"afterConcurrentHandlingStarted call......");
    }
   	*/
    
}

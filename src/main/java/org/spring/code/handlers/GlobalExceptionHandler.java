package org.spring.code.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.spring.code.helper.LoggerHelper;
import org.spring.code.helper.ResponseHelper;
import org.spring.code.services.CodeService;
import org.spring.code.vo.dto.CodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler{
	
	@Autowired @Qualifier("codeService")
	private CodeService codeService ;
	
	private static Map<String,CodeDto> codeDtoMap ;
	
	@PostConstruct
	public void init(){
		codeDtoMap = codeService.getCodeDtoMap(new HashMap<>());
	}
		
	
    
	// Specify name of a specific view that will be used to display the error:
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public ResponseEntity<Object> databaseError(HttpServletRequest req, Exception ex) {
		// Nothing to do.Returns the logical view name of an error page, passed
		// to the view-resolver(s) in usual way.
		// Note that the exception is NOT available to this view (it is not added
		// to the model) but see "Extending ExceptionHandlerExceptionResolver"
		// below.
		
		LoggerHelper.debug(this, "databaseError : Request: " + req.getRequestURL() + " raised " + ex.toString());
		ex.printStackTrace();
		
		//handleError(req,ex);
		
		//DB Error는 보통 ajaxt로 인해 오류가 난다 
		//ajax return value Error
		return ResponseHelper.send(codeDtoMap.get("E000500"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// Total control - setup a model and return the view name yourself.
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleError(HttpServletRequest req, Exception ex) {
		
		LoggerHelper.debug(this, "handleError : Request: " + req.getRequestURL() + " raised " + ex.toString());
		
		ex.printStackTrace();
		
	/*
		// error page로 redirect함 
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	*/
		return ResponseHelper.send(codeDtoMap.get("E000500"),HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
}

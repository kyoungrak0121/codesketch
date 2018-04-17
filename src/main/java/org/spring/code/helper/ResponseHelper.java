package org.spring.code.helper;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class ResponseHelper{
	public static ResponseEntity<Object> send(Object obj, HttpStatus state) {
		return new ResponseEntity<Object>(obj,state);
	}
}

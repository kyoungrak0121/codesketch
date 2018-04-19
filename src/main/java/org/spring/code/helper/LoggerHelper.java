package org.spring.code.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class  LoggerHelper {

	protected static final Logger Logger = LoggerFactory.getLogger(LoggerHelper.class);
	

	/* debug helper Method */
	public static void debug(Object obj,String str) {
		Logger.debug("=================================================================================================");
		Logger.debug("\n["+ obj.getClass().toString() +"]\n"+"=> " + str.replaceAll("\n","\r\n\t")+"\n"); 
		Logger.debug("=================================================================================================");
	}
	public static void debug(Object obj,Object vo) {
		debug(obj, JsonHelper.toJson(vo).toString());
	}
}

package org.spring.code.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Scope(value = "singleton")
@Component
public class JsonHelper {

	private static final Gson gson  = new GsonBuilder().serializeNulls().create();
	private static final JsonParser jsonParser = new JsonParser();
	//private JsonObject jsonObject ;
	//private JsonElement jsonElement
	//private String jsonData ; 
	

	/*
	public void setJsonObject(JsonObject jsonObject) {
		JsonElement rootObejct = parser.parse(json).getAsJsonObject().get(key);
	}
	*/
	
	public static String toJson(Object obj){
		return  gson.toJson(obj);
	}
	
	public static <E>Object getJsonToObject(String json,Class<?> cls) {
		
		//Type type = new TypeToken<T>() {}.getType();
		// Convert JSON to Java Object
		Object targetObj = gson.<E>fromJson(json, cls);
		return targetObj;
	}
	
	// Generic Method
	// 메서드 아규먼트에 타입 매개변수 T가 선언되어 있으면 메서드의 리턴 타입 앞에 제너릭 타입을 선언해야 한다.
	public static <E> List<E> getJsonToObjectList(String json, String key, Class<?> cls){
		
		JsonElement jsonElement = jsonParser.parse(json);
		JsonArray jsonArray = null ;
		List<E> list =  new ArrayList<>();
		
		if(jsonElement.isJsonArray() ){ 
			jsonArray = jsonElement.getAsJsonArray();
		}else {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			
			if(!isJsonKey(jsonObject, key)) {
				return null ;
			}
			jsonArray = jsonObject.get(key).getAsJsonArray();
		}
		
		for(final JsonElement element : jsonArray){
			E entity = gson.<E>fromJson(element, cls);
			list.add(entity);
        }

		return list;
	}
	
	public static boolean isJsonKey(JsonObject json ,String key){
		
		if( json.get(key) == null || json.get(key).isJsonNull() ){  
			return false; 
		}
		return true;
	}
	
}

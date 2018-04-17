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
	
	public static Object getJsonToObject(String json,Class<Object> cls) {
		
		//Type type = new TypeToken<T>() {}.getType();
		// Convert JSON to Java Object
		Object targetObj = gson.fromJson(json, cls);
		return targetObj;
	}
	
	// Generic Method
	public static <E> List<E> getJsonToObjectList(String json, String key, Class<?> cls){
		
		if(! isJsonKey(json, key)){ return null; }
		
		JsonArray jsonArray = jsonParser.parse(json).getAsJsonObject().get(key).getAsJsonArray();
		
		List<E> list =  new ArrayList<>();
        
		for(final JsonElement jsonElement : jsonArray){
			E entity = gson.<E>fromJson(jsonElement, cls);
			list.add(entity);
        }

		return list;
	}
	
	public static boolean isJsonKey(String json ,String key){
		JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
		if(jsonObject.get(key) == null || jsonObject.get(key).isJsonNull() ){ 
			return false; 
		}
		return true;
	}
	
}

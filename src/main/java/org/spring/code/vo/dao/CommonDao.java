package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao<T> {
	
	public final static String NAMESPACE = "org.spring.code.vo.dao.";
	
    public List<T> selectList(Map<?, ?> params);
    public Object selectOne(Map<?, ?> params);
    
    public Object insert(List<T> vo);
    public Object update(T vo);
    public Object delete(List<T> vo);

}

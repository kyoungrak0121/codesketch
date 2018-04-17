package org.spring.code.vo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.spring.code.helper.LoggerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
		
	@Autowired
	protected SqlSession sqlSession;
		
	public void setSqlSession(SqlSession sqlSession) {
	    this.sqlSession = sqlSession;
	}
	
	// 바인딩 전  ?? 형태로 보는 경우
	
	public String getMapperSql(String queryId, T vo){
		return sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(vo).getSql();
	}
	
	public String getMapperSql(String queryId, Map<?,?> params){
		return sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(params).getSql();
	}
	
	// 바인딩 후 param까지 셋팅해서 보는 경우
	public String getMapperQuery(String queryId , Map<?,?> params){

		BoundSql boundSql = sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(params);
		String query = boundSql.getSql();
		Object paramObj = boundSql.getParameterObject();

		if(paramObj != null){              // 파라미터가 아무것도 없을 경우
			List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
			for(ParameterMapping mapping : paramMapping){
				String propValue = mapping.getProperty();       
				query=query.replaceFirst("\\?", "#{"+propValue+"}");
			}
		}
		return query; 
	}
	
	@Override
	public List<T> select(Map<?,?> params) { return null; }
	public List<T> select(String namespace, Map<?,?> params){ 
		LoggerHelper.debug(this, getMapperQuery(namespace , params));
		return sqlSession.selectList(namespace , params); 
	}
	
	@Override
	public Object insert(List<T> vo) { return null; }
	public Object insert(String namespace, List<T> vo){
		return sqlSession.insert(namespace , vo); 
	}
	
	
	@Override
	public Object update(T vo) { return null; }
	public Object update(String namespace , T vo){ 		
		return sqlSession.update(namespace , vo);
	}
	
	@Override
	public Object delete(List<T> vo) { return null; }
	public Object delete(String namespace ,List<T> vo){ 	
		return sqlSession.delete(namespace , vo); 
	}


}

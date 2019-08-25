package com.michael.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.michael.annotation.UserAnnotation;
import com.michael.datasource.DBContextHolder;

/**
 * 数据库级查询
 * 
 * @author michael
 *
 */
@Repository
public class BaseDao extends SqlSessionDaoSupport {
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	/**
	 * 新增
	 * 
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int insertRow(T entity) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().insert(entity.getClass().getSimpleName() + ".insert", entity);
	}

	public <T> int insertRow(String statementName, T entity) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().insert(statementName, entity);
	}

	/**
	 * 批量新增
	 * 
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int insertRows(List<T> entities) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().insert(entities.get(0).getClass().getSimpleName() + ".batchInsert", entities);
	}

	public <T> int insertRows(String statementName, List<T> entities) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().insert(statementName, entities);
	}

	/**
	 * 删除
	 * 
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int deleteRow(Class<T> entity, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
			return getSqlSession().update(entity.getSimpleName() + ".delete", parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	/**
	 * 删除
	 * 
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int deleteNoRow(Class<T> entity, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().update(entity.getSimpleName() + ".delete", parameters);
	}

	public <T> int deleteRow(String statementName, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
			return getSqlSession().delete(statementName, parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	public <T> int deleteNoRow(String statementName, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().delete(statementName, parameters);
	}

	/**
	 * 更新
	 * 
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int updateRow(T entity) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().update(entity.getClass().getSimpleName() + ".update", entity);
	}

	public int updateRow(String statementName, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
			return getSqlSession().update(statementName, parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	public int updateNoRow(String statementName, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.WRITE_DATA_SOURCE);
		return getSqlSession().update(statementName, parameters);
	}

	/**
	 * 根据实体标识查询
	 * 
	 * @param entity 实体
	 * @return 实体
	 */
	@SuppressWarnings("unchecked")
	public <T> T getRow(Class<T> entity, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
			return (T) getSqlSession().selectOne(entity.getSimpleName() + ".get", parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	/**
	 * 根据实体标识查询
	 * 
	 * @param entity 实体
	 * @return 实体
	 */
	@SuppressWarnings("unchecked")
	public <T> T getNoRow(Class<T> entity, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
		return (T) getSqlSession().selectOne(entity.getSimpleName() + ".get", parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> T getRow(String statementName, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
			return (T) getSqlSession().selectOne(statementName, parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getNoRow(String statementName, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
		return (T) getSqlSession().selectOne(statementName, parameters);
	}

	/**
	 * 根据实体标识查询
	 * 
	 * @param entity 实体
	 * @return 实体
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entity, String id) {
		return (T) getSqlSession().selectOne(entity.getSimpleName() + ".get", id);
	}

	/**
	 * 查询实体集
	 * 
	 * @param entity 实体
	 * @return 实体集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryRows(Class<T> entity, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
			return getSqlSession().selectList(entity.getSimpleName() + ".query", parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	/**
	 * 查询实体集
	 * 
	 * @param entity 实体
	 * @return 实体集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryNoRows(Class<T> entity, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
		return getSqlSession().selectList(entity.getSimpleName() + ".query", parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryRows(String statementName, Map<String, Object> parameters) {
		try {
			DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
			return getSqlSession().selectList(statementName, parameters);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryRows(String statementName, Object params) {
		DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
		return getSqlSession().selectList(statementName, params);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryNoRows(String statementName, Map<String, Object> parameters) {
		DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
		return getSqlSession().selectList(statementName, parameters);
	}

	/**
	 * 查询返回map
	 * 
	 * @param statementName 命名空间+id
	 * @param parameters 请求参数
	 * @param key 作为主见的key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> queryMapRows(String statementName, Map<String, Object> parameters, String key) {
		try {
			DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
			return getSqlSession().selectMap(statementName, parameters, key);
		} finally {
			if(parameters!=null){
				parameters.clear();
				parameters = null;
			}
		}
	}

	/**
	 * 查询返回map
	 * 
	 * @param statementName 命名空间+id
	 * @param parameters 请求参数
	 * @param key 作为主见的key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> queryMapNoRows(String statementName, Map<String, Object> parameters, String key) {
		DBContextHolder.setDBType(DBContextHolder.READ_DATA_SOURCE);
		return getSqlSession().selectMap(statementName, parameters, key);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryNoClearMapRows(String statementName, Map<String, Object> parameters) {
		return getSqlSession().selectList(statementName, parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String, T> queryNoClearMapRows(String statementName, Map<String, Object> parameters, String key) {
		return getSqlSession().selectMap(statementName, parameters, key);
	}
}

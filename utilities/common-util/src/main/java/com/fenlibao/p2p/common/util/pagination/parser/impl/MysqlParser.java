package com.fenlibao.p2p.common.util.pagination.parser.impl;

import java.util.Map;

import com.fenlibao.p2p.common.util.pagination.Page;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

public class MysqlParser extends AbstractParser {

	public String getPageSql(String sql) {
		StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
		sqlBuilder.append(sql);
		sqlBuilder.append(" limit ?,?");
		return sqlBuilder.toString();
	}

	public Map<Object, Object> setPageParameter(MappedStatement ms, Object parameterObject,
			BoundSql boundSql, Page<Object> page) {
		Map<Object, Object> paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
		paramMap.put(PAGEPARAMETER_FIRST, page.getStartRow());
		paramMap.put(PAGEPARAMETER_SECOND, page.getPageSize());
		return paramMap;
	}

}
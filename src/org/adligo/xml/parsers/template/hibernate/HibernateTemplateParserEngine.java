package org.adligo.xml.parsers.template.hibernate;

import java.sql.SQLException;

import org.adligo.xml.parsers.template.TemplateParserEngine;
import org.adligo.xml.parsers.template.jdbc.QueryParameterAggregator;
import org.adligo.xml.parsers.template.jdbc.JdbcParamsDecorator;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class HibernateTemplateParserEngine {

	/**
	 * note this api return the populated query (with filled in question marks)
	 * but does not bind it to a Class, thats up to you 
	 * @param in
	 * @return
	 */
	public static SQLQuery parse(HibernateEngineInput in) throws SQLException {
		 //does a null check for connection, params, and template
		  // allowed operators is a internally managed set (never null)
		  in.validate();
		  QueryParameterAggregator aggregator = new QueryParameterAggregator();
		  JdbcParamsDecorator jdbcParams =	new JdbcParamsDecorator(in.getParams(), 
				  in.getAllowedOperators(), aggregator);
		  Session session = in.getSession();
		  String sqlWithQuestionMarks = TemplateParserEngine.parse(in.getTemplate(), jdbcParams);
		  SQLQuery toRet = session.createSQLQuery(sqlWithQuestionMarks);
		  
		  HibernatePopulator.setJdbcQuestionMarks(aggregator, toRet);
		  return toRet;
	}
}

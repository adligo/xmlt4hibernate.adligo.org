package org.adligo.xml.parsers.template.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.models.params.client.ValueTypes;
import org.adligo.xml.parsers.template.jdbc.QueryParameterAggregator;
import org.hibernate.Query;

public class HibernatePopulator {
	private static final Log log = LogFactory.getLog(HibernatePopulator.class);
	
	public static void setJdbcQuestionMarks(QueryParameterAggregator agg,Query stmt) throws SQLException {
		for (int i = 0; i < agg.size(); i++) {
			Object value = agg.getValue(i);
			short type = (Short) agg.getType(i);
			try {
				switch (type) {
					case ValueTypes.STRING:
						stmt.setString(i, (String) value); 
						break;
					case ValueTypes.INTEGER:
						stmt.setInteger(i, (Integer) value); 
						break;
					case ValueTypes.DOUBLE:
						stmt.setDouble(i, (Double) value); 
						break;
					case ValueTypes.LONG:
						stmt.setLong(i, (Long) value); 
						break;
					case ValueTypes.SHORT:
						stmt.setShort(i, (Short) value); 
						break;
					case ValueTypes.FLOAT:
						stmt.setFloat(i, (Float) value); 
						break;
					case ValueTypes.DATE:
						stmt.setDate(i, new java.sql.Date(((Date) value).getTime())); 
						break;
					case ValueTypes.BOOLEAN:
						stmt.setBoolean(i, (Boolean) value); 
						break;
					case ValueTypes.BIG_DECIMAL:
						stmt.setBigDecimal(i, (BigDecimal) value); 
						break;
					default:
						throw new SQLException("Unknown type " + type +
								" for paramter " + i + " value = " + value);
				}
			} catch (SQLException ex) {
				log.error("SQLException setting paramter " + i + " a " +
						type + " with content " + value);
				throw ex;
			}
		}
	}
}

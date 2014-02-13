package org.adligo.xml.parsers.template_hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.models.params.shared.ValueType;
import org.adligo.models.params.shared.ValueTypes;
import org.adligo.xml.parsers.template.jdbc.QueryParameterAggregator;
import org.hibernate.Query;

public class HibernatePopulator {
	private static final Log log = LogFactory.getLog(HibernatePopulator.class);
	
	public static void setJdbcQuestionMarks(QueryParameterAggregator agg,Query stmt) throws SQLException {
		for (int i = 0; i < agg.size(); i++) {
			Object value = agg.getValue(i);
			ValueType vt = agg.getType(i);
			short type = vt.getId();
			try {
				switch (type) {
					case ValueTypes.STRING_ID:
						stmt.setString(i, (String) value); 
						break;
					case ValueTypes.INTEGER_ID:
						stmt.setInteger(i, (Integer) value); 
						break;
					case ValueTypes.DOUBLE_ID:
						stmt.setDouble(i, (Double) value); 
						break;
					case ValueTypes.LONG_ID:
						stmt.setLong(i, (Long) value); 
						break;
					case ValueTypes.SHORT_ID:
						stmt.setShort(i, (Short) value); 
						break;
					case ValueTypes.FLOAT_ID:
						stmt.setFloat(i, (Float) value); 
						break;
					case ValueTypes.DATE_ID:
						stmt.setDate(i, new java.sql.Date(((Date) value).getTime())); 
						break;
					case ValueTypes.BOOLEAN_ID:
						stmt.setBoolean(i, (Boolean) value); 
						break;
					case ValueTypes.BIG_DECIMAL_ID:
						stmt.setBigDecimal(i, new BigDecimal((String) value)); 
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

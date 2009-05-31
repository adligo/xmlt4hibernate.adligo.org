package org.adligo.xml.parsers.template.hibernate;

import org.adligo.xml.parsers.template.jdbc.InjectionSafeEngineInput;
import org.hibernate.Session;

public class HibernateEngineInput extends InjectionSafeEngineInput {
	private Session session;

	protected void validate(Class<?> clz) {
		super.validate(clz);
		if (session == null) {
			throw new NullPointerException(clz.getName() + " needs a "+
					" session.");
		}
	}
	
	public boolean validate() {
		validate(getClass());
		return true;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}

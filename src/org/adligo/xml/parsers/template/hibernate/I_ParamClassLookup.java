package org.adligo.xml.parsers.template.hibernate;

import org.adligo.models.params.client.I_TemplateParams;

/**
 * the idea behind this class is that it will
 * look up the Class you want based on the Params (I_TemplateParams)
 * 
 * @author scott
 *
 */
public interface I_ParamClassLookup {
	public Class<?> lookup(I_TemplateParams params);
}

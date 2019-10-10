/*******************************************************************************
 * /*******************************************************************************
 * * Copyright: 2019 digiBlitz Foundation
 * * 
 * * License: digiBlitz Public License 1.0 (DPL) 
 * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 * * 
 * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 * * 
 * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 * * 
 * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.intuit.developer.sampleapp.crud.helper;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.intuit.ipp.data.Class;
import com.intuit.ipp.data.ReferenceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

/**
 * @author dderose
 *
 */
public final class ClassHelper {

	private ClassHelper() {
		
	}

	public static Class getClassFields() throws FMSException {

		Class classObj = new Class();
		classObj.setName("Class_name_" + RandomStringUtils.randomAlphanumeric(5));
		classObj.setSubClass(false);
		classObj.setFullyQualifiedName("Class_name_" + RandomStringUtils.randomAlphanumeric(5));
		return classObj;
	}

	public static Class getClass(DataService service) throws FMSException {
		List<Class> classes = (List<Class>) service.findAll(new Class());
		if (!classes.isEmpty()) {
			return classes.get(0);
		}
		return createClass(service);
	}

	private static Class createClass(DataService service) throws FMSException {
		return service.add(getClassFields());
	}

	public static ReferenceType getClassRef(Class classObj) {
		ReferenceType classRef = new ReferenceType();
		classRef.setName(classObj.getName());
		classRef.setValue(classObj.getId());
		return classRef;
	}

}

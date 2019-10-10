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
package com.intuit.developer.sampleapp.crud.entities.employee;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.intuit.developer.sampleapp.crud.helper.EmployeeHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Employee;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to update employee
 * 1. Sparse update with limited fields
 * 2. Full update with all fields
 * Note: We'll create an entity first and then update the same
 * 
 * @author dderose
 *
 */
public class EmployeeUpdate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			updateEmployee();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void updateEmployee() throws FMSException, ParseException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// create employee
			Employee employee = EmployeeHelper.getEmployeeWithMandatoryFields();
			Employee addEmployee = service.add(employee);
			LOG.info("Employee added : " + addEmployee.getId() + " name ::: " + addEmployee.getDisplayName());
			
			// sparse update employee 
			addEmployee.setSparse(true);
			addEmployee.setDisplayName(RandomStringUtils.randomAlphanumeric(6));
			Employee savedEmployee = service.update(addEmployee);
			LOG.info("Employee sparse updated: " + savedEmployee.getId() + " name ::: " + savedEmployee.getDisplayName() );
			
			// update employee with all fields
			addEmployee = service.findById(savedEmployee);
			Employee updatedEmployee = EmployeeHelper.getEmployeeWithAllFields();
			updatedEmployee.setId(addEmployee.getId());
			updatedEmployee.setSyncToken(addEmployee.getSyncToken());
		    savedEmployee = service.update(updatedEmployee);
		    LOG.info("Employee updated with all fields : " + savedEmployee.getId() + " name ::: " + savedEmployee.getDisplayName());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}	
		}
		
	}

}

/*******************************************************************************
 * Copyright: 2018 Menschforce Foundation www.menschforce.org/copyright/
 * 
 * License: digiBlitz Public License 1.0 (DPL) administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 * 
 * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 * 
 * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 * 
 * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.intuit.developer.sampleapp.crud.entities.department;

import java.util.List;

import com.intuit.developer.sampleapp.crud.helper.DepartmentHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Department;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to delete department data
 * Name-list resources can only be soft deleted meaning, marked as inactive
 * Note: We'll create an entity first and then delete the same
 * 
 * @author dderose
 *
 */
public class DepartmentDelete {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			deleteDepartment();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void deleteDepartment() throws FMSException {
		
		try {
			DataService service = DataServiceFactory.getDataService();
			
			// create department
			Department department = DepartmentHelper.getDepartmentFields();
			Department addDepartment = service.add(department);
			LOG.info("Department added : " + addDepartment.getId() + " active flag ::: " + addDepartment.isActive());
			
			// set active flag as false to soft delete
			addDepartment.setActive(false);
			Department deletedDepartment = service.update(addDepartment);		
			LOG.info("Department deleted : " + deletedDepartment.getId() + " active flag ::: " + deletedDepartment.isActive());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}
		}
		
	}
}

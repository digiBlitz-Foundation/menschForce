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
package com.intuit.developer.sampleapp.crud.entities.timeactivity;

import java.text.ParseException;
import java.util.List;

import com.intuit.developer.sampleapp.crud.helper.TimeActivityHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.data.TimeActivity;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.DateUtils;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to update timeactivity
 * 1. Sparse update with limited fields
 * 2. Full update with all fields
 * Note: We'll create an entity first and then update the same
 * 
 * @author dderose
 *
 */
public class TimeActivityUpdate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			updateTimeActivity();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void updateTimeActivity() throws FMSException, ParseException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// create timeactivity
			TimeActivity timeactivity = TimeActivityHelper.getTimeActivityFields(service);
			TimeActivity addTimeActivity = service.add(timeactivity);
			LOG.info("TimeActivity added : " + addTimeActivity.getId() + " txn date ::: " + addTimeActivity.getTxnDate());
			
			// sparse update timeactivity 
			addTimeActivity.setSparse(true);
			addTimeActivity.setTxnDate(DateUtils.getDateWithNextDays(2));
			TimeActivity savedTimeActivity = service.update(addTimeActivity);
			LOG.info("TimeActivity sparse updated: " + savedTimeActivity.getId() + " txn date ::: " + savedTimeActivity.getTxnDate() );
			
			// update timeactivity with all fields
			addTimeActivity = service.findById(savedTimeActivity);
			TimeActivity updatedTimeActivity = TimeActivityHelper.getTimeActivityFields(service);
			updatedTimeActivity.setId(addTimeActivity.getId());
			updatedTimeActivity.setSyncToken(addTimeActivity.getSyncToken());
		    savedTimeActivity = service.update(updatedTimeActivity);
		    LOG.info("TimeActivity updated with all fields : " + savedTimeActivity.getId() + " txn date ::: " + savedTimeActivity.getTxnDate());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}
		}
		
	}

}

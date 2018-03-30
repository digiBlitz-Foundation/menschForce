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
package com.intuit.developer.sampleapp.crud.entities;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.intuit.developer.sampleapp.crud.helper.ItemHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.Item;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to update item
 * 1. Sparse update with limited fields
 * 2. Full update with all fields
 * Note: We'll create an entity first and then update the same
 * 
 * @author dderose
 *
 */
public class ItemUpdate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main(String[] args) {
		try {
			updateItem();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void updateItem() throws FMSException, ParseException {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// create item
			Item item = ItemHelper.getItemFields(service);
			Item addItem = service.add(item);
			LOG.info("Item added : " + addItem.getId() + " name ::: " + addItem.getName());
			
			// sparse update item 
			addItem.setSparse(true);
			addItem.setName(RandomStringUtils.randomAlphanumeric(6));
			Item savedItem = service.update(addItem);
			LOG.info("Item sparse updated: " + savedItem.getId() + " name ::: " + savedItem.getName() );
			
			// update item with all fields
			addItem = service.findById(savedItem);
			Item updatedItem = ItemHelper.getItemFields(service);
			updatedItem.setId(addItem.getId());
			updatedItem.setSyncToken(addItem.getSyncToken());
		    savedItem = service.update(updatedItem);
		    LOG.info("Item updated with all fields : " + savedItem.getId() + " name ::: " + savedItem.getName());
			
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			for(Error item : list){
				LOG.error("Error while deleting entity :: " + item.getMessage());
				//System.out.println(item.getMessage());
			}
		}
		
	}

}

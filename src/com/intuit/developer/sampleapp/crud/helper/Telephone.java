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
package com.intuit.developer.sampleapp.crud.helper;

import com.intuit.ipp.data.TelephoneNumber;

/**
 * @author dderose
 *
 */
public final class Telephone {
	
	private Telephone() {
		
	}

	public static TelephoneNumber getPrimaryPhone() {
		TelephoneNumber primaryNum = new TelephoneNumber();
		primaryNum.setFreeFormNumber("(650)111-1111");
		primaryNum.setDefault(true);
		primaryNum.setTag("Business");
		return primaryNum;
	}
	
	public static TelephoneNumber getAlternatePhone() {
		TelephoneNumber alternativeNum = new TelephoneNumber();
		alternativeNum.setFreeFormNumber("(650)111-2222");
		alternativeNum.setDefault(false);
		alternativeNum.setTag("Business");
		return alternativeNum;
	}
	
	public static TelephoneNumber getMobilePhone() {
		TelephoneNumber mobile = new TelephoneNumber();
		mobile.setFreeFormNumber("(650)111-3333");
		mobile.setDefault(false);
		mobile.setTag("Home");
		return mobile;
	}
	
	public static TelephoneNumber getFax() {
		TelephoneNumber fax = new TelephoneNumber();
		fax.setFreeFormNumber("(650)111-1111");
		fax.setDefault(false);
		fax.setTag("Business");
		return fax;
	}

}

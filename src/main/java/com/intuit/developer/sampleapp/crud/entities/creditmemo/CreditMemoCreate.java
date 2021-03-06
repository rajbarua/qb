package com.intuit.developer.sampleapp.crud.entities.creditmemo;

import java.util.List;

import com.intuit.developer.sampleapp.crud.helper.CreditMemoHelper;
import com.intuit.developer.sampleapp.crud.qbo.DataServiceFactory;
import com.intuit.ipp.data.CreditMemo;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create creditmemo
 * 
 * @author dderose
 *
 */
public class CreditMemoCreate {

	private static final org.slf4j.Logger LOG = Logger.getLogger();
	
	public static void main_(String[] args) {
		try {
			createCreditMemo();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createCreditMemo() throws Exception {
		
		try {
			
			DataService service = DataServiceFactory.getDataService();
			
			// add creditmemo
			CreditMemo creditmemo = CreditMemoHelper.getCreditMemoFields(service);
			CreditMemo savedCreditMemo = service.add(creditmemo);
			LOG.info("CreditMemo created: " + savedCreditMemo.getId());
						
		} catch (FMSException e) {
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}

}

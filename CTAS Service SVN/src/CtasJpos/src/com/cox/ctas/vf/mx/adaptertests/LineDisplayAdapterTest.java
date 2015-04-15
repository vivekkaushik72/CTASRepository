package com.cox.ctas.vf.mx.adaptertests;

import com.cox.ctas.service.webservice.LineItem;
import com.cox.ctas.service.webservice.LineItemRequest;
import com.cox.ctas.service.webservice.TenderRequest;
import com.cox.ctas.vf.mx.adapters.Mx8xxFormsAdapter;
import com.cox.ctas.vf.mx.adapters.Mx8xxLineDisplayAdapter;
import com.verifone.javapos.services.VFForm;

public class LineDisplayAdapterTest {

	private static final String CLASSNAME = "CLASSNAME: CoxVfLineDisplayTest: ";
	private static Mx8xxFormsAdapter lid = new Mx8xxFormsAdapter();
	private static LineItemRequest lir= new LineItemRequest();
	private static LineItem li = new LineItem();
	private static TenderRequest tr = new TenderRequest();
	
	
	
	public static void main (String [] args){
		
		lir.setDiscount(0.00);
		lir.setQuantity(1);
		lir.setTax(1.00);
		lir.setTotal(1.00);
		
		li.setDescription("Test");
		li.setPrice(1.00);
		li.setQuantity(1);
		li.setTaxable("T");
		
		LineItem [] liArray = {li};
		lir.setItems(liArray);
		
		tr.setTotal(10.00);
		
		
		
		lid.open();
		lid.claim();
		lid.deviceEnabled(true);
		lid.clearScreen();
		lid.initForm("ITEMS");
		lid.addItems(lir);
		lid.showForm("ITEMS");
		lid.clearScreen();
		lid.initForm("TENDERS");
		lid.addTender(tr);
		lid.showForm("TENDERS");
		
		
	}
	
	
	
	
}

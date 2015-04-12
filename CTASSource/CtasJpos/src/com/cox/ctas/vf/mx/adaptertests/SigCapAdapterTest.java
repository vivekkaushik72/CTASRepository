
package com.cox.ctas.vf.mx.adaptertests;

import com.cox.ctas.vf.mx.adapters.Mx8xxSigCapAdapter;

public class SigCapAdapterTest {

	public static void main (String [] arg){
		Mx8xxSigCapAdapter sigCap = new Mx8xxSigCapAdapter();
		
		
		sigCap.openDevice();
		sigCap.claim();
		sigCap.deviceEnabled(true);
	
		sigCap.OnBeginCapture("SIGNATUR");
		sigCap.OnEndCapture();
	}
}

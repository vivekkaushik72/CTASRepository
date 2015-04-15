package com.cox.ctas.vf.mx.utility;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.cox.wireless.security.Encrypt;
import com.cox.wireless.security.SecurityException;

public class TestSecurityKey {

	
		public static void main(String[] args) {
			try {
				//String encryptedData = Encrypt.encrypt("5454545454545454");
				
				TestSecurityKey test = new TestSecurityKey();
				
				String encryptedData = test.encryptData("5454545454545454");
				System.out.println("Credit Card Number " + encryptedData);

				encryptedData = Encrypt.encrypt("11");
				System.out.println("Month " + encryptedData);

				encryptedData = Encrypt.encrypt("2011");
				System.out.println("Year " + encryptedData);

				Encrypt.refreshKey();

				HashMap clearMap = new HashMap();
				clearMap.put("CREDIT_CARD", "4567123456789098");
				clearMap.put("EXPIRATION_YEAR", Integer.valueOf(2011));
				clearMap.put("EXPIRATION_MONTH", Integer.valueOf(12));
				clearMap.put("MyOwnKey", "Value to be encrypted");

				HashMap encryptedMap = Encrypt.encrypt(clearMap);

				Collection values = encryptedMap.values();
				Iterator it = values.iterator();
				while (it.hasNext()) {
					System.out.println("encrypted value is " + it.next());
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		public String encryptData(String data)
		{
			try {
				return Encrypt.encrypt(data);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
}
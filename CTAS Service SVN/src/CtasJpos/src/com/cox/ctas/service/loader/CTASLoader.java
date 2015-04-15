/************************************************************
 * Copyright(c) 2015 Cox Communications. All Rights Reserved.
 * 
 * Rev 1.0 Feb 24, 2015 - initial revision 
 ************************************************************/
package com.cox.ctas.service.loader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cox.ctas.service.configuration.CTASProperties;

/**
 * Startup class for loading the CTAS Service. Starts the Jetty Server 
 * and published CTAS Web Services components. 
 */
public class CTASLoader {
	
	/**required serial UID*/
	private static final long serialVersionUID = 7945500401345015181L;
	/**logger for the class*/
	private static Logger logger = Logger.getLogger(CTASLoader.class);
	/**holds an instance of the Jetty server*/
	private Server jServer;
	/**holds an instance of the CTAS properties that holds configurable values*/
	private CTASProperties properties;

	
	/**
	 * default constructor
	 */
	CTASLoader(){
		loadCTASProperties();
	}
		
	/**
	 * main method and the starting point of CTAS Application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// REVISIT
		// Future Work
		// if (args.length == 0)
		// {
		// System.out.println("NO CONFIGURATION FILE EXIST FOR CTAS SERVICE");
		// System.exit(-1);
		// }

		CTASLoader loader = new CTASLoader();
		loader.configureLog4j();
		
		logger.info("****************** Starting CTAS Service ******************");
		int serverPort = loader.getProperties().getJserverPort();
		loader.startJettyServer(serverPort);
		logger.info("****************** Jetty Server Started ******************");
	}

	/**
	 * configures Log4j using the default configuration file
	 */
	public void configureLog4j() {
		DOMConfigurator.configure(ClassLoader.getSystemResource("config/log4j/log4j.xml"));
	}

	/**
	 * loads the CTAS properties from designated configuration file
	 *
	 */
	public void loadCTASProperties() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/context/ApplicationContext.xml");      
        logger.info("Getting CTAS Properties");
        properties = (CTASProperties) applicationContext.getBean("CTASProperties");
	}
	
	/**
	 * starts the Jetty server using the resources specified
	 * 
	 * @param port 	server port 
	 */
	public void startJettyServer(int port){
		  if(port > 0){
			  try 
			  {
				jServer = new Server(port);
				WebAppContext context = new WebAppContext();
				context.setContextPath("/" + "CTASWebService");
				context.setWar(ClassLoader.getSystemResource("war/CTASWebService.war").toString());
				context.setServer(jServer);
				jServer.setHandler(context);
				jServer.start();
			 } 
			 catch (Exception e) {
				 logger.error("Exception occurred while starting Jetty Server ", e);
			}
		  }
		  else{
			  logger.info("Wrong port specified");
		  }
	}
	
	/**
	 * gets the value held in properties
	 * 
	 * @return	an instance of CTASProperties class
	 */
	public CTASProperties getProperties() {
		return properties;
	}
	
	/**
	 * sets the value of properties
	 * 
	 * @param properties
	 */
	public void setProperties(CTASProperties properties) {
		this.properties = properties;
	}
}

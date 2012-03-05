/**
 * 
 */
package com.trendrr.cheshire.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.codec.http.HttpResponse;

import com.trendrr.cheshire.CheshireController;
import com.trendrr.strest.StrestException;
import com.trendrr.strest.server.StrestController;
import com.trendrr.strest.server.StrestControllerFilter;

/**
 * @author Dustin Norlander
 * @created Mar 25, 2011
 * 
 */
public abstract class CheshireFilter implements StrestControllerFilter {

	protected Log log = LogFactory.getLog(CheshireFilter.class);

	public CheshireFilter() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.trendrr.strest.server.StrestControllerFilter#before(com.trendrr.strest.server.StrestController)
	 */
	@Override
	public void before(StrestController controller) throws StrestException {
		if (!(controller instanceof CheshireController)) {
			return;
		}
		CheshireController cont = (CheshireController)controller;
		this.before(cont);
	}

	
	public abstract void before(CheshireController controller) throws StrestException;
	
	/* (non-Javadoc)
	 * @see com.trendrr.strest.server.StrestControllerFilter#after(com.trendrr.strest.server.StrestController)
	 */
	@Override
	public void after(StrestController controller) throws StrestException {
		if (!(controller instanceof CheshireController)) {
			return;
		}
		CheshireController cont = (CheshireController)controller;
		this.after(cont);

	}
	
	public abstract void after(CheshireController controller) throws StrestException;
	
	
	@Override
	public void error(StrestController controller, HttpResponse response,
			Exception exception) {
		if (controller == null) {
			this.error(null, response, exception);
			return;
		}
		
		if (!(controller instanceof CheshireController)) {
			return;
		}
		CheshireController cont = (CheshireController)controller;
		this.error(cont, response, exception);
		
	}
	
	public abstract void error(CheshireController controller, HttpResponse response,
			Exception exception);
}

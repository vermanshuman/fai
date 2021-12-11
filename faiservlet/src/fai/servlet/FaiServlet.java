package fai.servlet;

import javax.servlet.*;
import fai.services.util.Environment;


public class FaiServlet extends it.swdes.servlet.GenericServlet {


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Environment.setEnvironment(getContextResources());
	}	

	public void destroy() {
		super.destroy();
	}
}

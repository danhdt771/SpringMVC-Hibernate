package asherdinh.springmvc_hibernate.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Create webApplicationContext
		AnnotationConfigWebApplicationContext webApplicationContext =
				new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(AppConfig.class);
		
		// Manage the lifecycle of the webApplicationContext
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        
		//Create a dispatcher servlet object
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
		
		//register dispatcher servlet with servlet context
		ServletRegistration.Dynamic myCustomDispatcherServlet = 
				servletContext.addServlet("myDispatcherServlet", dispatcherServlet);
		myCustomDispatcherServlet.setLoadOnStartup(1);
		myCustomDispatcherServlet.addMapping("/");
	}

}

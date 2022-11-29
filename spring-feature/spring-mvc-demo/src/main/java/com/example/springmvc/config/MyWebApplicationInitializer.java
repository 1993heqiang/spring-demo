package com.example.springmvc.config;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * com.example.springmvc.config.MyWebApplicationInitializer
 *
 * @author HeQiang
 * @since 2022/11/12
 **/
//@Component
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext =
				new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);

		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext =
				new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherConfig.class);

		// Register and map the dispatcher servlet
		// 通过构造器方式，关联applicationContext
		// You'll find that DispatcherServlet, FrameworkServlet, ContextLoaderListener and DelegatingFilterProxy all now support constructor arguments
		DispatcherServlet servlet = new DispatcherServlet(dispatcherContext);
		ServletRegistration.Dynamic dispatcher =
				servletContext.addServlet("dispatcher", servlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}

class AppConfig {

}

class DispatcherConfig {

}


//@Component
class MyServletContextInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		ServletRegistration.Dynamic dispatcher =
				servletContext.addServlet("test", new SimpleServlet());
		dispatcher.setLoadOnStartup(2);
		dispatcher.addMapping("/hehe");
	}

	private static class SimpleServlet extends GenericServlet {

		@Override
		public void service(ServletRequest req, ServletResponse res)
				throws ServletException, IOException {
			PrintWriter writer = res.getWriter();
			writer.write("hehe");
			writer.flush();
		}
	}
}



/*class MyWebAppInitializer2 extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[0];
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
}*/

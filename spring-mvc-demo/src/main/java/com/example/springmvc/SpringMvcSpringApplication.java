package com.example.springmvc;

import java.util.List;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;

/**
 * Main
 *
 * @author HeQiang
 * @since 2022/11/12
 **/
@SpringBootApplication
public class SpringMvcSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcSpringApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(ApplicationContext context) {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) throws Exception {
				String dispatcherName = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME;
				DispatcherServlet dispatcherServlet = context.getBean(dispatcherName, DispatcherServlet.class);
				List<HandlerMapping> mappings = dispatcherServlet.getHandlerMappings();
				BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
			}
		};
	}

}

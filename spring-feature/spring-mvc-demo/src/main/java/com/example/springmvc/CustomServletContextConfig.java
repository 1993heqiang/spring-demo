package com.example.springmvc;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * CustomServletContextConfig
 *
 * @author HeQiang
 * @since 2022/11/16
 **/
@Component
public class CustomServletContextConfig implements ApplicationListener<ApplicationStartedEvent> {

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		ConfigurableApplicationContext applicationContext = event.getApplicationContext();
		if (applicationContext instanceof ConfigurableWebApplicationContext webApplicationContext) {
			ServletContext servletContext = webApplicationContext.getServletContext();
			Assert.notNull(servletContext, "servletContext is null.");

			// Set application scope model
			Map<String, String> applicationScopeModel = new HashMap<>();
			applicationScopeModel.put("name", "Tom");
			servletContext.setAttribute("scopedTarget.user", applicationScopeModel);
		}
	}
}

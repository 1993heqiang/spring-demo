package com.example.springmvc.config;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

/**
 * HandlerMappingConfiguration
 *
 * @author HeQiang
 * @since 2022/11/12
 **/
//@Configuration
public class HandlerMappingConfiguration {

	/**
	 * {@link BeanNameUrlHandlerMapping}
	 *
	 * @return Person
	 */
	@Bean(name = {"person", "/person"})
	public Person person() {
		return new Person("Tom", "12");
	}

	/**
	 * {@link SimpleUrlHandlerMapping}
	 *
	 * @return SimpleUrlHandlerMapping
	 */
	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		Map<String, Object> urlMap = new HashMap<>();
		urlMap.put("welcome", "ticketController");
		return new SimpleUrlHandlerMapping(urlMap);
	}


}

//@Component
class TicketController {

	public String welcome() {
		return "welcome";
	}

}

//@Component
class TicketControllerHandleAdapter implements HandlerAdapter {

	@Override
	public boolean supports(@NonNull Object handler) {
		return handler instanceof TicketController;
	}

	@Override
	public ModelAndView handle(@NonNull HttpServletRequest request, HttpServletResponse response,
			@NonNull Object handler) throws Exception {
		PrintWriter writer = response.getWriter();
		String uri = request.getRequestURI();
		String[] split = uri.split("/");
		Method method = ReflectionUtils.findMethod(TicketController.class, split[split.length - 1]);
		String message = method != null ? method.invoke(handler).toString() : "url is error.";
		writer.write(message);
		writer.flush();
		return null;
	}

	@Override
	public long getLastModified(@NonNull HttpServletRequest request, @NonNull Object handler) {
		return -1;
	}
}

record Person(String name, String age) {

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age='" + age + '\'' +
				'}';
	}
}

//@Component
class PersonHandleAdapter implements HandlerAdapter {

	@Override
	public boolean supports(@NonNull Object handler) {
		return handler instanceof Person;
	}

	@Override
	public ModelAndView handle(@NonNull HttpServletRequest request, HttpServletResponse response,
			@NonNull Object handler) throws Exception {
		PrintWriter writer = response.getWriter();
		writer.write(handler.toString());
		writer.flush();
		return null;
	}

	@Override
	public long getLastModified(@NonNull HttpServletRequest request, @NonNull Object handler) {
		return -1;
	}
}

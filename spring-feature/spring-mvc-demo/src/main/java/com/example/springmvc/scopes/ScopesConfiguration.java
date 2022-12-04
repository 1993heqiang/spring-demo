package com.example.springmvc.scopes;

import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ScopesConfiguration
 *
 * @author HeQiang
 * @since 2022/11/29
 **/
@Configuration(proxyBeanMethods = false)
public class ScopesConfiguration {

	@Bean
	CustomScopeConfigurer configCustomScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("custom", new CustomScope());
		return configurer;
	}

}

class CustomScope implements Scope {
	public static final Log LOGGER = LogFactory.getLog(CustomScope.class);

	private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();


	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (cache.contains(name)) {
			return cache.get(name);
		}
		Object object = objectFactory.getObject();
		cache.put(name, object);
		return cache.get(name);
	}

	@Override
	public Object remove(String name) {
		return cache.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(name + " is already destroyed.");
		}
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
}



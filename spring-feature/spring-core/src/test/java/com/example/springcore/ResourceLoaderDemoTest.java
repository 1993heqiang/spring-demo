package com.example.springcore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Properties;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * ResourceLoaderDemoTest
 *
 * @author HeQiang
 * @since 2022/11/29
 **/
class ResourceLoaderDemoTest {

	private static ResourceLoader resourceLoader;

	@BeforeAll
	static void beforeClass() {
		resourceLoader = new DefaultResourceLoader();
	}

	@Test
	void loadPropertiesTest() {
		Resource resource = resourceLoader.getResource(
				"classpath:com/example/springcore/example.properties");
		PropertyResolver propertyResolver = createPropertyResolver(resource);
		assertEquals("bar", propertyResolver.getProperty("foo"));
		assertEquals("hello bar", propertyResolver.getProperty("hello.foo"));
	}

	@Test
	void loadXmlPropertiesTest() {
		Resource resource = resourceLoader.getResource(
				"classpath:com/example/springcore/example.xml");
		PropertyResolver propertyResolver = createPropertyResolver(resource);
		assertEquals("bar", propertyResolver.getProperty("foo"));
	}


	@SneakyThrows
	private PropertyResolver createPropertyResolver(Resource resource) {
		EncodedResource encodedResource = new EncodedResource(resource);
		Properties properties = PropertiesLoaderUtils.loadProperties(encodedResource);
		PropertiesPropertySource propertySource = new PropertiesPropertySource("test", properties);
		MutablePropertySources propertySources = new MutablePropertySources();
		propertySources.addFirst(propertySource);
		return new PropertySourcesPropertyResolver(propertySources);
	}
}
package com.example.springmvc.theme;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

/**
 * ThemeConfig
 *
 * @author HeQiang
 * @since 2022/11/16
 **/
@Configuration(proxyBeanMethods = false)
public class ThemeConfig implements WebMvcConfigurer {

	@Bean
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
		themeSource.setBasenamePrefix("META-INF/theme/");
		return themeSource;
	}

	@Bean
	public ThemeResolver themeResolver() {
		SessionThemeResolver themeResolver = new SessionThemeResolver();
		themeResolver.setDefaultThemeName("default");
		return themeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ThemeChangeInterceptor());
	}
}

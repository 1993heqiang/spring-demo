package com.example.springmvc.security;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.info.InfoEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest.EndpointRequestMatcher;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest.StaticResourceRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ClassUtils;

/**
 * CustomWebSecurityAutoConfiguration
 *
 * @author HeQiang
 * @since 2022/11/17
 **/
@AutoConfiguration(before = ManagementWebSecurityAutoConfiguration.class,
		after = {HealthEndpointAutoConfiguration.class, InfoEndpointAutoConfiguration.class,
				WebEndpointAutoConfiguration.class, OAuth2ClientAutoConfiguration.class,
				OAuth2ResourceServerAutoConfiguration.class,
				Saml2RelyingPartyAutoConfiguration.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnDefaultWebSecurity
public class CustomWebSecurityAutoConfiguration {

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain customWebSecurityAutoConfiguration(HttpSecurity http) throws Exception {
		http.authorizeRequests(requests -> {
			requests.requestMatchers(createRequestMatcher()).permitAll();
			requests.anyRequest().authenticated();
		});
		if (ClassUtils.isPresent("org.springframework.web.servlet.DispatcherServlet", null)) {
			http.cors();
		}
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	public RequestMatcher createRequestMatcher() {
		return request -> {
			EndpointRequestMatcher requestMatcher = EndpointRequest.toAnyEndpoint();
			boolean endpointMatch = requestMatcher.matches(request);
			StaticResourceRequest resourceRequest = PathRequest.toStaticResources();
			StaticResourceRequestMatcher staticResourceRequestMatcher = resourceRequest.atCommonLocations();
			boolean staticResourceMatch = staticResourceRequestMatcher.matches(request);
			return endpointMatch || staticResourceMatch;
		};
	}
}

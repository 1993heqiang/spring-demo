package com.example.springmvc.scopes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.annotation.UserConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ScopesConfigurationTest
 *
 * @author HeQiang
 * @since 2022/11/29
 **/
class ScopesConfigurationTest {

	@Test
	void customScopeTest() {
		new ApplicationContextRunner().withConfiguration(UserConfigurations.of(ScopesConfiguration.class))
				.withBean(UserScopeComponent.class)
				.run(context -> {
					UserScopeComponent userScopeComponent = context.getBean(UserScopeComponent.class);
					assertNotNull(userScopeComponent);
					BeanDefinition beanDefinition = context.getBeanFactory()
							.getBeanDefinition("userScopeComponent");
					assertEquals("custom", beanDefinition.getScope());
				});
	}

}

@Scope("custom")
@Component
class UserScopeComponent {

}
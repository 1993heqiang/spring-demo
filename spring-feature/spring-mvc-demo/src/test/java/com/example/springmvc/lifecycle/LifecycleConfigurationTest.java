package com.example.springmvc.lifecycle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.annotation.UserConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 * LifecycleConfigurationTest
 *
 * @author HeQiang
 * @since 2022/11/29
 **/
@ExtendWith(OutputCaptureExtension.class)
class LifecycleConfigurationTest {

	@Test
	void lifecycleTest(CapturedOutput output) {
		new ApplicationContextRunner()
				.withConfiguration(UserConfigurations.of(LifecycleConfiguration.class))
				.run(context -> {
					assertThat(output.getAll()).contains("afterPropertiesSet");
					assertThat(output.getAll()).contains("start MyLifecycle");
				});
	}
}
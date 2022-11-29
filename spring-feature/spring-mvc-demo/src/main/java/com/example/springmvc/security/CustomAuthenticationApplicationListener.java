package com.example.springmvc.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;

/**
 * CustomAuthenticationApplicationListener
 *
 * @author HeQiang
 * @since 2022/11/18
 **/
public class CustomAuthenticationApplicationListener implements
		ApplicationListener<AbstractAuthenticationEvent> {
	private static final Log LOGGER = LogFactory.getLog(
			CustomAuthenticationApplicationListener.class);

	@Override
	public void onApplicationEvent(@NonNull AbstractAuthenticationEvent event) {
		if (event instanceof AbstractAuthenticationFailureEvent failureEvent) {
			var message = LogFormatUtils.formatValue(
					String.format("Event source: %s, msg: %s.", event.getSource(),
							failureEvent.getException()), true);
			LOGGER.warn(message);
		}
	}
}

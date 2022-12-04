package com.example.springmvc.lifecycle;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LifecycleDemo
 *
 * @author HeQiang
 * @since 2022/11/29
 **/
@Configuration(proxyBeanMethods = false)
public class LifecycleConfiguration {

	@Bean
	LifeCycleBean lifeCycleBean() {
		return new LifeCycleBean();
	}

	@Bean
	MyLifecycle myLifecycle() {
		return new MyLifecycle();
	}

}

class LifeCycleBean implements InitializingBean, DisposableBean {

	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("preDestroy");
	}


	@Override
	public void destroy() throws Exception {
		System.out.println("destroy");
	}

}

/**
 * MyLifecycle
 *
 * @see org.springframework.context.LifecycleProcessor
 */
class MyLifecycle implements SmartLifecycle {

	private final AtomicBoolean isRunning = new AtomicBoolean(false);

	@Override
	public void start() {
		System.out.println("start MyLifecycle");
		isRunning.set(true);
	}

	@Override
	public void stop() {
		System.out.println("stop MyLifecycle");
		isRunning.set(false);
	}

	@Override
	public boolean isRunning() {
		return isRunning.get();
	}
}
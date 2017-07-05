package com.nick.contentEvaluator;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nick.contentEvaluator.output.EmailOutput;
import com.nick.contentEvaluator.output.EmailParams;
import com.nick.contentEvaluator.output.OutputDestination;

@Configuration
@ComponentScan
@PropertySource("classpath:test.properties")
@EnableScheduling
public class TestAppConfig {

	private EmailOutput emailOutputMock = Mockito.mock(EmailOutput.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "emailOutput")
	public OutputDestination<EmailParams> emailOutput() {
		return emailOutputMock;
	}

}

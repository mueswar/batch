package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobFactory;

	@Autowired
	private StepBuilderFactory stepFactory;

	@Bean
	public Step hellowWorldStep() {
		return stepFactory.get("readJsonStep").<String, String>chunk(5).reader(new Reader()).processor(new Processor())
				.writer(new Writer()).build();
	}

	@Bean
	public Job helloWorldJob() {
		return jobFactory.get("helloworld").flow(hellowWorldStep()).end().build();
	}

}

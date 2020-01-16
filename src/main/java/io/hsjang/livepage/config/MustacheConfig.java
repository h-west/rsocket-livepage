package io.hsjang.livepage.config;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Collector;
import com.samskivert.mustache.Mustache.TemplateLoader;

import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MustacheConfig {

    @Bean
	public Mustache.Compiler mustacheCompiler(TemplateLoader mustacheTemplateLoader, Environment environment) {
		return Mustache.compiler().withLoader(mustacheTemplateLoader).withCollector(collector(environment)).withDelims("{^ $}");
	}

	private Collector collector(Environment environment) {
		MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
		collector.setEnvironment(environment);
		return collector;
	}
}
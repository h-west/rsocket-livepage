package io.hsjang.livepage.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer{

    @Value("classpath:/static/index.html") 
	Resource index;

	@Value("classpath:/static/page.html") 
	Resource page;
    
    @Bean
	public RouterFunction<ServerResponse> staticPages() {
        return RouterFunctions
            .route(RequestPredicates.GET("/"), req -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).bodyValue(index))
            .andRoute(RequestPredicates.GET("/page/**"), req -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).bodyValue(page));
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }
}
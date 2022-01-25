package it.fai.be.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Log4j2
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final ConfigurableEnvironment configurableEnvironment;

    @Value("${fai.front.url}")
    private String faiFrontUrl;

    @Value("${origins.allow-all}")
    private Boolean allowAll;

    @Autowired
    public WebMvcConfiguration(ConfigurableEnvironment configurableEnvironment) {
        this.configurableEnvironment = configurableEnvironment;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Allowed origins: {}", faiFrontUrl);
        if (Boolean.TRUE.equals(allowAll)) {
            registry.addMapping("/**").allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE")
                    .allowedOrigins("*")
                    .allowedHeaders("*");
        } else {
            registry.addMapping("/**").allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE")
                    .allowedOrigins(faiFrontUrl)
                    .allowedHeaders("*");
        }
    }
}

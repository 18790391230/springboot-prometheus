package com.wuyiming;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application implements ApplicationRunner, BeanFactoryAware {

    private BeanFactory beanFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return registry -> registry.config().commonTags("application", applicationName);
    }

    @Bean
    public ExecutorService executorService(final MeterRegistry registry) {
        return ExecutorServiceMetrics.monitor(registry,
                Executors.newFixedThreadPool(20),
                "my executor",
                Tags.of("key", "value"));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService bean = beanFactory.getBean(ExecutorService.class);
        bean.submit(() -> {
            System.out.println("=========================");
        });
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

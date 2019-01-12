package com.model.zhujie;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Arrays;
import java.util.Set;

public class ClassPathTransactionalScanner extends ClassPathBeanDefinitionScanner {


    public ClassPathTransactionalScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public ClassPathTransactionalScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public ClassPathTransactionalScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public ClassPathTransactionalScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No DgbSecurity Spring Componet was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        }

        return beanDefinitions;
    }
}

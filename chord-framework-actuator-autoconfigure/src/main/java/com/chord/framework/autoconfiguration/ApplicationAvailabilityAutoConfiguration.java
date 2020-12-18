package com.chord.framework.autoconfiguration;

import com.chord.framework.common.actuator.health.base.ApplicationAvailabilityBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.actuate.health.CompositeHealthIndicatorFactory;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.OrderedHealthAggregator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
@Configuration
public class ApplicationAvailabilityAutoConfiguration {

    @Bean
    public ApplicationAvailabilityBean applicationAvailability() {
        return new ApplicationAvailabilityBean();
    }

    public static HealthIndicator get(ApplicationContext applicationContext, Class<? extends HealthIndicator> healthIndicator) {
        HealthAggregator healthAggregator = getHealthAggregator(applicationContext);
        Map<String, HealthIndicator> indicators = new LinkedHashMap<>(applicationContext.getBeansOfType(healthIndicator));
        CompositeHealthIndicatorFactory factory = new CompositeHealthIndicatorFactory();
        return factory.createHealthIndicator(healthAggregator, indicators);
    }

    private static HealthAggregator getHealthAggregator(
            ApplicationContext applicationContext) {
        try {
            return applicationContext.getBean(HealthAggregator.class);
        }
        catch (NoSuchBeanDefinitionException ex) {
            return new OrderedHealthAggregator();
        }
    }

}

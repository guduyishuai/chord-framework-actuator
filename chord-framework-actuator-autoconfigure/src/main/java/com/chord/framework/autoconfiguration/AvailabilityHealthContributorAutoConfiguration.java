package com.chord.framework.autoconfiguration;

import com.chord.framework.common.actuator.health.base.ApplicationAvailability;
import com.chord.framework.common.actuator.health.liveness.LivenessStateHealthIndicator;
import com.chord.framework.common.actuator.health.readiness.ReadinessStateHealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
@Configuration
@AutoConfigureAfter(ApplicationAvailabilityAutoConfiguration.class)
public class AvailabilityHealthContributorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "livenessStateHealthIndicator")
    @ConditionalOnProperty(prefix = "management.health.livenessstate", name = "enabled", havingValue = "true")
    public LivenessStateHealthIndicator livenessStateHealthIndicator(ApplicationAvailability applicationAvailability) {
        return new LivenessStateHealthIndicator(applicationAvailability);
    }

    @Bean
    @ConditionalOnMissingBean(name = "readinessStateHealthIndicator")
    @ConditionalOnProperty(prefix = "management.health.readinessstate", name = "enabled", havingValue = "true")
    public ReadinessStateHealthIndicator readinessStateHealthIndicator(
            ApplicationAvailability applicationAvailability) {
        return new ReadinessStateHealthIndicator(applicationAvailability);
    }

}

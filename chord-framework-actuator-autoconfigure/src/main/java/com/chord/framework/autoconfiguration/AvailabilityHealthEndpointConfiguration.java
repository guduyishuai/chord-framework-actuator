package com.chord.framework.autoconfiguration;

import com.chord.framework.common.actuator.health.liveness.LivenessHealthEndpoint;
import com.chord.framework.common.actuator.health.liveness.LivenessStateHealthIndicator;
import com.chord.framework.common.actuator.health.readiness.ReadinessHealthEndpoint;
import com.chord.framework.common.actuator.health.readiness.ReadinessStateHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2020/12/15
 *
 * @author: wulinfeng
 */
@Configuration
public class AvailabilityHealthEndpointConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public LivenessHealthEndpoint livenessHealthEndpoint(ApplicationContext applicationContext) {
        return new LivenessHealthEndpoint(ApplicationAvailabilityAutoConfiguration.get(applicationContext, LivenessStateHealthIndicator.class));
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public ReadinessHealthEndpoint readinessHealthEndpoint(ApplicationContext applicationContext) {
        return new ReadinessHealthEndpoint(ApplicationAvailabilityAutoConfiguration.get(applicationContext, ReadinessStateHealthIndicator.class));
    }

}

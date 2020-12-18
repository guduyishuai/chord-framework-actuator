package com.chord.framework.autoconfiguration;

import com.chord.framework.common.actuator.health.liveness.LivenessHealthEndpoint;
import com.chord.framework.common.actuator.health.liveness.LivenessHealthEndpointWebExtension;
import com.chord.framework.common.actuator.health.liveness.LivenessStateHealthIndicator;
import com.chord.framework.common.actuator.health.readiness.ReadinessHealthEndpointWebExtension;
import com.chord.framework.common.actuator.health.readiness.ReadinessStateHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.health.HealthWebEndpointResponseMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2020/12/15
 *
 * @author: wulinfeng
 */
@Configuration
public class AvailabilityHealthWebExtensionConfiguration {

    @Configuration
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    static class ServletWebHealthConfiguration {

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        @ConditionalOnBean(LivenessHealthEndpoint.class)
        public LivenessHealthEndpointWebExtension livenessHealthEndpointWebExtension(
                ApplicationContext applicationContext,
                HealthWebEndpointResponseMapper responseMapper) {
            return new LivenessHealthEndpointWebExtension(
                    ApplicationAvailabilityAutoConfiguration.get(applicationContext, LivenessStateHealthIndicator.class),
                    responseMapper);
        }

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        @ConditionalOnBean(LivenessHealthEndpoint.class)
        public ReadinessHealthEndpointWebExtension readinessHealthEndpointWebExtension(
                ApplicationContext applicationContext,
                HealthWebEndpointResponseMapper responseMapper) {
            return new ReadinessHealthEndpointWebExtension(
                    ApplicationAvailabilityAutoConfiguration.get(applicationContext, ReadinessStateHealthIndicator.class),
                    responseMapper);
        }

    }

}

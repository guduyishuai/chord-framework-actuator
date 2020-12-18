package com.chord.framework.autoconfiguration;

import com.chord.framework.common.actuator.health.mapping.AvailabilityHealthRootPathRule;
import com.chord.framework.common.actuator.health.mapping.MappingWebEndpointRulePathMapper;
import com.chord.framework.common.actuator.health.mapping.RootPathRule;
import org.springframework.boot.actuate.autoconfigure.endpoint.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.endpoint.web.PathMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2020/12/16
 *
 * @author: wulinfeng
 */
@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(EndpointAutoConfiguration.class)
@EnableConfigurationProperties(WebEndpointProperties.class)
public class ChordWebEndpointAutoConfiguration {

    private final WebEndpointProperties properties;

    public ChordWebEndpointAutoConfiguration(WebEndpointProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public PathMapper webEndpointPathMapper(RootPathRule rootPathRule) {
        return new MappingWebEndpointRulePathMapper(rootPathRule);
    }

    @Bean
    @ConditionalOnMissingBean
    public RootPathRule webEndpointRootPathRule() {
        return new AvailabilityHealthRootPathRule(this.properties.getPathMapping());
    }


}

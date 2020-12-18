package com.chord.framework.common.actuator.health.base;

import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.health.*;
import org.springframework.util.Assert;

/**
 * Created on 2020/12/14
 *
 * @author: wulinfeng
 */
public class AvailabilityHealthEndpointSupport {

    protected HealthIndicator healthIndicator;

    /**
     * Create a new {@link HealthEndpoint} instance.
     * @param healthIndicator the health indicator
     */
    public AvailabilityHealthEndpointSupport(HealthIndicator healthIndicator) {
        Assert.notNull(healthIndicator, "HealthIndicator must not be null");
        this.healthIndicator = healthIndicator;
    }

    protected Health doHealth() {
        return this.healthIndicator.health();
    }

    protected WebEndpointResponse<Health> doGetHealth(SecurityContext securityContext, HealthWebEndpointResponseMapper responseMapper) {
        return responseMapper.map(doHealth(), securityContext);
    }

    protected WebEndpointResponse<Health> doGetHealth(SecurityContext securityContext,
                                                  ShowDetails showDetails,
                                                  HealthWebEndpointResponseMapper responseMapper) {
        return responseMapper.map(doHealth(), securityContext, showDetails);
    }

}

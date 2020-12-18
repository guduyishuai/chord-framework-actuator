package com.chord.framework.common.actuator.health.readiness;

import com.chord.framework.common.actuator.health.base.AvailabilityHealthEndpointSupport;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.health.*;

/**
 * Created on 2020/12/14
 *
 * @author: wulinfeng
 */
@EndpointWebExtension(endpoint = ReadinessHealthEndpoint.class)
public class ReadinessHealthEndpointWebExtension extends AvailabilityHealthEndpointSupport {

    private final HealthWebEndpointResponseMapper responseMapper;

    /**
     * Create a new {@link HealthEndpoint} instance.
     *
     * @param healthIndicator the health indicator
     */
    public ReadinessHealthEndpointWebExtension(HealthIndicator healthIndicator, HealthWebEndpointResponseMapper responseMapper) {
        super(healthIndicator);
        this.responseMapper = responseMapper;
    }

    @ReadOperation
    public WebEndpointResponse<Health> getHealth(SecurityContext securityContext) {
        return doGetHealth(securityContext, this.responseMapper);
    }

    public WebEndpointResponse<Health> getHealth(SecurityContext securityContext,
                                                 ShowDetails showDetails) {
        return doGetHealth(securityContext, showDetails, this.responseMapper);
    }

}

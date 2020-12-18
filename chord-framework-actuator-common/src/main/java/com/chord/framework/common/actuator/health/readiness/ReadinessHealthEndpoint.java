package com.chord.framework.common.actuator.health.readiness;

import com.chord.framework.common.actuator.health.base.ApplicationAvailabilityBean;
import com.chord.framework.common.actuator.health.base.AvailabilityHealthEndpointSupport;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * Created on 2020/12/11
 *
 * @author: wulinfeng
 */
@Endpoint(id = ApplicationAvailabilityBean.Constants.PROBE_READINESS_NAME)
public class ReadinessHealthEndpoint extends AvailabilityHealthEndpointSupport {

    /**
     * Create a new {@link HealthEndpoint} instance.
     *
     * @param healthIndicator the health indicator
     */
    public ReadinessHealthEndpoint(HealthIndicator healthIndicator) {
        super(healthIndicator);
    }

    @ReadOperation
    public Health health() {
        return doHealth();
    }
    
}

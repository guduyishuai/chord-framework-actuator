package com.chord.framework.common.actuator.health.liveness;

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
@Endpoint(id = ApplicationAvailabilityBean.Constants.PROBE_LIVENESS_NAME)
public class LivenessHealthEndpoint extends AvailabilityHealthEndpointSupport {

    /**
     * Create a new {@link HealthEndpoint} instance.
     *
     * @param healthIndicator the health indicator
     */
    public LivenessHealthEndpoint(HealthIndicator healthIndicator) {
        super(healthIndicator);
    }

    @ReadOperation
    public Health health() {
        return doHealth();
    }

}

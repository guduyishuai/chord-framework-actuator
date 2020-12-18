package com.chord.framework.common.actuator.health.readiness;

import com.chord.framework.common.actuator.health.base.ApplicationAvailability;
import com.chord.framework.common.actuator.health.base.AvailabilityState;
import com.chord.framework.common.actuator.health.base.AvailabilityStateHealthIndicator;
import org.springframework.boot.actuate.health.Status;

/**
 *
 * 应用就绪的健康指标
 *
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public class ReadinessStateHealthIndicator extends AvailabilityStateHealthIndicator {

    public ReadinessStateHealthIndicator(ApplicationAvailability availability) {
        super(availability, ReadinessState.class, (statusMappings) -> {
            statusMappings.add(ReadinessState.ACCEPTING_TRAFFIC, Status.UP);
            statusMappings.add(ReadinessState.REFUSING_TRAFFIC, Status.OUT_OF_SERVICE);
        });
    }

    @Override
    protected AvailabilityState getState(ApplicationAvailability applicationAvailability) {
        return applicationAvailability.getReadinessState();
    }

}

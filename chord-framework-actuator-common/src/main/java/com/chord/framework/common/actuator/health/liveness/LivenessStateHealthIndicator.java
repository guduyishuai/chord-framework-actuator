package com.chord.framework.common.actuator.health.liveness;

import com.chord.framework.common.actuator.health.base.ApplicationAvailability;
import com.chord.framework.common.actuator.health.base.AvailabilityState;
import com.chord.framework.common.actuator.health.base.AvailabilityStateHealthIndicator;
import org.springframework.boot.actuate.health.Status;

/**
 *
 * 应用存活的健康指标
 *
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public class LivenessStateHealthIndicator extends AvailabilityStateHealthIndicator {

    public LivenessStateHealthIndicator(ApplicationAvailability availability) {
        super(availability, LivenessState.class, (statusMappings) -> {
            statusMappings.add(LivenessState.CORRECT, Status.UP);
            statusMappings.add(LivenessState.BROKEN, Status.DOWN);
        });
    }

    @Override
    protected AvailabilityState getState(ApplicationAvailability applicationAvailability) {
        return applicationAvailability.getLivenessState();
    }

}

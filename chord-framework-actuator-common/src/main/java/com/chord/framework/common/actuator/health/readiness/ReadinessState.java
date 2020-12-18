package com.chord.framework.common.actuator.health.readiness;

import com.chord.framework.common.actuator.health.base.AvailabilityState;

/**
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public enum ReadinessState implements AvailabilityState {

    ACCEPTING_TRAFFIC, REFUSING_TRAFFIC

}

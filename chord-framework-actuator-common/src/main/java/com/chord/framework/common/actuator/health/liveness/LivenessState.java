package com.chord.framework.common.actuator.health.liveness;

import com.chord.framework.common.actuator.health.base.AvailabilityState;

/**
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public enum LivenessState implements AvailabilityState {

    CORRECT, BROKEN

}

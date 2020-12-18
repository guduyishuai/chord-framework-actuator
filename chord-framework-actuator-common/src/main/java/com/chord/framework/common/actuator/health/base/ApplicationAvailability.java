package com.chord.framework.common.actuator.health.base;

import com.chord.framework.common.actuator.health.event.AvailabilityChangeEvent;
import com.chord.framework.common.actuator.health.liveness.LivenessState;
import com.chord.framework.common.actuator.health.readiness.ReadinessState;

/**
 *
 * 应用可用状态工具类，通过事件驱动状态变化
 *
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public interface ApplicationAvailability {

    /**
     *
     * 默认状态为宕机
     *
     * @return
     */
    default LivenessState getLivenessState() {
        return getState(LivenessState.class, LivenessState.BROKEN);
    }

    /**
     *
     * 默认状态为未就绪
     *
     * @return
     */
    default ReadinessState getReadinessState() {
        return getState(ReadinessState.class, ReadinessState.REFUSING_TRAFFIC);
    }

    <S extends AvailabilityState> S getState(Class<S> stateType, S defaultState);

    <S extends AvailabilityState> S getState(Class<S> stateType);

    <S extends AvailabilityState> AvailabilityChangeEvent<S> getLastChangeEvent(Class<S> stateType);

}

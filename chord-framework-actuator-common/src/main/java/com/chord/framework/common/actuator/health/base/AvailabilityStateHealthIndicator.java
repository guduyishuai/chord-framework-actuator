package com.chord.framework.common.actuator.health.base;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.util.Assert;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * 提供应用可用状态的健康指标
 *
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public class AvailabilityStateHealthIndicator extends AbstractAvailabilityStateHealthIndicator {

    private final ApplicationAvailability applicationAvailability;

    private final Class<? extends AvailabilityState> stateType;

    private final Map<AvailabilityState, Status> statusMappings = new HashMap<>();

    public <S extends AvailabilityState> AvailabilityStateHealthIndicator(
            ApplicationAvailability applicationAvailability, Class<S> stateType,
            Consumer<StatusMappings<S>> statusMappings) {
        Assert.notNull(applicationAvailability, "ApplicationAvailability must not be null");
        Assert.notNull(stateType, "StateType must not be null");
        Assert.notNull(statusMappings, "StatusMappings must not be null");
        this.applicationAvailability = applicationAvailability;
        this.stateType = stateType;
        statusMappings.accept(this.statusMappings::put);
        assertAllEnumsMapped(stateType);
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        AvailabilityState state = getState(this.applicationAvailability);
        Status status = this.statusMappings.get(state);
        if (status == null) {
            status = this.statusMappings.get(null);
        }
        Assert.state(status != null, () -> "No mapping provided for " + state);
        builder.status(status);
    }

    protected AvailabilityState getState(ApplicationAvailability applicationAvailability) {
        return applicationAvailability.getState(this.stateType);
    }

    private <S extends AvailabilityState> void assertAllEnumsMapped(Class<S> stateType) {
        if (!this.statusMappings.containsKey(null) && Enum.class.isAssignableFrom(stateType)) {
            EnumSet elements = EnumSet.allOf((Class) stateType);
            for (Object element : elements) {
                Assert.isTrue(this.statusMappings.containsKey(element),
                        () -> "StatusMappings does not include " + element);
            }
        }
    }

    /**
     *
     * 用来管理AvailabilityState和State的对应关系
     *
     * @param <S>
     */
    public interface StatusMappings<S extends AvailabilityState> {

        /**
         *
         * 默认状态的key为null，用于容错
         *
         * @param status
         */
        default void addDefaultStatus(Status status) {
            add(null, status);
        }

        void add(S availabilityState, Status status);

    }

}

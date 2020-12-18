package com.chord.framework.common.actuator.health.event;

import com.chord.framework.common.actuator.health.base.AvailabilityState;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.util.Assert;

/**
 *
 * 应用可用事件
 *
 * Created on 2020/12/10
 *
 * @author: wulinfeng
 */
public class AvailabilityChangeEvent <S extends AvailabilityState> extends PayloadApplicationEvent<S> {

    public AvailabilityChangeEvent(Object source, S payload) {
        super(source, payload);
    }

    public static <S extends AvailabilityState> void publish(ApplicationContext context, S state) {
        Assert.notNull(context, "Context must not be null");
        publish(context, context, state);
    }

    public static <S extends AvailabilityState> void publish(ApplicationEventPublisher publisher, Object source,
                                                             S state) {
        Assert.notNull(publisher, "Publisher must not be null");
        publisher.publishEvent(new AvailabilityChangeEvent<>(source, state));
    }

    public S getState() {
        return getPayload();
    }

}

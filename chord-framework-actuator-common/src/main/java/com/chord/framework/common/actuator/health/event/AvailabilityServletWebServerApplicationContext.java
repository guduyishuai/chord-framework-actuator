package com.chord.framework.common.actuator.health.event;

import com.chord.framework.common.actuator.health.readiness.ReadinessState;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 *
 * web容器停止时，改变就绪状态
 *
 * Created on 2020/12/17
 *
 * @author: wulinfeng
 */
public class AvailabilityServletWebServerApplicationContext extends AnnotationConfigServletWebServerApplicationContext {

    @Override
    protected void doClose() {
        if (isActive()) {
            AvailabilityChangeEvent.publish(this, ReadinessState.REFUSING_TRAFFIC);
        }
        super.doClose();
    }

}

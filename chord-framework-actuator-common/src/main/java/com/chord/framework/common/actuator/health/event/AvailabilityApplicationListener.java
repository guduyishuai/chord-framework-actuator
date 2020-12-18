package com.chord.framework.common.actuator.health.event;

import com.chord.framework.common.actuator.health.liveness.LivenessState;
import com.chord.framework.common.actuator.health.readiness.ReadinessState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *
 * 使用{@link AvailabilityServletWebServerApplicationContext}作为上下文，在web容器停止的时候改变就绪状态
 * 发布应用可用事件{@link AvailabilityChangeEvent}
 *
 * @see AvailabilityServletWebServerApplicationContext
 *
 * Created on 2020/12/17
 *
 * @author: wulinfeng
 */
public class AvailabilityApplicationListener implements SpringApplicationRunListener {

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityApplicationListener.class);

    private final SpringApplication springApplication;

    public AvailabilityApplicationListener(SpringApplication springApplication, String[] args) {
        this.springApplication = springApplication;
    }

    @Override
    public void starting() {
        try {
            setFinalStatic(this.springApplication.getClass().getDeclaredField("DEFAULT_SERVLET_WEB_CONTEXT_CLASS"), AvailabilityServletWebServerApplicationContext.class.getName());
        } catch (Exception e) {
            logger.warn("caonnot init the context, the readiness will not work when webserver is down");
        }
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        AvailabilityChangeEvent.publish(context, LivenessState.CORRECT);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        AvailabilityChangeEvent.publish(context, ReadinessState.ACCEPTING_TRAFFIC);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    private void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

}

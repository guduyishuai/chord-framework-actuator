package com.chord.framework.common.actuator.health.mapping;

import com.chord.framework.common.actuator.health.base.ApplicationAvailabilityBean;
import org.springframework.boot.actuate.endpoint.EndpointId;
import org.springframework.boot.actuate.endpoint.web.PathMapper;
import org.springframework.boot.actuate.endpoint.web.WebOperationRequestPredicate;
import org.springframework.boot.actuate.endpoint.web.servlet.AbstractWebMvcEndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 由于{@link EndpointId}对id进行了限制，不能使用带"/"的字符串，这是出于jmx的export的考虑。
 * 因此需要通过{@link PathMapper}对根路径进行改变。
 * 这样可以在{@link WebMvcEndpointHandlerMapping}注册{@link HandlerMethod}时，将"liveness"和"readiness"这两个Endpoint的访问路径注册成"/health/liveness"和"/health/readiness"
 * 当然，这是默认的行为，如果需要自己改变的话，可以在application.yml中做相应的配置
 *
 * @see EndpointId
 * @see PathMapper
 * @see WebOperationRequestPredicate
 * @see AbstractWebMvcEndpointHandlerMapping
 *
 * Created on 2020/12/16
 *
 * @author: wulinfeng
 */
public class AvailabilityHealthRootPathRule implements RootPathRule {

    /**预定义规则，优先级最高**/
    private final Map<EndpointId, String> pathMapping;

    public AvailabilityHealthRootPathRule(Map<String, String> pathMapping) {
        this.pathMapping = new HashMap<>();
        pathMapping.forEach((id, path) -> this.pathMapping
                .put(EndpointId.fromPropertyValue(id), path));
    }

    @Override
    public String getRootPath(EndpointId endpointId) {
        String path = this.pathMapping.get(endpointId);
        if(StringUtils.hasText(path)) {
            return path;
        } else {
            if(ApplicationAvailabilityBean.Constants.PROBE_LIVENESS_NAME.equals(endpointId.toLowerCaseString()) ||
                    ApplicationAvailabilityBean.Constants.PROBE_READINESS_NAME.equals(endpointId.toLowerCaseString())) {
                return ApplicationAvailabilityBean.Constants.PROBE_PREFIX + PathMapper.useEndpointId().getRootPath(endpointId);
            }
            return PathMapper.useEndpointId().getRootPath(endpointId);
        }
    }

}

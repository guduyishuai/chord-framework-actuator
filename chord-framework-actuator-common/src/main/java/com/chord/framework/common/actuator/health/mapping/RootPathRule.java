package com.chord.framework.common.actuator.health.mapping;

import org.springframework.boot.actuate.endpoint.EndpointId;

/**
 *
 * 解析Endpoint的根路径规则
 *
 * Created on 2020/12/16
 *
 * @author: wulinfeng
 */
public interface RootPathRule {

    String getRootPath(EndpointId endpointId);

}

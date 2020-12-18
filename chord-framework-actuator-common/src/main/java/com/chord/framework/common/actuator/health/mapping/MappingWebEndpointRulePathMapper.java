package com.chord.framework.common.actuator.health.mapping;

import org.springframework.boot.actuate.endpoint.EndpointId;
import org.springframework.boot.actuate.endpoint.web.PathMapper;

/**
 *
 * 委托{@link RootPathRule}对rootPath的解析提供策略
 *
 * Created on 2020/12/16
 *
 * @author: wulinfeng
 */
public class MappingWebEndpointRulePathMapper implements PathMapper {

    private final RootPathRule rootPathRule;

    public MappingWebEndpointRulePathMapper(RootPathRule rootPathRule) {
        this.rootPathRule = rootPathRule;
    }

    @Override
    @Deprecated
    public String getRootPath(String endpointId) {
        return getRootPath(EndpointId.of(endpointId));
    }

    @Override
    public String getRootPath(EndpointId endpointId) {
        return rootPathRule.getRootPath(endpointId);
    }

}

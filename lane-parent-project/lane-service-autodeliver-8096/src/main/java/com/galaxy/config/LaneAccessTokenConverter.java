package com.galaxy.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lane
 * @date 2021年07月01日 下午6:12
 */
@Component
public class LaneAccessTokenConverter extends DefaultAccessTokenConverter {
    //取出认证信息
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {

        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
        oAuth2Authentication.setDetails(map);  // 将map放入认证对象中，认证对象在controller中可以拿到
        return oAuth2Authentication;
    }
}

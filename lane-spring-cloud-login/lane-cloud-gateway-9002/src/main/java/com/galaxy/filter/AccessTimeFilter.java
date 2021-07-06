package com.galaxy.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lane
 * @date 2021年06月27日 下午6:37
 */
/**
 * 访问次数过滤器，用于限制访问注册接口的次数
 */
@Component
@Slf4j
public class AccessTimeFilter implements GlobalFilter, Ordered {

    /**
     * 最大访问次数
     */
    private final int MAX_ACCESS_TIMES = 100;

    /**
     * 统计的时间间隔，以毫秒为单位
     */
    private final long STAT_INTERVAL = 600000;

    /**
     * 记录访问注册接口的次数
     */
    private Map<String, AccessInfo> accessInfoMap = new ConcurrentHashMap<>();



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        System.out.println("开始拦截判断。。。"+path);
        //不是注册不拦截
        if (!path.startsWith("/api/user/register")){
            chain.filter(exchange);
        }
        String clientIp = request.getRemoteAddress().getHostString();
        //更新访问信息
        AccessInfo accessInfo = updateAccessInfo(clientIp);
        //判断是否超过
        AtomicInteger accessTimes = accessInfo.getAccessTimes();
        int i = accessTimes.addAndGet(1);
        accessInfo.setAccessTimes(accessTimes);
        if(i>MAX_ACCESS_TIMES){
            return createRejectedResponse(exchange.getResponse());
        }
        System.out.println("拦截结束。。。"+path);
        return chain.filter(exchange);
    }
    /**
     * 创建拒绝响应
     *
     * @param response 响应对象
     * @return 创建结果
     */
    private Mono<Void> createRejectedResponse(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);

        String data = "Register too often!";
        DataBuffer buffer = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer));
    }
    /**
     * 创建访问记录
     * @author lane
     * @date 2021/6/27 下午7:03
     * @param clientIp
     * @return com.galaxy.filter.AccessInfo
     */
    private AccessInfo createAccessInfo(String clientIp){
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setAccessTimes(new AtomicInteger(0));
        accessInfo.setStartTime(System.currentTimeMillis());
        accessInfoMap.putIfAbsent(clientIp,accessInfo);
        return accessInfo;
    }
    /**
     * 更新访问记录
     * @author lane
     * @date 2021/6/27 下午7:20
     * @param clientIp
     * @return com.galaxy.filter.AccessInfo
     */
    private AccessInfo updateAccessInfo(String clientIp){

        AccessInfo accessInfo = accessInfoMap.get(clientIp);
        //如果首次访问
        if (accessInfo==null){
            AccessInfo accessInfo1 = createAccessInfo(clientIp);
            return accessInfo1;
        }
            long startTime = accessInfo.getStartTime();
            long currentTimeMillis = System.currentTimeMillis();
            startTime+=STAT_INTERVAL;
            //如果访没超过1分钟
            if (currentTimeMillis<startTime){
                return accessInfo;
            }
            //如果超过1分钟
            accessInfo.setAccessTimes(new AtomicInteger());
            accessInfo.setStartTime(System.currentTimeMillis());
            return  accessInfo;


    }

    @Override
    public int getOrder() {
        return 0;
    }
}

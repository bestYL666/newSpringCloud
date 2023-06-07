package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YL
 * @Date: 2023/4/12 10:54
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new ZoneAvoidanceRule();
    }

}

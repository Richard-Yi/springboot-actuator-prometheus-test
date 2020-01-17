package com.ricstudio.springbootactuatorprometheustest.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Richard_yyf
 * @version 1.0 2020/1/17
 */
@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * version1:
     * 1. 限制 '/shutdown'端点的访问，只允许ACTUATOR_ADMIN访问
     * 2. 允许外部访问其他的端点
     * 3. 允许外部访问静态资源
     * 4. 允许外部访问 '/'
     * 5. 其他的访问需要被校验
     * version2:
     * 1. 限制所有端点的访问，只允许ACTUATOR_ADMIN访问
     * 2. 允许外部访问静态资源
     * 3. 允许外部访问 '/'
     * 4. 其他的访问需要被校验
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // version1
//        http
//                .authorizeRequests()
//                    .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
//                        .hasRole("ACTUATOR_ADMIN")
//                .requestMatchers(EndpointRequest.toAnyEndpoint())
//                    .permitAll()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//                    .permitAll()
//                .antMatchers("/")
//                    .permitAll()
//                .antMatchers("/**")
//                    .authenticated()
//                .and()
//                .httpBasic();

        // version2
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                    .hasRole("ACTUATOR_ADMIN")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                .antMatchers("/")
                    .permitAll()
                .antMatchers("/**")
                    .authenticated()
                .and()
                .httpBasic();
    }
}

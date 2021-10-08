package com.webflux.springbootwebfluxdemo.router;

import com.webflux.springbootwebfluxdemo.handler.CustomerHandler;
import com.webflux.springbootwebfluxdemo.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers",handler::loadCustomers)
                .GET("/router/customers/stream",streamHandler::getCustomer)
                .GET("/router/customers/{input}",handler::findCustomerById)
                .POST("/router/customers/save",handler::saveCustomer)
                .build();
    }
}

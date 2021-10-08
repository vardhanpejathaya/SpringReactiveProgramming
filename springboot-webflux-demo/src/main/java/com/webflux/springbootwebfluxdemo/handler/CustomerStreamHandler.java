package com.webflux.springbootwebfluxdemo.handler;

import com.webflux.springbootwebfluxdemo.dao.CustomerDao;
import com.webflux.springbootwebfluxdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    //Get all customers
    public Mono<ServerResponse> getCustomer(ServerRequest request) {

        Flux<Customer> customersStream = customerDao.getCustomersStream();
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream,Customer.class);

    }
}

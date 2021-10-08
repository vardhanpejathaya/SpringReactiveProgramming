package com.webflux.springbootwebfluxdemo.dao;

import com.webflux.springbootwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1,15)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Processing count : "+i))
                .mapToObj(i -> new Customer(i,"customer" +i))
                .collect(Collectors.toList());

    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1,15)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing count in stream flow : "+i))
                .map(i -> new Customer(i,"customer" +i));

    }

    //Without sleep method
    public Flux<Customer> getCustomerList() {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("Processing count in stream flow : "+i))
                .map(i -> new Customer(i,"customer" +i));

    }
}

package com.webflux.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {



    @Test
    //Mono -- onComplete flow without error
    public void testMono() {
        Mono<String> just = Mono.just("vardhan pejathaya").log();
        just.subscribe(System.out::println);

    }

    @Test
    //Mono -- onComplete flow with error
    public void testMonowithError() {
        Mono<?> just = Mono.just("vardhan pejathaya")
                .then(Mono.error(new RuntimeException("vardhan error !!!!!!!!")))
                .log();
        just.subscribe(System.out::println, e -> System.out.println(e.getMessage()));

    }

    //Test flux
    @Test
    public void testFlux() {
        Flux<String> just = Flux.just("spring", "reactive", "web")
                .concatWithValues("vard values")
                .log();
        just.subscribe(System.out::println);

    }

    //Test flux
    @Test
    public void testFluxwithError() {
        Flux<String> just = Flux.just("spring", "reactive", "web")
                .concatWithValues("vard values")
                .concatWith(Flux.error(new RuntimeException("Exception occured in flux")))
                .log();
        just.subscribe(System.out::println, e->System.out.println(e.getMessage()));

    }
}

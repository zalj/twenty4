package com.example.controller;

import com.example.model.ComplexHttpRequest;
import com.example.model.ComplexHttpResponse;
import com.example.model.Greeting;
import com.example.model.Spittle;
import com.example.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomController {

    private static final String template = "Hello, %s!";

    private final AtomicLong idCounter = new AtomicLong();
    private final AtomicLong authorIdCounter = new AtomicLong();

    @Autowired
    private CustomService customService;

    @Autowired
    private PropertySourcesPlaceholderConfigurer configurer;

    @Value("${my.prop}")
    private String myProp;

    @Value("${notExist}")
    private String notExist;

    @GetMapping("/value")
    public String value() {
        System.out.println(notExist);
        System.out.println(myProp);
        PropertySources appliedPropertySources = configurer.getAppliedPropertySources();
        appliedPropertySources.stream().map(PropertySource::toString).forEach(System.out::println);
        return myProp + ", " + notExist;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(idCounter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/spittle")
    public List<Spittle> spittle(@RequestParam(value = "name", defaultValue = "World") String name) {
        Spittle hello = new Spittle(idCounter.incrementAndGet(), authorIdCounter.incrementAndGet(), name);
        return Collections.singletonList(hello);
    }

    @GetMapping("/spittles")
    public List<Spittle> spittles() {
        Spittle hello = new Spittle(idCounter.incrementAndGet(), authorIdCounter.incrementAndGet(), "hello");
        Spittle world = new Spittle(idCounter.incrementAndGet(), authorIdCounter.incrementAndGet(), "world");
        return Arrays.asList(hello, world);
    }

    @GetMapping("/result")
    public ComplexHttpResponse<?> result(@RequestBody ComplexHttpRequest request) {
        return customService.getResultList(request);
    }
}



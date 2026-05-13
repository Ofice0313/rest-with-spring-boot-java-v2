package devcaleb.rest_with_spring_boot_java_v2.controllers;

import devcaleb.rest_with_spring_boot_java_v2.entities.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //http://localhost:8081/greeting?name=Caleb
    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World")
            String name ){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}

package io.github.avec112.domain.greet;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        log.info("Greeting {}", name); // not allowed here
//        System.out.println("Greeting " + name); // not allowed here
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }
}

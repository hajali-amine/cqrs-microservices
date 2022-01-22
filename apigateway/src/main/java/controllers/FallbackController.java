package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/commandFallBack")
    public Mono<String> commandServiceFallBack(){
        return Mono.just("Command service is taking too long to respond or is down. Please try again later.");
    }

    @RequestMapping("/queryFallBack")
    public Mono<String> queryServiceFallBack(){
        return Mono.just("Query service is taking too long to respond or is down. Please try again later.");
    }
}

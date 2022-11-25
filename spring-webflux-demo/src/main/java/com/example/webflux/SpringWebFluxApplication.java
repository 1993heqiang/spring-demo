

package com.example.webflux;

import java.time.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@SpringBootApplication
public class SpringWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxApplication.class, args);
	}

	@GetMapping("/hello")
	public Flux<String> hello() {
		return Flux.interval(Duration.ofSeconds(1L)).map(time -> "Hello:" + time);
	}
}

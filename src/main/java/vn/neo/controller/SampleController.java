package vn.neo.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sample-api")
public class SampleController {
    private final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping
    @Retry(name = "sample-api", fallbackMethod = "fallbackResponse")
    public String sampleApi() {
        return new RestTemplate().getForEntity("http://localhost:9999/test", String.class).getBody();
    }

    public String fallbackResponse(Exception ex) {
        logger.info("Exception while call api test", ex);
        return "FAILED!";
    }
}

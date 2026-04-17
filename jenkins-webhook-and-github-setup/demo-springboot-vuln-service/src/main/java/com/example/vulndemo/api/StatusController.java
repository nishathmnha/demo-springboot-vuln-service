package com.example.vulndemo.api;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StatusController {

    @GetMapping
    public Map<String, String> home() {
        return Map.of(
            "message", "demo-springboot-vuln-service is running",
            "profile", "local"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "ok",
            "service", "demo-springboot-vuln-service"
        );
    }

    @GetMapping("/vulnerability-demo")
    public Map<String, Object> vulnerabilityDemo() {
        return Map.of(
            "service", "demo-springboot-vuln-service",
            "note", "This app intentionally includes outdated dependencies for CI/CD scan demos.",
            "scan_target", "pom.xml"
        );
    }
}

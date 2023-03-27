package dev.id.backend.logic.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/demo-controller")
public class DemoController {

    @GetMapping()
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from Demo Controller :)");
    }
}

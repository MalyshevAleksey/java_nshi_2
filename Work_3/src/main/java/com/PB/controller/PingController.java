package com.PB.controller;

import com.PB.responses.PingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class PingController {

    @GetMapping("/ping")
    public PingResponse Answering(){
        return new PingResponse();
    }
}

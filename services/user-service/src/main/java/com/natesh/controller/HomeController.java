package com.natesh.controller;

import com.natesh.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ApiResponse respo(){
        ApiResponse apiResponse = new ApiResponse("Welcome to user-service");
        return apiResponse;
    }
}

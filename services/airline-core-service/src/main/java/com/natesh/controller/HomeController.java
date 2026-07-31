package com.natesh.controller;

import com.natesh.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping
    public ApiResponse home(){
        ApiResponse apiResponse = new ApiResponse("I am in airline core service");
        return apiResponse;
    }

}

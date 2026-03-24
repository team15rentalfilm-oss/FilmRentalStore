package com.iem.FilmRentalStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    // Routes to the homepage
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Routes to Utsav's specific page
    @GetMapping("/member/utsav")
    public String utsavPage() {
        return "utsav";
    }

    // You can add additional mappings for other members here
    // @GetMapping("/member/member2") ...
}
package com.bootcamp.bc.bc_stock_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/realtime")
    public String realtime(@RequestParam("symbol") String symbol,
            Model model) {
        model.addAttribute("symbol", symbol);
        return "realtime";
    }

    @GetMapping("/history")
    public String history(@RequestParam("symbol") String symbol, Model model) {
        model.addAttribute("symbol", symbol);
        return "history";
    }
}

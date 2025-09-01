package com.NicFacturas.NicFacturasDemo.controllers;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import com.NicFacturas.NicFacturasDemo.services.AppService;

@Controller
public class AppController {
    private final AppService appService;
    public AppController(AppService appService) {
        this.appService = appService;
    }
    
    @GetMapping("/")
    public String indexRoute(Model model) {
        List<Map<String, String>> modelData = null;
        model.addAttribute("data", modelData);
        return "index";
    }
}

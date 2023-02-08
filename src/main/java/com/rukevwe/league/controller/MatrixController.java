package com.rukevwe.league.controller;

import com.rukevwe.league.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MatrixController {

    private MatrixService matrixService;

    @Autowired
    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/echo")
    public String echo(@RequestParam(value = "file") MultipartFile file) {

        return matrixService.echo(file);
    }

    @GetMapping("/invert")
    public String invert(@RequestParam(value = "file") MultipartFile file) {

        return matrixService.invert(file);
    }

    @GetMapping("/flatten")
    public String flatten(@RequestParam(value = "file") MultipartFile file) {

        return matrixService.flatten(file);
    }

    @GetMapping("/sum")
    public String sum(@RequestParam(value = "file") MultipartFile file) {

        return matrixService.sum(file);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam(value = "file") MultipartFile file) {

        return matrixService.multiply(file);
    }
}

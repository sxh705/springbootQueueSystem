package io.github.sxh705.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
    @RequestMapping()
    public String get() {
        return null;
    }

}

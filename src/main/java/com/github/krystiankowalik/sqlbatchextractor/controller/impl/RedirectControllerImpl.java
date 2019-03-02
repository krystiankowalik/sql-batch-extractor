package com.github.krystiankowalik.sqlbatchextractor.controller.impl;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(basePath = "/", value = "/", description = "Redirect Controller", hidden = true)
@Controller
public class RedirectControllerImpl {

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

}
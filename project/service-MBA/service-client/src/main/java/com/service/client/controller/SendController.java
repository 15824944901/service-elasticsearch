package com.service.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Date: 2021/2/8
 *
 * @author WX964987
 */
@Controller
public class SendController {

    @GetMapping("/index")
    public String get() {
        return "index";
    }


}

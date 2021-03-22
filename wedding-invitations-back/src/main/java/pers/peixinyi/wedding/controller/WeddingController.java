package pers.peixinyi.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.peixinyi.wedding.service.WeddingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-20 16:52
 */
@RestController
@CrossOrigin
public class WeddingController {

    @Autowired
    WeddingService weddingService;

    @GetMapping("/get/*")
    public List<String> getWeddingLeaveMessage() {
        try {
            return weddingService.getWeddingLeaveMessage();
        } catch (IOException e) {
            e.printStackTrace();
            List<String> strings = new ArrayList<String>();
            strings.add("代码异常了");
            return strings;
        }
    }

    @GetMapping("/add/{wedding_leave}")
    public Boolean addWeddingLeaveMessage(@PathVariable String wedding_leave) {
        try {
            return weddingService.addWeddingLeaveMessage(wedding_leave);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

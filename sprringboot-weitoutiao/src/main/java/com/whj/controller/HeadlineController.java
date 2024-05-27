package com.whj.controller;

import com.whj.pojo.Headline;
import com.whj.service.HeadlineService;
import com.whj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;
    @PostMapping ("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){

        Result result = headlineService.publish(headline, token);

        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Headline headline = headlineService.getById(hid);
        Map data = new HashMap<>();

        data.put("headline", headline);
        return Result.ok(data);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.updateDate(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeById(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }

}

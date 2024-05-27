package com.whj.controller;

import com.whj.pojo.vo.PortalVo;
import com.whj.service.HeadlineService;
import com.whj.service.TypeService;
import com.whj.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 首页控制层
 */

@RestController
@RequestMapping("portal")
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadLineDetail(Integer hid){
        Result result = headlineService.showHeadLineDetail(hid);
        return result;
    }

}

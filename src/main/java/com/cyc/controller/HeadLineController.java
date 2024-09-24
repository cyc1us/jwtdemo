/*
 * 修改记录:
 * 修改日期     修改人员    修改说明
 * ========    =======  ============================================
 * 2024/6/27  cyc        新增
 * ========    =======  ============================================
 */
package com.cyc.controller;

import com.cyc.pojo.Headline;
import com.cyc.service.HeadlineService;
import com.cyc.utils.JwtHelper;
import com.cyc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/headline")
@CrossOrigin
public class HeadLineController {


    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.updateHeadLine(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeById(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}

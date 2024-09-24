/*
 * 修改记录:
 * 修改日期     修改人员    修改说明
 * ========    =======  ============================================
 * 2024/6/27  chenyc      新增
 * ========    =======  ============================================
 */
package com.cyc.controller;

import com.cyc.pojo.vo.PortalVo;
import com.cyc.service.HeadlineService;
import com.cyc.service.TypeService;
import com.cyc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;


    @Autowired
    private HeadlineService headlineService;

    @GetMapping("/findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();
        return result;
    }

    /**
     * 首页分页查询
     * @return
     */
    @PostMapping("findNewsPage")
    public Result findNewPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewPage(portalVo);
        return result;
    }


    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}

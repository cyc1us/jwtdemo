package com.cyc.service;

import com.cyc.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyc.pojo.vo.PortalVo;
import com.cyc.utils.Result;

/**
* @author chenyc53360
* @description 针对表【news_headline】的数据库操作Service
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadLine(Headline headline);
}

package com.cyc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyc.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyc.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author chenyc53360
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-06-27 11:54:59
* @Entity com.cyc.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectPageMap(IPage page, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(@Param("hid") Integer hid);
}





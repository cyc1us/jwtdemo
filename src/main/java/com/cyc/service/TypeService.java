package com.cyc.service;

import com.cyc.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyc.utils.Result;

/**
* @author chenyc53360
* @description 针对表【news_type】的数据库操作Service
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}

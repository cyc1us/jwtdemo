package com.cyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyc.pojo.Type;
import com.cyc.service.TypeService;
import com.cyc.mapper.TypeMapper;
import com.cyc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenyc53360
* @description 针对表【news_type】的数据库操作Service实现
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}





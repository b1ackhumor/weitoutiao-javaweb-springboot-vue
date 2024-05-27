package com.whj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whj.pojo.Type;
import com.whj.service.TypeService;
import com.whj.mapper.TypeMapper;
import com.whj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 王贺杰
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-05-24 20:42:02
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有类别数据
     *
     * @return
     */
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }

}





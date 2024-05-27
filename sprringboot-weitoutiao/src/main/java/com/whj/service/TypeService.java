package com.whj.service;

import com.whj.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whj.utils.Result;

/**
* @author 王贺杰
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-05-24 20:42:02
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有类别数据
     * @return
     */
    Result findAllTypes();
}

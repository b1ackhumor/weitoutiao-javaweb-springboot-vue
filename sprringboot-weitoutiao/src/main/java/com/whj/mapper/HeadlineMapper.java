package com.whj.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whj.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whj.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 王贺杰
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-05-24 20:42:02
* @Entity com.whj.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page,@Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}





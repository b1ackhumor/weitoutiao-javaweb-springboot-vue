package com.whj.service;

import com.whj.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whj.pojo.vo.PortalVo;
import com.whj.utils.Result;

/**
* @author 王贺杰
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-05-24 20:42:02
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页数据查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 根据id查询详情
     * @param hid
     * @return
     */
    Result showHeadLineDetail(Integer hid);

    /**
     * 发布头条方法
     * @param headline
     * @return
     */
    Result publish(Headline headline, String token);

    /**
     * 修改头条数据
     * @param headline
     * @return
     */
    Result updateDate(Headline headline);
}

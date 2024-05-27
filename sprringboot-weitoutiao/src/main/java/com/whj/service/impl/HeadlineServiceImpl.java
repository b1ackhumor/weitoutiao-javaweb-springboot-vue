package com.whj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whj.pojo.Headline;
import com.whj.pojo.vo.PortalVo;
import com.whj.service.HeadlineService;
import com.whj.mapper.HeadlineMapper;
import com.whj.utils.JwtHelper;
import com.whj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 王贺杰
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-05-24 20:42:02
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 首页数据查询
     * 1. 进行分页数据查询
     * 2.分页数据拼接到result
     *  注意事项
     *      1.查询需要自定义语句，自定义mapper方法，携带分页
     *      2.返回的结果List<Map>
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectMyPage(page,portalVo);

        List<Map> records = page.getRecords();
        Map data = new HashMap();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());

        Map pageInfo = new HashMap<>();
        pageInfo.put("pageInfo",data);

        return Result.ok(pageInfo);
    }

    /**
     * 根据id查询详情
     * 1. 查询对应的数据（多表查询） 方法需要自定义
     * 2. 修改对应的阅读量（+1）【version乐观锁 ，需要得知当前数据对应的版本】
     * @param hid
     * @return
     */
    @Override
    public Result showHeadLineDetail(Integer hid) {

        Map data = headlineMapper.queryDetailMap(hid);
        Map headlineMap = new HashMap<>();
        headlineMap.put("headline",data);

        //修改阅读量+1
        Headline headline = new Headline();
        headline.setHid((Integer) data.get("hid"));
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer) data.get("pageViews") + 1);
        headlineMapper.updateById(headline);

        return Result.ok(headlineMap);
    }

    /**
     * 发布头条方法
     * 1. 补全数据
     *
     * @param headline
     * @return
     */
    @Override
    public Result publish(Headline headline, String token) {
        int userId = jwtHelper.getUserId(token).intValue();

        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    /**
     * 修改头条数据
     * 1. 查询数据最新version
     * 2. 更新修改时间为当前时间
     * @param headline
     * @return
     */
    @Override
    public Result updateDate(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);//乐观锁
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);
        return Result.ok(null);
    }



}





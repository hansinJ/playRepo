package com.text.demo.mapper.base;

import com.text.demo.entity.Banner;
import com.text.demo.entity.BannerCriteria;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    List<Banner> selectByExampleWithRowbounds(BannerCriteria example, RowBounds rowBounds);

    List<Banner> selectByExample(BannerCriteria example);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}
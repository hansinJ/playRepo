package com.text.demo.mapper.base;

import com.text.demo.entity.Setting;
import com.text.demo.entity.SettingCriteria;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface SettingMapper {
    int deleteByPrimaryKey(Integer settingId);

    int insert(Setting record);

    int insertSelective(Setting record);

    List<Setting> selectByExampleWithRowbounds(SettingCriteria example, RowBounds rowBounds);

    List<Setting> selectByExample(SettingCriteria example);

    Setting selectByPrimaryKey(Integer settingId);

    int updateByPrimaryKeySelective(Setting record);

    int updateByPrimaryKey(Setting record);
}
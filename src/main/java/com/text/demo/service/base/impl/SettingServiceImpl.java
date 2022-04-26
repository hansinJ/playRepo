package com.text.demo.service.base.impl;

import com.text.demo.entity.Setting;
import com.text.demo.entity.SettingCriteria;
import com.text.demo.mapper.base.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl {
    @Autowired
    private SettingMapper settingMapper;

    public int insert(String value) {
        Setting setting = new Setting();
        setting.setValue(value);
        settingMapper.insertSelective(setting);
        return setting.getSettingId();
    }

    public int update(Integer settingId, String value) {
        Setting setting = new Setting();
        setting.setSettingId(settingId);
        setting.setValue(value);
        return settingMapper.updateByPrimaryKeySelective(setting);
    }

    public List<Setting> list() {
        SettingCriteria settingCriteria = new SettingCriteria();
        settingCriteria.setOrderByClause("setting_id desc");
        return settingMapper.selectByExample(settingCriteria);
    }
}

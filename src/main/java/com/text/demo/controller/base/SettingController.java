package com.text.demo.controller.base;

import com.text.demo.entity.Setting;
import com.text.demo.service.base.impl.SettingServiceImpl;
import com.text.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("setting")
public class SettingController {
    @Autowired
    private SettingServiceImpl settingServiceImpl;

    @ApiOperation("新增/修改")
    @PostMapping
    public JsonResult setting(@RequestBody Setting setting) {
        JsonResult res = new JsonResult();
        if (setting == null) {
            res.setCode(-1);
            res.setMsg("参数无效");
            return res;
        }
        if (setting != null && setting.getSettingId() == null) {
            res.setCode(0);
            res.setData(settingServiceImpl.insert(setting.getValue()));
            return res;
        }
        res.setCode(0);
        res.setData(settingServiceImpl.update(setting.getSettingId(), setting.getValue()));
        return res;
    }

    @ApiOperation("列表")
    @GetMapping
    public JsonResult list() {
        JsonResult res = new JsonResult();
        res.setCode(0);
        res.setData(settingServiceImpl.list());
        return res;
    }
}

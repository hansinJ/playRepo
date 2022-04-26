package com.text.demo.controller.base;

import com.text.demo.service.base.impl.SettingServiceImpl;
import com.text.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("setting")
public class SettingController {
    @Autowired
    private SettingServiceImpl settingServiceImpl;

    @ApiOperation("新增/修改")
    @PostMapping
    public JsonResult setting(@RequestParam(required = false) Integer settingId,
                              @RequestParam String value) {
        JsonResult res = new JsonResult();
        if (settingId == null) {
            res.setData(settingServiceImpl.insert(value));
            return res;
        }
        res.setData(settingServiceImpl.update(settingId, value));
        return res;
    }

    @ApiOperation("新增/修改")
    @GetMapping
    public JsonResult list() {
        JsonResult res = new JsonResult();
        res.setData(settingServiceImpl.list());
        return res;
    }
}

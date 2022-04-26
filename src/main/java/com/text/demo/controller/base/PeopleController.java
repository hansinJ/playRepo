package com.text.demo.controller.base;

import com.text.demo.entity.PeopleAndPlayEntity;
import com.text.demo.entity.PeopleEntity;
import com.text.demo.entity.PlaySort;
import com.text.demo.model.ApiResult;
import com.text.demo.service.base.PeopleService;
import com.text.demo.utils.JsonResult;
import com.text.demo.utils.UuidUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/people/")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @Value("${serverUrl}")
    String serverUrl;

    @RequestMapping("list")
    @ResponseBody
    public JsonResult peopleList(Integer[] playState, String nickName, String businessStatus, Integer currentPage, Integer nowPageSize) {
        JsonResult res = new JsonResult();
        try {
            Map<String, Object> data = peopleService.peopleList(playState, nickName, businessStatus, currentPage, nowPageSize);
            res.setCode(200);
            res.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    // 查找陪玩类型
    @RequestMapping("getPlayStateList")
    @ResponseBody
    public JsonResult getPlayStateList() {
        JsonResult res = new JsonResult();
        try {
            List<Object> data = peopleService.getPlayStateList();
            res.setData(data);
            res.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping(value = "peopleSave")
    @ResponseBody
    public JsonResult peopleSave(@RequestBody PeopleAndPlayEntity peopleEntity) {
        JsonResult res = new JsonResult();
        try {
            Boolean data = peopleService.peopleSave(peopleEntity);
            res.setMsg(data);
            res.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "peopleEdit", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult peopleEdit(@RequestBody PeopleAndPlayEntity peopleEntity) {
        JsonResult res = new JsonResult();
        try {
            Boolean data = peopleService.peopleEdit(peopleEntity);
            res.setData(data);
            res.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("peopleDetail")
    @ResponseBody
    public JsonResult peopleDetail(int id) {
        JsonResult res = new JsonResult();
        try {
            PeopleEntity data = peopleService.peopleDetail(id);
            res.setData(data);
            res.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("toUpperAndLower")
    @ResponseBody
    public JsonResult toUpperAndLower(String ids, String frameState) {
        JsonResult res = new JsonResult();
        try {
            // 上下架
            Boolean data = peopleService.toUpperAndLower(ids, frameState);
            res.setData(data);
            res.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("peopleDel")
    @ResponseBody
    public JsonResult peopleDel(String ids) {
        JsonResult res = new JsonResult();
        try {
            Boolean data = peopleService.deleteData(ids);
            res.setData(data);
            res.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 图片上传
     *
     * @param files
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> excelImport(List<MultipartFile> files, HttpServletRequest request) throws IOException {
        String url = "";
        Map<String, Object> data = new HashMap<>();
        if (files != null) {
            for (MultipartFile file : files) {
                String filename = UuidUtil.createUUID() + file.getOriginalFilename();
                SaveFileFromInputStream(file.getInputStream(), serverUrl, filename);//保存到服务器的路径
                url = url + filename + ",";
            }
        }
        data.put("data", url.substring(0, url.length() - 1));
        return data;
    }

    /**
     * 将MultipartFile转化为file并保存到服务器上的某地
     */
    public void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + savefile);
        System.out.println("------------" + path + savefile);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    @ApiOperation("排序")
    @ResponseBody
    @PutMapping("sort")
    public ApiResult sort(@RequestBody List<PlaySort> playSorts) {
        ApiResult apiResult = new ApiResult();
        if (CollectionUtils.isEmpty(playSorts) || playSorts.stream().anyMatch(p -> p.getPlayId() == null || p.getPlayId().compareTo(0) < 1)) {
            return apiResult.setCode(-1).setMessage("参数无效");
        }

        return apiResult.setData(peopleService.sortPlay(playSorts));
    }
}

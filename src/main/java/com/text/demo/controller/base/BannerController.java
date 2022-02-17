package com.text.demo.controller.base;

import com.text.demo.constant.BusinessException;
import com.text.demo.controller.dto.BannerDto;
import com.text.demo.entity.Banner;
import com.text.demo.model.ApiResult;
import com.text.demo.model.PageResult;
import com.text.demo.service.base.impl.BannerService;
import com.text.demo.utils.DateHelper;
import com.text.demo.utils.PageUtil;
import com.text.demo.utils.StringUtil;
import com.text.demo.utils.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags = "Banner图")
@RestController
@ResponseBody
@CrossOrigin
@RequestMapping(value = "v1/banner")
public class BannerController {
    protected static final Logger logger = LoggerFactory.getLogger(BannerController.class);
    @Value("${serverUrl}")
    private String staticFolder;
    private final String uploadPath = "banner";
    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "上传Banner图片")
    @PostMapping(value = "upload")
    public ApiResult<String> upload(@RequestParam(value = "uploadFile") MultipartFile multipartFile) {
        ApiResult<String> apiResponse = new ApiResult();
        if (multipartFile == null) {
            return apiResponse.setCode(-1).setMessage("参数错误");
        }
        String fileName = multipartFile.getOriginalFilename().toLowerCase();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (!Arrays.asList("jpg", "jpeg", "png").contains(suffix)) {
            return apiResponse.setCode(-1).setMessage("不支持的图片格式");
        }
        String folder = new SimpleDateFormat("yyyyMMdd").format(new Date());
        fileName = String.format("%s.%s", SystemUtil.generateUUIDNoWhiffletree().substring(8, 16), suffix);

        try {
            File file = new File(new File(String.format("%s/%s/%s/%s", staticFolder, uploadPath, folder, fileName)).getAbsolutePath());
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else {
                file.createNewFile();
            }
            multipartFile.transferTo(file);

            return apiResponse.setData(String.format("%s/%s/%s", uploadPath, folder, fileName));
        } catch (HttpServerErrorException e) {
            logger.error("remote server error", e);
        } catch (Exception e) {
            logger.error("request error", e);
        }
        return apiResponse.setCode(-1).setMessage("上传失败");
    }

    @ApiOperation(value = "添加", httpMethod = "POST")
    @PostMapping
    public ApiResult<Boolean> addBanner(@RequestBody BannerDto bannerDto) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (null == bannerDto) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (StringUtil.isEmpty(bannerDto.getCover())) {
            return apiResult.setCode(-1).setMessage("封面图片不能为空");
        }
        if (StringUtil.isEmpty(bannerDto.getTitle())) {
            return apiResult.setCode(-1).setMessage("标题不能为空");
        }
        if (StringUtil.isEmpty(bannerDto.getPageName())) {
            return apiResult.setCode(-1).setMessage("展示页面不能为空");
        }
        if (!Arrays.asList("homepage").contains(bannerDto.getPageName().toLowerCase())) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (StringUtil.isEmpty(bannerDto.getLocation())) {
            return apiResult.setCode(-1).setMessage("展示位置不能为空");
        }
        if (!Arrays.asList("Top", "Bottom").contains(bannerDto.getLocation())) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (StringUtil.isEmpty(bannerDto.getDestination())) {
            return apiResult.setCode(-1).setMessage("跳转地址不能为空");
        }
        if (StringUtil.isEmpty(bannerDto.getStatus())) {
            return apiResult.setCode(-1).setMessage("状态不能为空");
        }
        if (!Arrays.asList("Online", "Offline", "Online-Ready").contains(bannerDto.getStatus())) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (bannerDto.getStatus().equals("Online-Ready")) {
            if (null == bannerDto.getOnlineDate()) {
                return apiResult.setCode(-1).setMessage("上线日期不能为空");
            }
            if (bannerDto.getOnlineDate().compareTo(DateHelper.getDate(new Date())) < 1) {
                return apiResult.setCode(-1).setMessage("上线日期必需大于今日");
            }
        } else {
            bannerDto.setOnlineDate(null);
        }
        if (bannerDto.getSort() != null && bannerDto.getSort().compareTo(0) < 1) {
            return apiResult.setCode(-1).setMessage("序号必需为大于0的整数");
        }

        Banner banner = new Banner();
        banner.setTitle(bannerDto.getTitle());
        banner.setPageName(bannerDto.getPageName());
        banner.setLocation(bannerDto.getLocation());
        banner.setDestination(bannerDto.getDestination());
        banner.setStatus(bannerDto.getStatus());
        banner.setOnlineDate(bannerDto.getOnlineDate());
        banner.setCover(bannerDto.getCover());
        banner.setSort(banner.getSort());
        // todo
//        banner.setCreateUserId(super.getCurrentUserId());
        return apiResult.setData(bannerService.addBanner(banner));
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @PutMapping("{bannerId}")
    public ApiResult<Boolean> updateBanner(@PathVariable Integer bannerId, @RequestBody BannerDto bannerDto) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (null == bannerDto || null == bannerId || bannerId <= 0) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (StringUtil.isEmpty(bannerDto.getCover())) {
            return apiResult.setCode(-1).setMessage("封面图片不能为空");
        }
        if (StringUtil.isEmpty(bannerDto.getTitle())) {
            return apiResult.setCode(-1).setMessage("标题不能为空");
        }
        if (StringUtil.isEmpty(bannerDto.getPageName())) {
            return apiResult.setCode(-1).setMessage("展示页面不能为空");
        }
        if (!Arrays.asList("HomePage").contains(bannerDto.getPageName())) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (StringUtil.isEmpty(bannerDto.getLocation())) {
            return apiResult.setCode(-1).setMessage("展示位置不能为空");
        }
        if (!Arrays.asList("Top", "Bottom").contains(bannerDto.getLocation())) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (StringUtil.isEmpty(bannerDto.getDestination())) {
            return apiResult.setCode(-1).setMessage("跳转地址不能为空");
        }
        if (StringUtil.isEmpty(bannerDto.getStatus())) {
            return apiResult.setCode(-1).setMessage("状态不能为空");
        }
        if (!Arrays.asList("Online", "Offline", "Online-Ready").contains(bannerDto.getStatus())) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        if (bannerDto.getStatus().equals("Online-Ready")) {
            if (null == bannerDto.getOnlineDate()) {
                return apiResult.setCode(-1).setMessage("上线日期不能为空");
            }
            if (bannerDto.getOnlineDate().compareTo(DateHelper.getDate(new Date())) < 1) {
                return apiResult.setCode(-1).setMessage("上线日期必需大于今日");
            }
        } else {
            bannerDto.setOnlineDate(null);
        }
        if (bannerDto.getSort() != null && bannerDto.getSort().compareTo(0) < 1) {
            return apiResult.setCode(-1).setMessage("序号必需为大于0的整数");
        }

        Banner banner = new Banner();
        banner.setBannerId(bannerId);
        banner.setTitle(bannerDto.getTitle());
        banner.setPageName(bannerDto.getPageName());
        banner.setLocation(bannerDto.getLocation());
        banner.setDestination(bannerDto.getDestination());
        banner.setStatus(bannerDto.getStatus());
        banner.setOnlineDate(bannerDto.getOnlineDate());
        banner.setCover(bannerDto.getCover());
        banner.setSort(banner.getSort());
        return apiResult.setData(bannerService.updateBanner(banner));
    }

    @ApiOperation(value = "详细", httpMethod = "GET")
    @GetMapping("{bannerId}")
    public ApiResult<Banner> getBannerDetail(@PathVariable Integer bannerId) {
        ApiResult<Banner> apiResult = new ApiResult<>();
        if (null == bannerId || bannerId <= 0) {
            return apiResult.setCode(BusinessException.PARAMETER_INVALID).setMessage(BusinessException.getMessage(BusinessException.PARAMETER_INVALID));
        }
        Banner banner = bannerService.getBannerDetail(bannerId);
        if (banner.getSort().equals(0)) {
            banner.setSort(null);
        }
        return apiResult.setData(banner);
    }

    @ApiOperation(value = "列表", httpMethod = "GET", notes = "pageName参数说明<br/>首页：HomePage<br/>status参数说明<br/>上线：Online；上线准备：Online-Ready；下线：Offline")
    @GetMapping
    public ApiResult<PageResult<List<Banner>>> getBankList(
            @RequestParam(required = false) String pageName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize
    ) {
        pageIndex = PageUtil.adaptPageIndex(pageIndex);
        pageSize = PageUtil.adaptPageSize(pageSize);
        return new ApiResult<PageResult<List<Banner>>>().setData(bannerService.getBannerPageList(pageIndex, pageSize, pageName, status));
    }
}

package com.text.demo.service.base.impl;

import com.github.pagehelper.Page;
import com.text.demo.entity.Banner;
import com.text.demo.entity.BannerCriteria;
import com.text.demo.mapper.base.BannerMapper;
import com.text.demo.model.PageResult;
import com.text.demo.utils.DateHelper;
import com.text.demo.utils.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    public Boolean addBanner(Banner banner) {
        if (banner.getCreateUserId() == null) {
            return false;
        }
        Date date = new Date();
        if (!banner.getStatus().toLowerCase().equals("Online-Ready")) {
            banner.setOnlineDate(null);
        }
        if (null == banner.getSort()) {
            banner.setSort(0);
        }
        banner.setCreateTime(date);
        banner.setUpdateUserId(banner.getCreateUserId());
        banner.setUpdateTime(date);
        if (bannerMapper.insertSelective(banner) > 0) {
            return true;
        }
        return false;
    }

    public Banner getBannerDetail(Integer bannerId) {
        return bannerMapper.selectByPrimaryKey(bannerId);
    }

    public Boolean updateBanner(Banner banner) {
        if (banner.getBannerId() == null || banner.getUpdateUserId() == null) {
            return false;
        }
        Date date = new Date();
        if (!banner.getStatus().toLowerCase().equals("Online-Ready")) {
            banner.setOnlineDate(null);
        }
        if (null == banner.getSort()) {
            banner.setSort(0);
        }
        banner.setCreateUserId(null);
        banner.setCreateTime(null);
        banner.setUpdateTime(date);
        return bannerMapper.updateByPrimaryKeySelective(banner) > 0;
    }

    public PageResult<List<Banner>> getBannerPageList(
            Integer pageIndex,
            Integer pageSize,
            String pageName,
            String status
    ) {
        PageResult<List<Banner>> pageResult = new PageResult<>();
        if (pageIndex < 0 || pageSize <= 0) {
            return pageResult;
        }
        BannerCriteria bannerCriteria = new BannerCriteria();
        bannerCriteria.setOrderByClause("banner_id desc");
        BannerCriteria.Criteria criteria = bannerCriteria.createCriteria();
        if (!StringUtil.isEmpty(pageName)) {
            criteria.andPageNameEqualTo(pageName);
        }
        if (!StringUtil.isEmpty(status) && Arrays.asList("Online", "Offline", "Online-Ready").contains(status.toLowerCase())) {
            criteria.andStatusEqualTo(status);
        }
        List<Banner> bannerList = bannerMapper.selectByExampleWithRowbounds(bannerCriteria, new RowBounds(pageIndex, pageSize));

        return pageResult.setPageIndex(pageIndex).setPageSize(pageSize).setTotal(((Page<Banner>) bannerList).getTotal()).setRows(bannerList);
    }

    private List<Banner> getValidBannerList() {
        BannerCriteria bannerCriteria = new BannerCriteria();
        bannerCriteria.setOrderByClause("if(sort = 0, 0, 1) desc, sort asc, banner_id desc");
        BannerCriteria.Criteria criteria = bannerCriteria.createCriteria();
        criteria.andStatusIn(Arrays.asList("Online", "Online-Ready"));
        List<Banner> bannerList = bannerMapper.selectByExample(bannerCriteria);
        bannerList.forEach(b -> {
            b.setCreateUserId(null);
            b.setUpdateUserId(null);
            b.setUpdateTime(null);
            b.setSort(null);
        });

        return bannerList;
    }

    public List<Banner> getAppBannerList(String pageName) {
        Integer count = 0;
        List<Banner> bannerList = new ArrayList<>();
        for (Banner banner : this.getValidBannerList()) {
            if ((StringUtil.isEmpty(pageName) || banner.getPageName().equals(pageName)) &&
                    (banner.getStatus().equals("Online") || (banner.getOnlineDate() != null && banner.getOnlineDate().compareTo(DateHelper.getDate(new Date())) < 1))) {
                bannerList.add(banner);
                count++;
                if (count == 3) {
                    break;
                }
            }
        }
        return bannerList;
    }
}

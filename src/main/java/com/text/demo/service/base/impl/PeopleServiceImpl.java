package com.text.demo.service.base.impl;

import com.text.demo.entity.PeopleAndPlayEntity;
import com.text.demo.entity.PeopleEntity;
import com.text.demo.entity.PlaySort;
import com.text.demo.mapper.base.PeopleMapper;
import com.text.demo.service.base.PeopleService;
import com.text.demo.utils.ReturnData;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    PeopleMapper peopleMapper;

    @Value("${serverUrl}")
    String serverUrl;

    @Override
    public Map<String, Object> peopleList(Integer[] playState, String nickName, String businessStatus, Integer currentPage, Integer nowPageSize) {
        int current = (currentPage - 1) * nowPageSize;
        List<PeopleEntity> res = peopleMapper.peopleList(playState, nickName, businessStatus, current, nowPageSize);
        List<PeopleEntity> total = peopleMapper.getTotal(playState, nickName, businessStatus);
        List<PeopleEntity> resDistinctByKey = res.stream()
                .filter(distinctByKey(PeopleEntity::getId))
                .collect(Collectors.toList());
        List<PeopleEntity> totalDistinctByKey = total.stream()
                .filter(distinctByKey(PeopleEntity::getId))
                .collect(Collectors.toList());
        for (PeopleEntity peopleEntity : resDistinctByKey) {
            peopleEntity.setPlayStateList(peopleMapper.getPlayStateListById(peopleEntity.getId()));
        }
        //对list进行分片
//        resDistinctByKey.sort(Comparator.comparing(PeopleEntity::getSortTemp).thenComparing(PeopleEntity::getSort));
        List<List<PeopleEntity>> pageList = ListUtils.partition(resDistinctByKey, nowPageSize);
        int totals = 0;
        if (null != totalDistinctByKey) {
            totals = totalDistinctByKey.size();
        }
        if (currentPage.compareTo(pageList.size()) == 1) {
            Map<String, Object> data = new HashMap<>();
            data.put("total", totals);
            data.put("row", "");
            data.put("current", currentPage);
            data.put("nowPageSize", nowPageSize);
            return data;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("total", totals);
        data.put("row", pageList.get(currentPage - 1));
        data.put("current", currentPage);
        data.put("nowPageSize", nowPageSize);
        return data;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public List<Object> getPlayStateList() {
        List<Object> res = peopleMapper.getPlayStateList();
        return res;
    }

    @Override
    public Boolean toUpperAndLower(String ids, String frameState) {
        int res = 0;
        if (!ids.contains(",")) {
            res = peopleMapper.toUpperAndLower(Integer.parseInt(ids), frameState);
        } else {
            for (String id : ids.split(",")) {
                res = peopleMapper.toUpperAndLower(Integer.parseInt(id), frameState);
            }
        }
        return ReturnData.returnData(res);
    }

    @Override
    public PeopleEntity peopleDetail(int id) {
        PeopleEntity res = peopleMapper.peopleDetail(id);
        res.setPlayStateList(peopleMapper.getPlayStateListById(res.getId()));
        return res;
    }

    @Override
    public Boolean deleteData(String ids) {
        int res = 0;
        if (ids.split(",").length == 0) {
            res = peopleMapper.deleteDataOne(Integer.parseInt(ids));
            //先删除陪玩类型关联关系
            peopleMapper.delPlayState(Integer.parseInt(ids));
        } else {
            for (String id : ids.split(",")) {
                res = peopleMapper.deleteDataOne(Integer.parseInt(id));
                peopleMapper.delPlayState(Integer.parseInt(id));
            }
        }
        return ReturnData.returnData(res);
    }

    @Override
    public Boolean peopleSave(PeopleAndPlayEntity peopleEntity) {
        // 保存到数据库
        peopleEntity.setBusinessStatus("Open");
        peopleEntity.setLastUpdateTime(new Date());
        peopleEntity.setCreateTime(new Date());
        peopleEntity.setCreateUser(1);
        int res = peopleMapper.peopleSave(peopleEntity);
        int peopleId = peopleMapper.getPeopleId();
        int[] playStates = peopleEntity.getPlayStates();
        // 增加类型人员关联数据
        if (null != playStates && playStates.length != 0) {
            for (int playState : playStates) {
                peopleMapper.savePlayState(peopleId, playState);
            }
        }
        return ReturnData.returnData(res);
    }

    @Override
    public Boolean peopleEdit(PeopleAndPlayEntity peopleEntity) {
        peopleEntity.setLastUpdateTime(new Date());
        peopleEntity.setLastUpdateUser(1);
        int res = peopleMapper.peopleEdit(peopleEntity);
        int[] playStates = peopleEntity.getPlayStates();
        //先删除陪玩类型关联关系
        peopleMapper.delPlayState(peopleEntity.getId());
        // 增加类型人员关联数据
        if (null != playStates && playStates.length != 0) {
            for (int playState : playStates) {
                peopleMapper.savePlayState(peopleEntity.getId(), playState);
            }
        }
        return ReturnData.returnData(res);
    }

    public Boolean sortPlay(List<PlaySort> playSorts) {
        return peopleMapper.sortPlay(playSorts) > 0;
    }
}

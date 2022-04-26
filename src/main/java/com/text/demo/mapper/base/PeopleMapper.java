package com.text.demo.mapper.base;

import com.text.demo.entity.PeopleAndPlayEntity;
import com.text.demo.entity.PeopleEntity;
import com.text.demo.entity.PlaySort;
import com.text.demo.entity.PlayStateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {
    int deleteData(@Param("ids") int[] ids);

    List<PeopleEntity> peopleList(@Param("playState") Integer[] playState,@Param("nickName") String nickName,@Param("businessStatus") String businessStatus,@Param("current")int current,@Param("nowPageSize") int nowPageSize);

    int peopleSave(PeopleAndPlayEntity peopleEntity);

    int peopleEdit(PeopleAndPlayEntity peopleEntity);

    PeopleEntity peopleDetail(@Param("id") int id);

    List<Object> getPlayStateList();

    int toUpperAndLower(@Param("id")int id,@Param("frameState")String frameState);

    List<PeopleEntity> getTotal(@Param("playState") Integer[] playState,@Param("nickName") String nickName,@Param("businessStatus") String businessStatus);

    int deleteDataOne(@Param("id")int id);

    List<PlayStateEntity> getPlayStateListById(@Param("playId")int id);

    int savePlayState(@Param("playId")int playId,@Param("playState") int playState);

    void delPlayState(@Param("playId")int id);

    int getPeopleId();

    int sortPlay(@Param("list")List<PlaySort> sorts);
}

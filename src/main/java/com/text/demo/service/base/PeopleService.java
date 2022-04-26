package com.text.demo.service.base;

import com.text.demo.entity.PeopleAndPlayEntity;
import com.text.demo.entity.PeopleEntity;
import com.text.demo.entity.PlaySort;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PeopleService {
    Map<String, Object> peopleList(Integer[] playState, String nickName, String businessStatus, Integer currentPage, Integer nowPageSize);

    Boolean peopleEdit(PeopleAndPlayEntity peopleEntity);

    PeopleEntity peopleDetail(int id);

    Boolean deleteData(String ids);

    Boolean peopleSave(PeopleAndPlayEntity peopleEntity);

    List<Object> getPlayStateList();

    Boolean toUpperAndLower(String ids, String frameState);

    Boolean sortPlay(List<PlaySort> playSorts);
}

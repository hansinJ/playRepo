package com.text.demo;

import com.text.demo.service.base.PeopleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PeopleControllerTest {
    @Autowired
    private PeopleService peopleService;

    @Test
    public void playList(){
        Map<String, Object> data = peopleService.peopleList(null, null, null, 1, 30);
        System.out.println(data.get("row").toString());
    }
}

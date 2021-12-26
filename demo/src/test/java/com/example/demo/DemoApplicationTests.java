package com.example.demo;

import com.example.demo.user.entity.BaseUser;
import com.example.demo.user.entity.Course;
import com.example.demo.user.mapper.BaseUserMapper;
import com.example.demo.user.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    BaseUserMapper baseUserMapper;
    CourseMapper mapper;

    @Autowired
    public DemoApplicationTests(BaseUserMapper mapper, CourseMapper courseMapper) {
        this.baseUserMapper = mapper;
        this.mapper = courseMapper;
    }

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            BaseUser bo = new BaseUser();
//            bo.setId(i);
            bo.setUserId(UUID.randomUUID().toString());
            bo.setUserAccount("account_" + i);
            bo.setUserName("张三" + i);
            bo.setUserPassword(UUID.randomUUID().toString());
            baseUserMapper.insert(bo);
        }
    }

    @Test
    void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course bo = new Course();
//            bo.setCid(Long.valueOf(i));
            bo.setCname("张三" + i);
            bo.setCstatus(i + "");
            bo.setUserId(Long.valueOf("" + (1000 + i)));
            mapper.insert(bo);
        }
    }

}

package com.yingtao.crud.test;

import com.yingtao.crud.bean.Department;
import com.yingtao.crud.bean.Employee;
import com.yingtao.crud.dao.DepartmentMapper;
import com.yingtao.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author Adam
 * @create 2022-03-11 18:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession session;


    @Test
    public void testMapper(){
        //新增部门信息
//        departmentMapper.insertSelective(new Department(null, "开发部"));
//        departmentMapper.insertSelective(new Department(null, "测试部"));

//        employeeMapper.insertSelective(new Employee(null, "张三", "M", "zhangsan@qq.com", 1));
//        employeeMapper.insertSelective(new Employee(null, "李四", "M", "lisi@163.com", 1));
//        employeeMapper.insertSelective(new Employee(null, "王五", "M", "wangwu@sina.com", 1));

        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 100; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uid, "W", uid + "@gmail.com", 2));
        }
    }
}

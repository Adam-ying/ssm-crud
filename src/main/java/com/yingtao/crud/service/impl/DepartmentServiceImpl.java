package com.yingtao.crud.service.impl;

import com.yingtao.crud.bean.Department;
import com.yingtao.crud.dao.DepartmentMapper;
import com.yingtao.crud.service.DepartmentServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Adam
 * @create 2022-03-14 9:09
 */
@Service
public class DepartmentServiceImpl implements DepartmentServie {

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}

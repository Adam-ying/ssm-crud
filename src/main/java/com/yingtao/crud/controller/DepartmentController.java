package com.yingtao.crud.controller;

import com.yingtao.crud.bean.Department;
import com.yingtao.crud.bean.Msg;
import com.yingtao.crud.service.DepartmentServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Adam
 * @create 2022-03-14 9:06
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentServie departmentServie;

    /**
     * 返回所有的部门信息
     * @return
     */

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> depts = departmentServie.getDepts();
        return Msg.success().add("depts",depts);
    }
}

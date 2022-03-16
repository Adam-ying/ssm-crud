package com.yingtao.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yingtao.crud.bean.Employee;
import com.yingtao.crud.bean.Msg;
import com.yingtao.crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adam
 * @create 2022-03-12 17:29
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 单个删除与批量删除二合一
     */
    @DeleteMapping("/emp/{ids}")
    @ResponseBody
    public Msg deleteEmpById(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            String[] strIds = ids.split("-");
            //组装ids数组
            List<Integer> del_ids = new ArrayList<>();
            for (String strId:strIds) {
                del_ids.add(Integer.parseInt(strId));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            int empId = Integer.parseInt(ids);
            employeeService.deleteEmpById(empId);
        }
        return Msg.success();
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/emp/{empId}")
    @ResponseBody
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 查询某用户信息
     */
    @GetMapping("/emp/{empId}")
    @ResponseBody
    public Msg getEmp(@PathVariable("empId") Integer empId){
        Employee employee = employeeService.getEmp(empId);
        return Msg.success().add("emp",employee);
    }


    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);
    }
//    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
//        PageHelper.startPage(pn,5);
//        List<Employee> emps = employeeService.getAll();
//        PageInfo page = new PageInfo(emps,5);
//        model.addAttribute("pageInfo",page);
//        return "list";
//    }

    /**
     * 检查用户名是否可用
     */
    @GetMapping("/checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName")String empName){
        //先判断用户名是否合法
        String regEx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regEx)){
            return Msg.fail().add("va_msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        //然后判断用户名是否已经存在
        if (employeeService.checkUser(empName)){
            return Msg.success();
        }
        return Msg.fail().add("va_msg", "用户名不可用");
    }

    /**
     * JSR303 校验新增用户数据
     * @param employee
     * @param result
     * @return
     */
    @PostMapping(value = "/emp")
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            //校检失败，在模态框中显示错误信息
            Map<String,Object> map = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                map.put(error.getField(),error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }
        employeeService.saveEmp(employee);
        return Msg.success();
    }
}

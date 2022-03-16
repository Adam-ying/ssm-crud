package com.yingtao.crud.service;

import com.yingtao.crud.bean.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Adam
 * @create 2022-03-12 17:26
 */

public interface EmployeeService {


    List<Employee> getAll();

    boolean checkUser(String empName);

    void saveEmp(Employee employee);

    Employee getEmp(Integer empId);

    void updateEmp(Employee employee);

    void deleteBatch(List<Integer> del_ids);

    void deleteEmpById(int empId);
}

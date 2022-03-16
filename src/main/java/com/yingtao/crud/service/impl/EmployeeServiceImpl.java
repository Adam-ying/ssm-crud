package com.yingtao.crud.service.impl;

import com.yingtao.crud.bean.Employee;
import com.yingtao.crud.bean.EmployeeExample;
import com.yingtao.crud.dao.EmployeeMapper;
import com.yingtao.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Adam
 * @create 2022-03-12 17:27
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 判断用户名是否存在
     *
     * @param empName
     * @return true 代表用户名可用 ，反之
     */
    @Override
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count==0;
    }

    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    @Override
    public Employee getEmp(Integer empId) {
        Employee employee = employeeMapper.selectByPrimaryKey(empId);
        return employee;
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void deleteBatch(List<Integer> del_ids) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpIdIn(del_ids);
        employeeMapper.deleteByExample(example);
    }

    @Override
    public void deleteEmpById(int empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }
}

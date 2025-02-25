package com.example.CMS.Service;

import com.example.CMS.Dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    EmployeeDto CreateEmployee(EmployeeDto employeeDto);

    EmployeeDto GetEmployeeById(Long employeeId);

    List<EmployeeDto> GetAllEmployee();

    EmployeeDto UpdateEmployee(Long employeeId, EmployeeDto updateEmployee);

    void deleteEmployee(Long employeeId);
}

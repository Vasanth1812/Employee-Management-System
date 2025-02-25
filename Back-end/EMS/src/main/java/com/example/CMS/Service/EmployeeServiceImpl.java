package com.example.CMS.Service;

import com.example.CMS.Dto.EmployeeDto;
import com.example.CMS.Entity.Employee;
import com.example.CMS.Exception.ResourceNotFoundException;
import com.example.CMS.Mapper.EmployeeMapper;
import com.example.CMS.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto CreateEmployee(EmployeeDto employeeDto){
        Employee employee = EmployeeMapper.maptoEmployee(employeeDto);
        Employee CreateEmployee = employeeRepo.save(employee);
        return EmployeeMapper.maptoEmployeeDto(CreateEmployee);
    }

    @Override
    public EmployeeDto GetEmployeeById(Long employeeId){
        Employee employee = employeeRepo.findById(employeeId).
                orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given id: "+ employeeId));
        return EmployeeMapper.maptoEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> GetAllEmployee(){
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.maptoEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto UpdateEmployee(Long employeeId, EmployeeDto updateEmployee) {
       Employee employee = employeeRepo.findById(employeeId).orElseThrow(
               ()-> new ResourceNotFoundException("Employee id not exists"));
       employee.setFirstName(updateEmployee.getFirstName());
       employee.setLastName(updateEmployee.getLastName());
       employee.setEmail(updateEmployee.getEmail());

       Employee updatedEmployee = employeeRepo.save(employee);
        return EmployeeMapper.maptoEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee id not exists"));
        employeeRepo.deleteById(employeeId);
    }

}

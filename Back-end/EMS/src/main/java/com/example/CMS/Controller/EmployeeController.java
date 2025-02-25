package com.example.CMS.Controller;

import com.example.CMS.Dto.EmployeeDto;
import com.example.CMS.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> CreateEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.CreateEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee , HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> GetEmployeeById(@PathVariable Long employeeId){
        EmployeeDto employeeDto = employeeService.GetEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto , HttpStatus.OK);
    }

    // get all employee
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> GetAllEmployee(){
        List<EmployeeDto> employees = employeeService.GetAllEmployee();
        return new ResponseEntity<>(employees , HttpStatus.OK);
    }

    // update employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId , @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employee = employeeService.UpdateEmployee(employeeId,updatedEmployee);
        return new ResponseEntity<>(employee , HttpStatus.OK);
    }

    //Delete employee
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}

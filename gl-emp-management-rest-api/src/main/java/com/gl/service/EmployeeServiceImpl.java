package com.gl.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gl.dto.EmployeeDto;
import com.gl.entity.Employee;
import com.gl.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public Employee addNewEmployee(EmployeeDto empDto) {
		Employee employee = Employee.builder()
									.firstName(empDto.getFirstName())
									.lastName(empDto.getLastName())
									.email(empDto.getEmail())
									.build();
		
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> allEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No Employee Found"));
	}

	@Override
	public Employee updateEmployee(Integer id, EmployeeDto empDto) {
		Optional<Employee> empFromDb = employeeRepo.findById(id);
		if(empFromDb.isPresent()) {
			Employee employee = empFromDb.get();
			employee.setFirstName(empDto.getFirstName());
			employee.setLastName(empDto.getLastName());
			employee.setEmail(empDto.getEmail());
			
			return employeeRepo.save(employee);
		}
		
		throw new NoSuchElementException("No Employee Found");
	}

	@Override
	public String deleteEmployee(Integer id) {
		Optional<Employee> empFromDb = employeeRepo.findById(id);
		
		if(empFromDb.isPresent()) {
			employeeRepo.deleteById(id);
			return "Deleted employee id - " + id;
		}
		
		throw new NoSuchElementException("No Employee Found");
	}

	@Override
	public List<Employee> getEmployeesByFirstName(String firstName) {
		return employeeRepo.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> allEmployeesOrderByFirstName(Direction order) {
		return employeeRepo.findAll(Sort.by(order, "firstName"));
	}

}

package co.edureka.ems.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import co.edureka.ems.dto.Employee;

public  class employeeDAO {
	
	private HibernateTemplate session;
	
	public void setTemplate(HibernateTemplate template) {
		this.session= template;
	}

	@Transactional(readOnly =false)
	public void saveEmployee(Employee emp) {
		session.save(emp);
	}

	public Employee getById(int id){  
	    Employee emp = (Employee)session.get(Employee.class, id);  
	    return emp;  
	} 
	
}



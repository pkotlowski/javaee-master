/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jeedemo.service;

import com.example.jeedemo.domain.Department;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piotr
 */
@Stateless
public class DepartmentManager {
    
    	@PersistenceContext
	EntityManager em;
        
        public void addDepartment(Department department){
            department.setId(null);
            em.persist(department);
        }
        
        @SuppressWarnings("unchecked")	//wymuszenie poprawnosci na kompilatorze
	public List<Department> getAllDepartments() {
		return em.createNamedQuery("department.all").getResultList();
	}
        
}

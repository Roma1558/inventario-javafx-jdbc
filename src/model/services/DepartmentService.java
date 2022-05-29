package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll() {
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Livros"));
		list.add(new Department(2, "inform�tica"));
		list.add(new Department(3, "Moda"));
		list.add(new Department(4, "Eletr�nicos"));
		return list;
	}
}

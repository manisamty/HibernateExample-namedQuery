package com.infotech.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class NemedQueryInHibernateTest {

	public static void main(String[] args) {
		//getTotalSalaryOfEmployeesByDept();
		getEmployeeById();
	}
	
	private static void getEmployeeById() {
		int empId=3;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Employee> query = session.getNamedQuery("Employee.byId");
			query.setParameter("empId", empId);
			Employee employee = query.uniqueResult();
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();
		}
	}


	private static void getTotalSalaryOfEmployeesByDept() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Object[]> query = session.getNamedQuery("getTotalSalarOfEmployeesByDept");
			List<Object[]> list = query.list();
			for (Object[] objects : list) {
				String departmentName=(String)objects[0];
				Double totalSalByDept = (Double)objects[1];
				System.out.println("Department Name:"+departmentName);
				System.out.println("Total Sal By Dept:"+totalSalByDept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();
		}
	}

}

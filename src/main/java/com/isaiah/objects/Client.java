package com.isaiah.objects;

import com.isaiah.objects.Student;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class Client {
	
	Configuration configuration = new Configuration().configure(); //This line was added right I added the Hibernate annotations to the Student class.
	
	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	
	Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
	
	SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
	
	//Session session = null;
	//Transaction transaction = null;
	
	/**
	 * I've noticed we have to open the transaction and session over and over again so this should make things a little easier.
	 * Once a session object has been closed, it cannot be opened again so this wouldn't work.
	 */
	/**
	private void OpenSessionAndTransaction() { 
		
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
	}
	*/
	
	/*
	public void createStudent(Student st) {
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		
		session.save(st); //We uses the session to save the object to the db.
		transaction.commit(); //We commit the action to the db.
		session.close(); //We then close the session
	}
	
	public void createStudentByValue(int rollNo, String name) {
		createStudent(new Student(rollNo, name));
		
	}
	*/
	
	public void createStudent(Student st) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			
			session.save(st);
			transaction.commit();
			
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			
			
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	
//	public Student readStudent(int rollNo) {
//		Session session = sessionfactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		
//		Student st = session.get(Student.class, rollNo);
//		
//		session.close();
//		return st;
//	}
	
	public Student readStudent(int rollNo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Student st = null;
		
		try {
			transaction = session.beginTransaction();
			st = session.get(Student.class, rollNo);
			
			
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return st;
	}
	
	
	
	
//	public void updateStudentById(int rollNo, String updateName) {
//		Session session = sessionfactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		
//		Student st = session.get(Student.class, rollNo);
//		st.setName(updateName);
//		session.update(st);
//		transaction.commit();
//		
//		session.close();
//		
//	}
	
	public void updateStudentById(int rollNo, String updateName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Student st = session.get(Student.class, rollNo);
			st.setName(updateName);
			session.update(st);
			transaction.commit();
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
//	public void deleteStudentByRollNo(int rollNo) {
//		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		
//		Student st = session.get(Student.class, rollNo);
//		session.delete(st);
//		transaction.commit();
//		session.close();
//		
//	}
	
	
	public void deleteStudentByRollNo(int rollNo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Student st = session.get(Student.class, rollNo);
			transaction.commit();
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	public List<Student> getAllStudents() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Student> studentList = null;
		
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Student"); //For this line, make sure that from <Classname> is the name of the entity class, NOT the table itself.
			studentList = query.list();
			transaction.commit();
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		
		return studentList;
	}
	
	
	public Student hibernateTestQuery() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Student st = null;
		
		try {
			transaction = session.beginTransaction();
			//Query query = session.createQuery("from Student s where s.rollNo= 2");
			//Query query = session.createQuery("from Student s where s.name='John Doe'");
			Query query = session.createQuery("from Student where rollNo=:rollNo");
			query.setParameter("rollNo", 2);
			List<Student> stList= query.list();
			st = stList.getFirst();
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		
		
		return st;
	}
	
	
	public List<Student> nativeSQLTestQuery() {
		List<Student> studentList = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			NativeQuery query = session.createNativeQuery("Select * from studenttable"); //We use a native query if we want to
			query.addEntity(Student.class);
			
			studentList = query.list();
			transaction.commit();
		} catch(Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return studentList;
	}
	

}

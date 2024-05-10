package com.isaiah.simplilearnproject;

import java.util.List;

import com.isaiah.objects.*;

/**
 * Hello world!
 *
 */
public class App 
{

	static Client client;
	public static void main( String[] args ) {
		System.out.println("Hello World from Simplilearnproject!");

		/*
		 * Declaring and initializing the client object was what caused Hibernate to create the table 
		 */
		client = new Client();//I assume by initialing the client object, this is what will make hibernate work.

		//client.createStudentByValue(3, "James Doe"); //Using the hibernate client to add a student to the db
		Student s1 = client.readStudent(4);
		System.out.println("Student rollNo 4: " + s1);

		//client.updateStudentById(4, "Joe");
		s1 = client.readStudent(4);
		System.out.println("Student rollNo 4 after update: " + s1);

		//client.deleteStudentByRollNo(3);



		List<Student> stList = client.getAllStudents();
		for(Student st:stList) {
			System.out.println(st);
		}

		Student fromHQL = client.hibernateTestQuery();
		System.out.println(fromHQL);
		
		
		List<Student> studentsFromNativeSQL = client.nativeSQLTestQuery();
		
		for(Student st: studentsFromNativeSQL) {
			System.out.println(st);
		}
		

	}
}

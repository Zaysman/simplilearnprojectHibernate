package com.isaiah.objects;

import jakarta.persistence.*;

@Entity //Every persistent POJO class is an entity and is declared using the @Entity annotation (at class level)
@Table(name = "studenttable") //Defines the table the object is supposed to use. Hibernate will use the class name as the table anme by default
public class Student {
	
	@Id //Declares the identifier property of this entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) //This means we'll use the database to determine the ID for the object
	@Column(name = "rollNo") // @Column annotation specifies the details of the column for this property or field. It it isn't specified, property name will be used as the column name by default.
	private int rollNo;
	
	@Column(name = "name")
	private String name;
	
	public Student() {
		this(-1, "default name");
	}
	
	public Student(int rollNo, String name) {
		super();
		this.rollNo = rollNo;
		this.name = name;
	}
	
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + "]";
	}
	
	

}

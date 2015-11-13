package base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.expression.ParseException;

import domain.StudentDomainModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student_Test {
	
	private static StudentDomainModel stu1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try{
			dDate = new SimpleDateFormat("yyy-MM-dd").parse("1994-10-13");
		} catch (ParseException e) {
			e.printStackTrace(); }
		
		stu1 = new StudentDomainModel(stu1);
		stu1.setFirstName("George");
		stu1.setMiddleName("Angelo");
		stu1.setLastName("Kanakis");
		stu1.getStudentID();
		stu1.setDOB(dDate);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		StudentDomainModel stu;
		StudentDAL.deleteStudent(stu1.getStudentID());
		stu = StudentDAL.getStudent(stu1.getStudentID());
		assertNull("The student Shouldn't have been there before", stu);
	}

	@Test
	public void AddStudentTest() {
		StudentDomainModel stu;
		stu = StudentDAl.getStudent(stu1.getStudentID());
		assertNull("The person should not be in the database already", stu);
		
		
	}

}

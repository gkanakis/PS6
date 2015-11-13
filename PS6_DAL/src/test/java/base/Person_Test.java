package base;
	
	import static org.junit.Assert.*;

	import org.junit.After;
	import org.junit.AfterClass;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Test;
	import org.springframework.expression.ParseException;

	import domain.PersonDomainModel;

	import java.text.SimpleDateFormat;
	import java.util.Date;
	
public class Person_Test {
	private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Date dDate = null;
		try{
			dDate = new SimpleDateFormat("yyy-MM-dd").parse("1994-10-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per1 = new PersonDomainModel();
		per1.setFirstName("George");
		per1.setLastName("Kanakis");
		per1.setBirthday(dDate);
		per1.setCity("Newark");
		per1.setPostalCode(19711);
		per1.setStreet("145 Haines Street");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public  void setUp() throws Exception{
	}
	
	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;
		PersonDAL.deletePerson(per1.getPerson());
		per = PersonDAL.getPerson(per1.getPerson());
		assertNull("the person should be in the database already", per);
	}
	
	@Test
	public void AddPersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPerson());
		assertNull("The person should not exist in database already");
		PersonDAL.addPerson(per1);
		
		per = PersonDAL.getPerson(per1.getPerson());
		System.out.println(per1.getPerson() + "added to database");
		assertNotNull("This person has been added to the database");
	}
	
	@Test
	public void DeletePersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPerson());
		assertNull("The person shouldn't have been in the database", per);
		
		PersonDAL.deletePerson(per1.getPerson());
		per = PersonDAL.getPerson(per1.getPerson());
		assertNull("The Person shouldn't exsist in the database already",per);
	}
}

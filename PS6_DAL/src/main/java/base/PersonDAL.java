package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction add = null;
		int personID = 0;
		try {
			add = session.beginTransaction();
			session.save(per);
			add.commit(); }
		catch (HibernateException e){
			if (add != null)
				add.rollback();
			e.printStackTrace(); }
		finally {
			session.close();
		}
		return null;
	}

	public static ArrayList<PersonDomainModel> getPersons() {
		//PS6 - please implement	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction Tget = null;
		ArrayList<PersonDomainModel> personsOrig = new ArrayList<PersonDomainModel>();
		try {
			Tget = session.beginTransaction();
			
			List persons = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				personsOrig.add(per);
			}
			Tget.commit();
		}
		catch (HibernateException e) {
			if (Tget != null)
				Tget.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return personsOrig;
	}

	public static PersonDomainModel getPerson(UUID perID) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction getPer = null;
		PersonDomainModel perGet = null;
		
		try{
			getPer = session.beginTransaction();
			
			Query query = session.createQuery("from PersonDomainModel where perID = :id");
			query.setParameter("id", perID.toString());
			
			List<?> list = query.list();
			perGet = (PersonDomainModel)list.get(0);
		
			getPer.commit();
		}
		catch (HibernateException e) {
			if(getPer != null)
				getPer.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return perGet;
	}

	public static void deletePerson(UUID perID) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction delPer = null;
		PersonDomainModel perGet = null;
		
		try{
			delPer = session.beginTransaction();
			
			PersonDomainModel per = (PersonDomainModel) session.get(PersonDomainModel.class,perID);
			session.delete(per);
			
			delPer.commit();
		}
		catch (HibernateException e){
			if (delPer != null)
				delPer.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		//PS6 - please implement
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction updatePer = null;
		PersonDomainModel perGet = null;
		
		try {
			updatePer = session.beginTransaction();
			session.update(per);
			
			updatePer.commit();
		}
		catch (HibernateException e) {
			if (updatePer != null)
				updatePer.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return per;
	}
}














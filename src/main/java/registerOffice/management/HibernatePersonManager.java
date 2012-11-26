package registerOffice.management;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import registerOffice.businessObjects.persons.Person;

public class HibernatePersonManager implements ManagerInterface<Person>{

	private Session session;
	
	public HibernatePersonManager( Session session)
	{
		this.session = session;
	}
	
	@Override
	public Person get(int id) {
		
		List<Person> result = this.session.getNamedQuery("allPersonsById")
				.setInteger("Id",id).list();
		
		return result.get(0);
	}

	@Override
	public List<Person> getAll() {
		
		return (List<Person>)this.session.getNamedQuery("allPersons").list();
	}

	@Override
	public boolean save(Person obj) {
		try{
			this.session.beginTransaction();
			this.session.save(obj);
			
			session.beginTransaction().commit();
			return true;
		}catch(Exception ex)
		{
			return false;
		}
	}
////////////////////////////////////////////////////////////
	@Override
	public boolean addPerson(Person obj) {
		try{
			this.session.beginTransaction();
			this.session.save(obj);
			
			session.beginTransaction().commit();
			return true;
		}catch(Exception ex)
		{
			return false;
		}
	}
//////////////////////////////////////////////////////////////////////////
	@Override
	public boolean delete(Person obj) {
		try{
			this.session.beginTransaction();
			this.session.getNamedQuery("deleteFromPerson")
			.setInteger("id", obj.getId())
			.executeUpdate();
			this.session.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

}

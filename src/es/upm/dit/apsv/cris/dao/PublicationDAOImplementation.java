package es.upm.dit.apsv.cris.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.apsv.cris.model.Publication;

public class PublicationDAOImplementation implements PublicationDAO{

	private static PublicationDAOImplementation instance = null;

	private PublicationDAOImplementation() {}

	public static PublicationDAOImplementation getInstance() {
		if (null == instance) {
			instance = new PublicationDAOImplementation();
		}
		return instance;
	}
	@Override
	public Publication create(Publication publication) {
		Session session = SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.save(publication);
		session.getTransaction().commit();
		}catch(Exception e) {
		}finally {
		session.close();
		}
		return publication;
	}

	@Override
	public Publication read(String publicationId) {
		Session session = SessionFactoryService.get().openSession();
		Publication r = null;
		try {
		session.beginTransaction();
		r = session.get(Publication.class, publicationId);
		session.getTransaction().commit();
		}catch(Exception e) {		
		}finally {
		session.close();
		}
		return r;
	}

	@Override
	public Publication update(Publication publication) {
		Session session = SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.saveOrUpdate(publication);
		session.getTransaction().commit();
		}catch(Exception e) {		
		}finally {
		session.close();
		}
		return publication;
	}

	@Override
	public Publication delete(Publication publication) {
		Session session = SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.delete(publication);
		session.getTransaction().commit();
		}catch(Exception e) {		
		}finally {
		session.close();
		}
		return publication;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publication> readAll() {
		Session session = SessionFactoryService.get().openSession();
		List<Publication> l = null;
		try {
		session.beginTransaction();
		l =(List<Publication>)session.createQuery("from Publication").getResultList();
		session.getTransaction().commit();
		}catch(Exception e) {		
		}finally {
		session.close();
		}
		return l;
	}

	@Override
	public List<Publication> readAllPublications(String researcherId) {
		List<Publication> l = new ArrayList<Publication>();
		for (Publication r : this.readAll())
			if (r.getAuthors().indexOf(researcherId) > -1) 
					l.add(r);
		return l;
	}

}

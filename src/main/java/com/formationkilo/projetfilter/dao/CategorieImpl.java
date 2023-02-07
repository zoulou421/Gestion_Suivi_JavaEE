package com.formationkilo.projetfilter.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.formationkilo.projetfilter.config.HibernateUtil;
import com.formationkilo.projetfilter.entities.Categorie;

public class CategorieImpl implements ICategorie{
	
	private Transaction transaction;
	private Session session;
	

	public CategorieImpl() {
		session= HibernateUtil.getSessionFactory().openSession();
	}
	

	@Override
	public int ajouter(Categorie categorie) {
		try {
			transaction=session.beginTransaction();
			session.save(categorie);
			transaction.commit();
		    return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int modifier(Categorie categorie) {
		try {
			transaction=session.beginTransaction();
			session.merge(categorie);
			transaction.commit();
		    return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> listerTout() {
			try {
				
			    return session.createQuery("select c from Categorie c").getResultList();
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public Categorie listerByRef(String ref) {
		try {
			return session.get(Categorie.class, ref);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
	     }	
	}

	@Override
	public int supprimer(String ref) {
		try {
			transaction=session.beginTransaction();
		    //session.delete(session.get(Categorie.class, ref));
			session.delete(listerByRef(ref));
			transaction.commit();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
	     }	
	}

}

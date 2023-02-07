package com.formationkilo.projetfilter.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.formationkilo.projetfilter.config.HibernateUtil;
import com.formationkilo.projetfilter.entities.User;

public class UserImpl implements IUser{

	private Transaction transaction;
	private Session session;
	
	
	public UserImpl() {
		session= HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public int ajouter(User user) {
		try {
			transaction=session.beginTransaction();
			session.save(user);
			transaction.commit();
		    return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int modifier(User user) {
		try {
			transaction=session.beginTransaction();
			session.merge(user);
			transaction.commit();
		    return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listerTout() {
		try {
			
		    return session.createQuery("select user from User user").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User listerByRef(int id) {
		try {
			return session.get(User.class, id);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
	     }	
	}

	@Override
	public int supprimer(int id) {
		try {
			transaction=session.beginTransaction();
		    //session.delete(session.get(Produit.class, id));
			session.delete(listerByRef(id));
			transaction.commit();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
	     }	
	}

	@Override
	public User logon(String email, String password) {
		try {
			return (User) session.createQuery("SELECT u FROM User u WHERE u.email LIKE :em AND u.password LIKE :pass")
					.setParameter("em", email)
					.setParameter("pass", password)
					.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

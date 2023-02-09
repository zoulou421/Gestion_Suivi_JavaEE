package com.formationkilo.compte.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.formationkilo.compte.entities.User;
import com.formationkilo.conf.LinkDbUtil;
public class UserImpl implements IUser{

	private Transaction transaction=null;
	private Session session;

	public UserImpl() {
		session= LinkDbUtil.getSessionFactory().openSession();
	}

	
	public void saveUser(User user) {
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
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

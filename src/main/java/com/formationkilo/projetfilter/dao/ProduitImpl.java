package com.formationkilo.projetfilter.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.formationkilo.projetfilter.config.HibernateUtil;
import com.formationkilo.projetfilter.entities.Produit;

public class ProduitImpl implements IProduit {

	
	private Session session;
	private Transaction transaction;
	
	public ProduitImpl() {
		session=HibernateUtil.getSessionFactory().openSession();
	}	

	@Override
	public int ajouter(Produit produit) {
		try {
			transaction=session.beginTransaction();
			session.save(produit);
			transaction.commit();
		    return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
		

	@Override
	public int modifier(Produit produit) {
		try {
			transaction=session.beginTransaction();
			session.merge(produit);
			transaction.commit();
		    return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produit> listerTout() {
		try {
			
			return session.createQuery("select produit from Produit produit").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
	     }	
	}

	@Override
	public Produit listerByRef(String ref) {
		try {
			return session.get(Produit.class, ref);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
	     }	
	}

	@Override
	public int supprimer(String ref) {
		try {
			transaction=session.beginTransaction();
		    //session.delete(session.get(Produit.class, ref));
			session.delete(listerByRef(ref));
			transaction.commit();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
	     }	
		
		
	}
}

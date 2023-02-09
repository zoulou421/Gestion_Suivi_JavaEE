package com.formationkilo.compte.dao;

import java.util.List;

import com.formationkilo.compte.entities.User;

public interface IUser {
	  //ajouter v1
	   public void saveUser(User user);
	   //ajouter v2
		public int ajouter(User user);
	    //modifier
		public int modifier(User user);
		//lister Tout
		public List<User> listerTout();
		//lister par id
		public User listerByRef(int id);
		//Supprimer
		public int supprimer(int id);

		public User logon(String email, String password);
}

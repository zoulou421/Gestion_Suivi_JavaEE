package com.formationkilo.projetfilter.dao;

import java.util.List;

import com.formationkilo.projetfilter.entities.Categorie;
import com.formationkilo.projetfilter.entities.Produit;

public interface ICategorie {

	//ajouter
		public int ajouter(Categorie categorie);
	    //modifier
		public int modifier(Categorie categorie);
		//lister Tout
		public List<Categorie> listerTout();
		//lister par id=ref
		public Categorie listerByRef(String ref);
		//Supprimer
		public int supprimer(String ref);
}

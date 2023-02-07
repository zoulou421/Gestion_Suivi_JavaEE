package com.formationkilo.projetfilter.dao;

import java.util.List;

import com.formationkilo.projetfilter.entities.Produit;

public interface IProduit {

	//ajouter
	public int ajouter(Produit produit);
    //modifier
	public int modifier(Produit produit);
	//lister Tout
	public List<Produit> listerTout();
	//lister par id=ref
	public Produit listerByRef(String ref);
	//Supprimer
	public int supprimer(String ref);
	
	
}

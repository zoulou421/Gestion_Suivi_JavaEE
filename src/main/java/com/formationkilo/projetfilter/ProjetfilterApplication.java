package com.formationkilo.projetfilter;

import com.formationkilo.projetfilter.dao.IProduit;
import com.formationkilo.projetfilter.dao.IUser;
import com.formationkilo.projetfilter.dao.ProduitImpl;
import com.formationkilo.projetfilter.dao.UserImpl;
import com.formationkilo.projetfilter.entities.Produit;
import com.formationkilo.projetfilter.entities.User;

public class ProjetfilterApplication {

	public static void main(String[] args) {
		
              IUser userdao= new UserImpl();
              User user = new User();
              user.setNom("admin");
              user.setPrenom("Bonevy");
              user.setPassword("12345678");
              user.setEmail("admin@formationkilo.com");
              userdao.ajouter(user);
              
              IProduit prodao= new ProduitImpl();
              Produit prod=new Produit();
              prod.setRef("A05");
              prod.setNom("Pantaton Adidas");
              prod.setQtStock(10);
              prod.getCategorie();
              prodao.ajouter(prod);
              
              
              
              
	}

}

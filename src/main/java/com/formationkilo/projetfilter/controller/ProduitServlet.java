package com.formationkilo.projetfilter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formationkilo.projetfilter.dao.CategorieImpl;
import com.formationkilo.projetfilter.dao.ICategorie;
import com.formationkilo.projetfilter.dao.IProduit;
import com.formationkilo.projetfilter.dao.IUser;
import com.formationkilo.projetfilter.dao.ProduitImpl;
import com.formationkilo.projetfilter.dao.UserImpl;
import com.formationkilo.projetfilter.entities.Categorie;
import com.formationkilo.projetfilter.entities.Produit;
import com.formationkilo.projetfilter.entities.User;

@WebServlet(name = "produit", value = "/Produit")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IProduit produitdao;
	private ICategorie categoriedao;
	private IUser userdao;
	public ProduitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		produitdao = new ProduitImpl();
		categoriedao = new CategorieImpl();
		userdao= new UserImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//String ref = (req.getParameter("ref").toString()!=null)?req.getParameter("ref").toString():null;
		if(req.getParameter("ref")!=null) {
			String ref = req.getParameter("ref").toString();
			 produitdao.supprimer(ref);
			
		}
		
		
		/*if(ref!=null){

			Produit p = produitdao.listerByRef(ref);
			produitdao.modifier(p);
		}*/
		
		if(req.getSession().getAttribute("usersession") == null) {
			resp.sendRedirect("Login");
		} else {
		
			List<Produit> produits = produitdao.listerTout();
			req.setAttribute("list_produits", produits);
			
			List<Categorie> categories = categoriedao.listerTout();
			req.setAttribute("list_categories", categories);
			
			List<User> users = userdao.listerTout();
			req.setAttribute("list_users", users);
			
			req.getRequestDispatcher("WEB-INF/produits/list.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ref = req.getParameter("ref").toString();
		String nom = req.getParameter("nom").toString();
		double qt = Double.parseDouble(req.getParameter("qt").toString());
		String idCategorie = req.getParameter("categorie").toString();
		Categorie categorie = categoriedao.listerByRef(idCategorie);
		User user = (User)req.getSession().getAttribute("usersession");
		
		Produit produit = new Produit();
		produit.setRef(ref);
		produit.setNom(nom);
		produit.setQtStock(qt);
		produit.setCategorie(categorie);
		produit.setId(user);
		
		produitdao.ajouter(produit);
		resp.sendRedirect("Produit");
	}
}


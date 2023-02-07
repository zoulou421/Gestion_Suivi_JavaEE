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
import com.formationkilo.projetfilter.dao.ProduitImpl;
import com.formationkilo.projetfilter.entities.Categorie;
import com.formationkilo.projetfilter.entities.Produit;
import com.formationkilo.projetfilter.entities.User;

/**
 * Servlet implementation class CategorieServlet
 */
@WebServlet("/Categorie")
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ICategorie categoriedao;   
    IProduit produitdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		categoriedao = new CategorieImpl();
		produitdao = new ProduitImpl();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("usersession") == null) {
			response.sendRedirect("Login");
		} else {
		
			List<Categorie> categories = categoriedao.listerTout();
			request.setAttribute("list_categories", categories);
			
			List<Produit> produits = produitdao.listerTout();
			request.setAttribute("list_produits", produits);
			request.getRequestDispatcher("WEB-INF/categorie/list.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ref = request.getParameter("ref").toString();
		String nom = request.getParameter("nom").toString();
		
		Categorie categorie = new Categorie();
		categorie.setRef(ref);
		categorie.setNom(nom);
		User user = (User)request.getSession().getAttribute("usersession");
		categorie.setId(user);
		
		categoriedao.ajouter(categorie);
		response.sendRedirect("Categorie");
	}

}

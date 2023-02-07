package com.formationkilo.projetfilter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formationkilo.projetfilter.dao.IProduit;
import com.formationkilo.projetfilter.dao.IUser;
import com.formationkilo.projetfilter.dao.ProduitImpl;
import com.formationkilo.projetfilter.dao.UserImpl;
import com.formationkilo.projetfilter.entities.Produit;
import com.formationkilo.projetfilter.entities.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUser userdao;
    private IProduit pdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userdao = new UserImpl();
		pdao= new ProduitImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("usersession") == null) {
			response.sendRedirect("Login");
		} else {
		
			String page = request.getParameter("page").toString();
			if(page.equals("list")) {
				List<User> users = userdao.listerTout();
				request.setAttribute("list_users", users);
				
				List<Produit> produits = pdao.listerTout();
				request.setAttribute("list_produits", produits);
				
				request.getRequestDispatcher("WEB-INF/users/list.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("WEB-INF/users/profil.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom").toString();
		String prenom = request.getParameter("prenom").toString();
		String email = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();
		int etat = 0;
		User user = new User();
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setPassword(password);
		user.setEtat(etat);
		
		userdao.ajouter(user);
		response.sendRedirect("User?page=list");
	}

}
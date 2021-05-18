package com.demo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.beans.Category;
import com.demo.beans.Etudiant;
import com.demo.beans.Livre;
import com.demo.dao.DaoFactory;
import com.demo.dao.LivreDaoImpl;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = { "/Home", "/" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LivreDaoImpl LivreImpl = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		DaoFactory factory = DaoFactory.getInstance();
		LivreImpl = (LivreDaoImpl) factory.getLivreDaoImpl();
	}
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String category = request.getParameter("category");
			List<Livre> livres = (ArrayList<Livre>)LivreImpl.getLivres_Disponible();
			String auteur = request.getParameter("auteur");
			if(category == null || category.equals("all")) {
				if(auteur != null && !auteur.equals("")) {
					List<Livre> newListe = livres
							.stream()
							.filter(lv -> lv.getLivre_auteur().toLowerCase().equals(auteur.toLowerCase()))
							.sorted(Comparator.comparing(Livre::getDate_creation))
							.collect(Collectors.toList());
					request.setAttribute("category","all");
					request.setAttribute("livres", newListe);
				}else {
					request.setAttribute("category", "all");
					request.setAttribute("livres", livres);
				}
				
			}else {
				int id = Integer.parseInt(category);
				List<Livre> newListe = livres
					.stream()
					.filter(lv -> lv.getCategory().getCategory_id() == id)
					.sorted(Comparator.comparing(Livre::getLivre_auteur))
					.collect(Collectors.toList());
				
				if(auteur != null && !auteur.equals("")) {
					 newListe = newListe
							.stream()
							.filter(lv -> lv.getLivre_auteur().equals(auteur))
							.sorted(Comparator.comparing(Livre::getDate_creation))
							.collect(Collectors.toList());
				}
				request.setAttribute("category",category);
				request.setAttribute("livres", newListe);
			}
			
			ArrayList<Category> categories = (ArrayList<Category>)LivreImpl.getCategories();
			request.setAttribute("categories", categories);
			this.getServletContext().getRequestDispatcher("/Public/Home.jsp").forward(request, response);
		
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Etudiant et = (Etudiant) session.getAttribute("Etudiant");
		if( et != null) {
			if(req.getParameter("reserve_id") != null){
				int id = Integer.parseInt(req.getParameter("reserve_id"));
				LivreImpl.emprunte(et.getEtudiant_id(),id);
				req.setAttribute("message", "le livre est supprimer avec succées  ");
			}
			doGet(req,resp);
		}else {
			resp.sendRedirect("Auth");
		}
	}
	
	

}

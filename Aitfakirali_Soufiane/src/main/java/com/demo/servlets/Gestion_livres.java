package com.demo.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.demo.beans.Category;
import com.demo.beans.Etudiant;
import com.demo.beans.Livre;
import com.demo.dao.DaoFactory;
import com.demo.dao.LivreDaoImpl;


/**
 * Servlet implementation class Livres_gestion
 */
@WebServlet("/Livres")
@MultipartConfig( location = "c:/Images", maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class Gestion_livres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LivreDaoImpl LivreImpl = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gestion_livres() {
        super();
		DaoFactory factory = DaoFactory.getInstance();
		LivreImpl = (LivreDaoImpl) factory.getLivreDaoImpl();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Etudiant et = (Etudiant) session.getAttribute("Etudiant");
		if( et != null) {
			if(et.getEtudiant_role() != null && et.getEtudiant_role().equals("ADMIN")) {
				if(request.getParameter("delete_id") != null){
					int id = Integer.parseInt(request.getParameter("delete_id"));
					LivreImpl.Delete(id);
					request.setAttribute("message", "le livre est supprimer avec succées  ");
				}
				ArrayList<Livre> livres = (ArrayList<Livre>)LivreImpl.getAll();
				if(request.getParameter("filter") != null && request.getParameter("filter").equals("filtered")) {
					List<Livre> newList = livres
					.stream()
					.filter(livre -> livre.isEmprunte())
					.sorted(Comparator.comparing(Livre::getLivre_auteur))
					.collect(Collectors.toList());
					request.setAttribute("filtered", true);
					request.setAttribute("livres", newList);
				}else {
					request.setAttribute("filtered", false);
					request.setAttribute("livres", livres);
				}
				
				ArrayList<Category> categories = (ArrayList<Category>)LivreImpl.getCategories();
				request.setAttribute("categories", categories);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Livres.jsp").forward(request, response);
				
			}else {
				response.sendRedirect("Auth");
			}
		}else {
			response.sendRedirect("Auth");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Etudiant et = (Etudiant)session.getAttribute("Etudiant");
		if(et != null) {
			if(et.getEtudiant_role() != null && et.getEtudiant_role().equals("ADMIN")) {
				if(request.getParameter("retour_id") != null) {
					int id = Integer.parseInt((String)request.getParameter("retour_id"));
					LivreImpl.retour(id);
				}else {
					if(isValid(request)) {
						String auteur = request.getParameter("auteur");
						String titre = request.getParameter("titre");
						Date date = Date.valueOf(request.getParameter("date"));
						String description = request.getParameter("description");
						int category = Integer.parseInt(request.getParameter("category"));
						Livre lv = new Livre();
						lv.setLivre_auteur(auteur);
						lv.setLivre_titre(titre);
						lv.setDate_creation(date);
						lv.setDescription(description);
						lv.setCategory(LivreImpl.getCategory(category));
						LivreImpl.Save(lv);
					}
				}
				
			}
		}
		doGet(request,response);
		
	}
	
	boolean isValid(HttpServletRequest request) {
		String auteur = request.getParameter("auteur");
		String titre = request.getParameter("titre");
		if(auteur == null || auteur.equals("")) {
			return false;
		}
		if(titre == null || titre.equals("")) {
			return false;
		}
		if(request.getParameter("category") == null || request.getParameter("category").equals("")) {
			 return false;
		}
		if(request.getParameter("date") == null || request.getParameter("date").equals("")) {

			return false;
		}
		return true;
		
	}

}

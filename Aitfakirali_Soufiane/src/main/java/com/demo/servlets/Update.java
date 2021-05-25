package com.demo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.beans.Etudiant;
import com.demo.dao.DaoFactory;
import com.demo.dao.EtudiantDaoManager;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private EtudiantDaoManager EtudiantImpl;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        DaoFactory factory = DaoFactory.getInstance();
		EtudiantImpl = (EtudiantDaoManager) factory.getEtudianDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Etudiant u=(Etudiant)session.getAttribute("Etudiant");
		if(u != null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/compte.jsp").forward(request, response);
		else
			response.sendRedirect("Auth");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Etudiant u=(Etudiant)session.getAttribute("Etudiant");
		if(u != null){
			if(ValidateInputs(request)) {
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String addresse = request.getParameter("addresse");
				String telephone = request.getParameter("telephone");
				String filiere = request.getParameter("filiere");
				String age = request.getParameter("age");
                u.setEtudiant_nom(nom);
                u.setEtudiant_prenom(prenom);
                u.setEtudiant_email(email);
                u.setEtudiant_telephone(telephone);
                u.setEtudiant_adresse(addresse);
                u.setEtudiant_filiere(filiere);
                try {
                	 u.setEtudiant_age(Integer.parseInt(age));
                }catch(NumberFormatException e){
                	
                }
				if(EtudiantImpl.Update(u)) {
					session.setAttribute("Etudiant", u);
					request.setAttribute("message", "les informations sont mises à jour avec succès");
				}else {
					request.setAttribute("error", "il y a une erreur dans vos informations");
				}
			}else {
				request.setAttribute("error", "les information ne doivent pas etre vide");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/compte.jsp").forward(request, response);
		}
	}
	
	boolean ValidateInputs(HttpServletRequest req){
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String addresse = req.getParameter("addresse");
		String telephone = req.getParameter("telephone");
		if(nom == null || nom.equals("")) {
			return false;
		}
		if(prenom == null || prenom.equals("")) {
			return false;
		}
		if(email == null || email.equals("")) {
			if(EtudiantImpl.isExist(email)) {
				req.setAttribute("error", "la addresse email "+email+" est déja existe");
				return false;
			}
		}
		if(addresse == null || addresse.equals("")) {
			return false;
		}
		if(telephone == null || telephone.equals("")) {
			return false;
		}
		
		return true;
	}

}

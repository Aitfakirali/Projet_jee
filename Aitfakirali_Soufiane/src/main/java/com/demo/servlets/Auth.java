package com.demo.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.beans.Etudiant;
import com.demo.dao.DaoFactory;
import com.demo.dao.EtudiantDaoImpl;

/**
 * Servlet implementation class AuthEtudiant
 */
@WebServlet(urlPatterns={"/Auth","/register"})
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudiantDaoImpl EtudiantImpl = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Auth() {
		super();
		DaoFactory factory = DaoFactory.getInstance();
		EtudiantImpl = (EtudiantDaoImpl) factory.getEtudianDaoImpl();
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/Auth.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String[] path = url.split("/");
		if(path[2].equals("Auth")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Etudiant et = null;
			if ((et = EtudiantImpl.Auth(email, password)) != null) {
				HttpSession session = request.getSession();
				session.setAttribute("Etudiant", et);
				response.sendRedirect("Home");
			} else {
				request.setAttribute("email", email);
				request.setAttribute("autherror", "le mot de passe ou l'email est incorrect");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Auth.jsp").forward(request, response);
			}
		}else {
			if(ValidateInputs(request)) {
				Etudiant et = new Etudiant();
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String addresse = request.getParameter("addresse");
				String telephone = request.getParameter("telephone");
				String filiere = request.getParameter("filiere");
				String age = request.getParameter("age");
                et.setEtudiant_nom(nom);
                et.setEtudiant_prenom(prenom);
                et.setEtudiant_email(email);
                et.setEtudiant_password(password);
                et.setEtudiant_telephone(telephone);
                et.setEtudiant_adresse(addresse);
                et.setEtudiant_filiere(filiere);
                try {
                	 et.setEtudiant_age(Integer.parseInt(age));
                }catch(NumberFormatException e){
                	
                }
				if(EtudiantImpl.Save(et)) {
					request.setAttribute("message", "les informations sont saisie avec succes");
				}else {
					request.setAttribute("error", "les informations sont saisie correctement");
				}
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Auth.jsp").forward(request, response);
		}
		
	}
	
	boolean ValidateInputs(HttpServletRequest req){
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
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
		if(password == null || password.equals("")) {
			return false;
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

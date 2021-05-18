package com.demo.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
 * Servlet implementation class gestion_etudiant
 */
@WebServlet("/etudiants")
public class Gestion_etudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudiantDaoImpl EtudiantImpl = null;
	private String[] roles = {"","ADMIN","USER"};
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gestion_etudiant() {
        super();
        DaoFactory factory = DaoFactory.getInstance();
		EtudiantImpl = (EtudiantDaoImpl) factory.getEtudianDaoImpl();
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
		Etudiant et = (Etudiant)session.getAttribute("Etudiant");
		boolean isTrue = false;
		if(et != null) {
			if(et.getEtudiant_role() != null && et.getEtudiant_role().equals("ADMIN")) {
				List<Etudiant> etudiants = EtudiantImpl.getAll();
				request.setAttribute("etudiants", etudiants);
				isTrue = true;
			}
		}
		if(isTrue) {
			request.getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
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
		boolean isTrue = false;
		if(et != null) {
			if(et.getEtudiant_role() != null && et.getEtudiant_role().equals("ADMIN")) {
				if(request.getParameter("role") != null && request.getParameter("etudiant_id") != null) {
					int role_id = Integer.parseInt((String)request.getParameter("role"));
					int etudiant_id = Integer.parseInt((String)request.getParameter("etudiant_id"));
					Etudiant et_2 = EtudiantImpl.getOneByid(etudiant_id);
					if(!et_2.getEtudiant_role().equals(roles[role_id]));
						EtudiantImpl.ChangeRole(etudiant_id, role_id);

				}
				
				if(request.getParameter("role") == null && request.getParameter("delete_id") != null) {
					int delete_id = Integer.parseInt((String)request.getParameter("delete_id"));
					EtudiantImpl.Delete(delete_id);
				}
				List<Etudiant> etudiants = EtudiantImpl.getAll();
				request.setAttribute("etudiants", etudiants);
				isTrue = true;
			}
		}
		if(isTrue) {
			request.getRequestDispatcher("/WEB-INF/etudiants.jsp").forward(request, response);
		}else {
			response.sendRedirect("Auth");
		}
		
	}
		
}


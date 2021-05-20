package com.demo.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.beans.Etudiant;
import com.demo.beans.Livre;
import com.demo.dao.DaoFactory;
import com.demo.dao.EtudiantDaoImpl;
import com.demo.dao.LivreDaoImpl;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LivreDaoImpl LivreImpl = null;
	private EtudiantDaoImpl EtudiantImpl = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();

    }
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DaoFactory factory = DaoFactory.getInstance();
		LivreImpl = (LivreDaoImpl) factory.getLivreDaoImpl();
		EtudiantImpl = (EtudiantDaoImpl) factory.getEtudianDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("livre_id");
		Livre livre = LivreImpl.getOneByid(Integer.parseInt(id));
		Etudiant et = EtudiantImpl.getEtudiantReserveLivre(livre.getLivre_id());
		request.setAttribute("livre", livre);
		request.setAttribute("etudiant", et);
		this.getServletContext().getRequestDispatcher("/Public/Show.jsp").forward(request, response);
	}
	



}

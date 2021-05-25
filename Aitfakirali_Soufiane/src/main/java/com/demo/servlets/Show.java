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
import com.demo.dao.EtudiantDaoManager;
import com.demo.dao.LivreDaoManager;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LivreDaoManager LivreImpl = null;
	private EtudiantDaoManager EtudiantImpl = null;
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
		LivreImpl = (LivreDaoManager) factory.getLivreDaoImpl();
		EtudiantImpl = (EtudiantDaoManager) factory.getEtudianDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("livre_id");
		Livre livre = LivreImpl.getOneByid(Integer.parseInt(id));
		Etudiant et = null;
		if(livre != null)
			et = EtudiantImpl.getEtudiantReserveLivre(livre.getLivre_id());
		request.setAttribute("livre", livre);
		request.setAttribute("etudiant", et);
		this.getServletContext().getRequestDispatcher("/Public/Show.jsp").forward(request, response);
	}
	



}

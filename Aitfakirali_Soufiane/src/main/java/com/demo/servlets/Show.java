package com.demo.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.beans.Livre;
import com.demo.dao.DaoFactory;
import com.demo.dao.LivreDaoImpl;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LivreDaoImpl LivreImpl = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
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
		String id = request.getParameter("livre_id");
		Livre livre = LivreImpl.getOneByid(Integer.parseInt(id));
		request.setAttribute("livre", livre);
		this.getServletContext().getRequestDispatcher("/Public/Show.jsp").forward(request, response);
	}
	



}

package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContatoBeans;
import model.Dao;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insertContato", "/select", "/update", "/deletar" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Dao dao = new Dao();
	ContatoBeans contato = new ContatoBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insertContato")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/deletar")) {
			deletarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.sendRedirect("agenda.jsp");

		// criar objeto lista de contatos
		ArrayList<ContatoBeans> lista = dao.listarContatos();
		for (int i = 0; i < lista.size(); i++) {
			// System.out.println(lista.get(i).getId());
			// System.out.println(lista.get(i).getNome());
			// madar a lista para agenda.jsp
			request.setAttribute("contatos", lista);
			RequestDispatcher listaContatos = request.getRequestDispatcher("agenda.jsp");
			listaContatos.forward(request, response);
		}

	}

	// novo contato.
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar variavesi JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar mdt Inserir Contat0s
		dao.inserirContato(contato);
		// redirecionar para doc agenda.jsp
		response.sendRedirect("main");

	}
	// editar contato //

	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// receber id contato para edicao
		String id = request.getParameter("id");
		contato.setId(id);
		dao.selecionarContato(contato);

		// setar os atributos
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// System.out.println(request.getParameter("id"));

		// enviar ao doc editarContato.jsp
		RequestDispatcher rd = request.getRequestDispatcher("EditarContato.jsp");
		rd.forward(request, response);
	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * System.out.println(request.getParameter("id"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */
		// setar as variaveis java beans
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		dao.alterarContato(contato);
		// redirecionar para agenda
		response.sendRedirect("main");

	}

	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id =  request.getParameter("id");
		//System.out.println(id);
		contato.setId(id);
		dao.deletarContato(contato);
		response.sendRedirect("main");
	}
}

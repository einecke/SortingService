package servlets;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import objects.Book;
import utils.OrderBooks;
import utils.SortingServiceException;

public class SortingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(SortingServlet.class.getName());

	public SortingServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> books = new ArrayList<Book>();
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Collection<Book>>() {
		}.getType();
		String listJson = request.getParameter("books");
		Collection<Book> jsonBooks = gson.fromJson(listJson, collectionType);

		if (jsonBooks == null) {
			throw new SortingServiceException("");
		}

		for (Book book : jsonBooks) {
			books.add(book);
		}

		LOGGER.info("Lista de livros recebida: ");
		logBooks(books);

		File file = new File(request.getParameter("configPath"));

		if (!file.exists()) {
			LOGGER.error("Arquivo de configuração não foi encontrado " + request.getParameter("configPath") + " não foi encontrado.");
			setHeader(response);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("Arquivo de configuração não foi encontrado.");
		} else {
			OrderBooks comparator = new OrderBooks(request.getParameter("configPath"));
			Collections.sort(books, comparator);

			LOGGER.info("Processo realizado com sucesso.");
			LOGGER.info("Lista de livros ordenada: ");
			logBooks(books);

			setHeader(response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(gson.toJson(books));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	private void setHeader(HttpServletResponse response){
		response.addHeader("Content-transfer-encoding", "base64");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST");
		response.addHeader("Access-Control-Max-Age", "1000");
	}
	
	private void logBooks(List<Book> books) {
		for (Book book : books) {
			LOGGER.info("Titulo: " + book.getTitle() + " ||" + " Autor: " + book.getAuthor() + " ||" + " Ano: "
					+ book.getYear());
		}
	}
}
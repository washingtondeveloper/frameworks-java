package br.com.webframework.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Properties props;

	static {
		try {
			props = new Properties();
			InputStream is = FrontController.class.getResourceAsStream("/actions.properties");
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		path = path.substring(1, path.indexOf("."));
		
		String clazz = props.getProperty(path);
		if(clazz == null || clazz == ""){
			throw new IOException("Não existe essa rota! Favor verificar no arquivo actions.properties");
		}
		
		try {
			Action action = (Action) Class.forName(clazz).newInstance();
			action.setRequest(request);
			action.setResponse(response);
			action.process();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

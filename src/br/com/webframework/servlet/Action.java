package br.com.webframework.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class Action {

	private HttpServletRequest request;
	private HttpServletResponse response;

	public abstract void process() throws ServletException, IOException;

	protected void setAttribute(String nome, Object object) {
		getRequest().setAttribute(nome, object);
	}

	protected void forward(String page) throws ServletException, IOException {
		getRequest().getRequestDispatcher(page).forward(getRequest(), getResponse());
	}

	protected void redirect(String url) throws ServletException, IOException {
		getResponse().sendRedirect(url);
	}

	protected HttpSession getHttpSession() {
		return getRequest().getSession();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}

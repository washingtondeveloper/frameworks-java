package br.com.webframework.action;

import java.io.IOException;

import javax.servlet.ServletException;

import br.com.webframework.servlet.Action;

public class CadastrarAction extends Action {

	@Override
	public void process() throws ServletException, IOException {
		setAttribute("teste", "Desenvolvimento");
		forward("pagina.jsp");
	}

}

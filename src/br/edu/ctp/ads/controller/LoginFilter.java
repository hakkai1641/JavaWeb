package br.edu.ctp.ads.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ctp.ads.entity.ClienteFisico;

//@WebFilter("/telaCarrinhoDeCompras.jsf")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;

		// tento capturar o usuário da sessão
		ClienteFisico u = (ClienteFisico) req.getSession().getAttribute("cliente");

		// caso seja nulo redireciono para a tela de login
		// neste ponto adiciono qual tela o usuário tentava acessar
		if (u == null) {
			req.getSession().setAttribute("pagina", "telaCarrinhoDeCompras.jsf");
			res.sendRedirect("telaCarrinhoDeCompras.jsf");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
